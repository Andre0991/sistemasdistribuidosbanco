package com.mkyong.rest;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mkyong.entidades.Banco;
import com.mkyong.entidades.Cliente;
import com.mkyong.entidades.ContaCorrente;
import com.mkyong.entidades.LogConsultaSaldo;
import com.mkyong.entidades.LogDeposito;
import com.mkyong.entidades.LogSaque;
import com.mkyong.entidades.LogTransferencia;
import com.mkyong.entidades.LogTed;
import com.mkyong.entidades.LogTransacao;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import common.StatusTransacao;
import conversor.ClienteConversor;
import dao.ClienteDAO;
import dao.ContaCorrenteDAO;
import dao.LogConsultaSaldoDAO;
import dao.LogTedDAO;
import dto.ContaCorrenteDTO;
import factory.Singleton;
import request.DepositoRequest;
import request.TedRequest;
import request.SaqueRequest;
import request.Transferencia;
import response.ErrorResponse;

@Path("/contaCorrente")
public class ContaCorrenteService {


	private static final String TED_RECEIVER_NAME = "tedReceiver";
	private static final int STATUS_CODE_OK = 200;
	private static final int STATUS_CODE_NOT_FOUND = 404;
	private static final int STATUS_CODE_UNPROCESSABLE_ENTITY = 422;
	private static final int STATUS_CODE_ERROR = 500;

	private static final String CONTA_INFORMADA_INEXISTENTE_MSG = "Conta informada não existe";

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/saldo/{id}")
	public Response consultarSaldo(final @PathParam("id") String id) {
		Response resposta = null;
		ClienteDAO clienteDAO = Singleton.INSTANCE.getClienteDAO();
		LogConsultaSaldoDAO logConsultaSaldoDAO = Singleton.INSTANCE.getLogConsultaSaldoDAO();
		LogConsultaSaldo log = new LogConsultaSaldo();
		log.setHorario(new Date());
		log.setIdCliente(id);

		Cliente cliente = clienteDAO.findById(Long.parseLong(id));
		if (cliente == null) {
			resposta = Response.status(STATUS_CODE_NOT_FOUND).build();
			log.setStatus(STATUS_CODE_NOT_FOUND);
		}
		else {
			ContaCorrenteDTO contaCorrenteDTO = new ClienteConversor().convertClienteToContaCorrenteDTO(cliente);
			resposta = Response.status(STATUS_CODE_OK).entity(contaCorrenteDTO).build();
			log.setStatus(STATUS_CODE_OK);
		}
		logConsultaSaldoDAO.add(log);
		return resposta;
	}

	@POST
	@Path("/deposito")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deposito(final DepositoRequest depositoRequest) {
		// log
		LogDeposito logDeposito = new LogDeposito();
		logDeposito.setNumeroConta(depositoRequest.getNumeroConta());
		logDeposito.setHorario(new Date());
		logDeposito.setValor(depositoRequest.getValor());
		Response resposta;

		try {
			// verifica se conta existe
			ContaCorrenteDAO contaCorrenteDAO = Singleton.INSTANCE.getContaCorrenteDAO();
			ContaCorrente contaCorrente = contaCorrenteDAO.findByNumero(depositoRequest.getNumeroConta());
			if (contaCorrente == null) {
				resposta = Response.status(STATUS_CODE_NOT_FOUND).build();
				logDeposito.setStatus(StatusTransacao.CONTA_NAO_EXISTE);
			}
			else {
				double saldoAntesDoDeposito = contaCorrente.getSaldo();
				contaCorrente.setSaldo(saldoAntesDoDeposito + depositoRequest.getValor());
				resposta = Response.status(STATUS_CODE_OK).build();
				logDeposito.setStatus(StatusTransacao.SUCESSO);
				contaCorrente.addTransacao(logDeposito);
			}
		}
		catch (Exception e) {
			resposta = Response.status(STATUS_CODE_ERROR).build();
			logDeposito.setStatus(StatusTransacao.ERRO);
			logDeposito.setMsgExcecao(e.getMessage());
		}
		Singleton.INSTANCE.getLogDepositoDAO().add(logDeposito);
		return resposta;
	}
	
	// TODO: se conta B nao existe, mas conta A existe, desconta do A, mas B nao, é descontado do A e ocorre erro
	@POST
	@Path("/tedSender")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response tedSender(final TedRequest tedRequest) {
		Response response = null;
		// log
		LogTed logTed = new LogTed();
		logTed.setHorario(new Date());
		logTed.setIdBancoDestino(tedRequest.getIdBancoDestino());
		logTed.setNumeroContaDestino(tedRequest.getNumeroContaDestino());
		logTed.setNumeroContaOrigem(tedRequest.getNumeroContaOrigem());
		logTed.setValor(tedRequest.getValor());
		LogTedDAO logTedDAO = Singleton.INSTANCE.getLogTedDAO();
		
		// verifica endereco do banco de destino atraves do banco central
		Client client = Client.create();
		WebResource webResourceBancoCentral = client
		   .resource("http://localhost:10080/RESTfulExample/rest/banco/" + tedRequest.getIdBancoDestino());
		ClientResponse responseBancoCentral = webResourceBancoCentral.accept("application/json")
                   .get(ClientResponse.class);

		// valida resposta do banco central
		if (responseBancoCentral.getStatus() != STATUS_CODE_OK) {
			logTed.setStatus(StatusTransacao.ERRO);
			String msgErro = "Erro na comunicação com o banco central.";
			logTed.setMsgErro(msgErro);
			logTedDAO.add(logTed);
			return Response.status(STATUS_CODE_ERROR).entity(msgErro).build();
		}
		
		// valida se conta origem existe
		ContaCorrente contaOrigem =
				Singleton.INSTANCE.getContaCorrenteDAO().findByNumero(tedRequest.getNumeroContaOrigem());
		if (contaOrigem == null) {
			logTed.setStatus(StatusTransacao.ERRO);
			String msgErro = "Conta origem não encontrada.";
			logTed.setMsgErro(msgErro);
			logTedDAO.add(logTed);
			return Response.status(STATUS_CODE_NOT_FOUND).entity(msgErro).build();
		}
		
		// valida se conta origem tem saldo suficiente para o ted
		if (!contaOrigem.temSaldoParaTransferencia(tedRequest.getValor())) {
			String msgErro = "Conta não tem saldo suficiente para a transferência.";
			logTed.setStatus(StatusTransacao.ERRO);
			logTed.setMsgErro(msgErro);
			logTedDAO.add(logTed);
			return Response.status(STATUS_CODE_UNPROCESSABLE_ENTITY).entity(msgErro).build();
		}

		// envia requisicao para outro banco
		Banco entity = responseBancoCentral.getEntity(Banco.class);
		String endereco = entity.getEndereco();
		WebResource webResourceOutroBanco = client
				.resource(endereco + "contaCorrente/" + TED_RECEIVER_NAME);
		ClientResponse responseOutroBanco = webResourceOutroBanco
				.type("application/json")
				.accept("application/json")
				.post(ClientResponse.class, tedRequest);
		
		// valida resposta do outro banco
		if (responseOutroBanco.getStatus() != STATUS_CODE_OK) {
			logTed.setStatus(StatusTransacao.ERRO);
			String msgErro = "Erro na comunicação com o outro banco.";
			logTed.setMsgErro(msgErro);
			logTedDAO.add(logTed);
			return Response.status(STATUS_CODE_ERROR).entity(msgErro).build();
		}

		logTed.setStatus(StatusTransacao.SUCESSO);
		logTedDAO.add(logTed);
		contaOrigem.addTransacao(logTed);
		response = Response.status(responseOutroBanco.getStatus()).build();
		return response;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/" + TED_RECEIVER_NAME)
	public Response tedReceiver(final TedRequest tedRequest) {
		Response resposta = null;

		// log
		LogTed logTed = new LogTed();
		logTed.setHorario(new Date());
		logTed.setIdBancoDestino(tedRequest.getIdBancoDestino());
		logTed.setNumeroContaDestino(tedRequest.getNumeroContaDestino());
		logTed.setNumeroContaOrigem(tedRequest.getNumeroContaOrigem());
		logTed.setValor(tedRequest.getValor());

		// verifica se conta existe
		ContaCorrenteDAO contaCorrenteDAO = Singleton.INSTANCE.getContaCorrenteDAO();
		ContaCorrente contaDestino = contaCorrenteDAO.findByNumero(tedRequest.getNumeroContaDestino());
		if (contaDestino == null) {
			resposta = Response
					.status(STATUS_CODE_NOT_FOUND)
					.entity(new ErrorResponse(STATUS_CODE_NOT_FOUND, CONTA_INFORMADA_INEXISTENTE_MSG))
					.build();
			logTed.setStatus(StatusTransacao.CONTA_NAO_EXISTE);
		}
		else {
			double saldoAntesDoTED = contaDestino.getSaldo();
			contaDestino.setSaldo(saldoAntesDoTED + tedRequest.getValor());
			resposta = Response.status(STATUS_CODE_OK).build();
			logTed.setStatus(StatusTransacao.SUCESSO);
			contaDestino.addTransacao(logTed);
		}
		Singleton.INSTANCE.getLogTedDAO().add(logTed);
		return resposta;
	}
	
		@POST
	@Path("/transferencia")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response transferencia(final Transferencia transferencia) {
		//log
		LogTransferencia logTransferencia = new LogTransferencia();
		logTransferencia.setHorario(new Date());
		logTransferencia.setContaDestino(transferencia.getContaDestino());
		logTransferencia.setContaOrigem(transferencia.getContaOrigem());
		logTransferencia.setValor(transferencia.getValor());
		
		Response resposta;

		try {
			ContaCorrenteDAO contaCorrenteDAO = Singleton.INSTANCE.getContaCorrenteDAO();
			ContaCorrente contaCorrenteDestino = contaCorrenteDAO.findByNumero(transferencia.getContaDestino());
			if (contaCorrenteDestino == null) {
				resposta = Response.status(STATUS_CODE_NOT_FOUND).build();
				logTransferencia.setStatus(StatusTransacao.CONTA_NAO_EXISTE);
			}
			else {
				double saldoAntesDaTransferencia = contaCorrenteDestino.getSaldo();
				contaCorrenteDestino.setSaldo(saldoAntesDaTransferencia + transferencia.getValor());
				resposta = Response.status(STATUS_CODE_OK).build();
				logTransferencia.setStatus(StatusTransacao.SUCESSO);

				ContaCorrente contaCorrenteOrigem = contaCorrenteDAO.findByNumero(transferencia.getContaOrigem());
				if (contaCorrenteOrigem == null) {
					resposta = Response.status(STATUS_CODE_NOT_FOUND).build();
					logTransferencia.setStatus(StatusTransacao.CONTA_NAO_EXISTE);
				}
				else {
					double saldoOrigemAntesDaTransferencia = contaCorrenteOrigem.getSaldo();
					contaCorrenteOrigem.setSaldo(saldoOrigemAntesDaTransferencia - transferencia.getValor());
					resposta = Response.status(STATUS_CODE_OK).build();
					logTransferencia.setStatus(StatusTransacao.SUCESSO);
				}

			}

		}
		catch (Exception e) {
			resposta = Response.status(STATUS_CODE_ERROR).build();
			logTransferencia.setStatus(StatusTransacao.ERRO);
			logTransferencia.setMsgExcecao(e.getMessage());

		}
		return resposta;
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/extrato/{id}")
	public Response extrato(final @PathParam("id") String id) {
		Response resposta = null;
		ClienteDAO clienteDAO = Singleton.INSTANCE.getClienteDAO();

		Cliente cliente = clienteDAO.findById(Long.parseLong(id));
		if (cliente == null) {
			resposta = Response.status(STATUS_CODE_NOT_FOUND).build();
		}
		else {
			List<LogTransacao> historicoTransacoes = cliente.getContaCorrente().getHistoricoTransacoes();
			resposta = Response.status(STATUS_CODE_OK).entity(historicoTransacoes).build();
		}
		return resposta;
	}
	
	@POST
	@Path("/saque")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saqueContaCorrente(final SaqueRequest saqueRequest) {
		// log
		LogSaque logSaque = new LogSaque();
		logSaque.setNumeroConta(saqueRequest.getNumeroConta());
		logSaque.setHorario(new Date());
		logSaque.setValor(saqueRequest.getValor());
		Response resposta;

		try {
			ContaCorrenteDAO contaCorrenteDAO = Singleton.INSTANCE.getContaCorrenteDAO();
			ContaCorrente contaCorrente = contaCorrenteDAO.findByNumero(saqueRequest.getNumeroConta());
			if (contaCorrente == null) {
				resposta = Response.status(STATUS_CODE_NOT_FOUND).build();
				logSaque.setStatus(StatusTransacao.CONTA_NAO_EXISTE);
			}
			else {
				double saldoAntesDoSaque = contaCorrente.getSaldo();
				contaCorrente.setSaldo(saldoAntesDoSaque - saqueRequest.getValor());
				resposta = Response.status(STATUS_CODE_OK).build();
				logSaque.setStatus(StatusTransacao.SUCESSO);
			}
		}
		catch (Exception e) {
			resposta = Response.status(STATUS_CODE_ERROR).build();
			logSaque.setStatus(StatusTransacao.ERRO);
			logSaque.setMsgExcecao(e.getMessage());
		}
		Singleton.INSTANCE.getLogSaqueDAO().add(logSaque);
		return resposta;
	}
			
}

