package com.mkyong.rest;

import java.util.Date;

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
import com.mkyong.entidades.LogDeposito;
import com.mkyong.entidades.LogTed;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import common.StatusTransacao;
import conversor.ClienteConversor;
import dao.ClienteDAO;
import dao.ContaCorrenteDAO;
import dao.LogTedDAO;
import dto.ContaCorrenteDTO;
import factory.Singleton;
import request.DepositoRequest;
import request.TedRequest;

@Path("/cliente")
public class ClienteService {

	private static final String TED_RECEIVER_NAME = "tedReceiver";
	private static final int STATUS_CODE_OK = 200;
	private static final int STATUS_CODE_NOT_FOUND = 404;
	private static final int STATUS_CODE_UNPROCESSABLE_ENTITY = 422;
	private static final int STATUS_CODE_ERROR = 500;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/saldoContaCorrente/{id}")
	public Response consultarSaldo(final @PathParam("id") String id) {
		Response resposta = null;
		ClienteDAO clienteDAO = Singleton.INSTANCE.getClienteDAO();

		Cliente cliente = clienteDAO.findById(Long.parseLong(id));
		if (cliente == null) {
			resposta = Response.status(STATUS_CODE_NOT_FOUND).build();
		}
		else {
			ContaCorrenteDTO contaCorrenteDTO = new ClienteConversor().convertClienteToContaCorrenteDTO(cliente);
			resposta = Response.status(STATUS_CODE_OK).entity(contaCorrenteDTO).build();
		}
		return resposta;
	}

	@POST
	@Path("/deposito")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response depositoContaCorrente(final DepositoRequest depositoRequest) {
		// log
		LogDeposito logDeposito = new LogDeposito();
		logDeposito.setNumeroConta(depositoRequest.getNumeroConta());
		logDeposito.setHorario(new Date());
		logDeposito.setValor(depositoRequest.getValor());
		Response resposta;

		try {
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
				.resource(endereco + TED_RECEIVER_NAME);
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
		ContaCorrente conta = contaCorrenteDAO.findByNumero(tedRequest.getNumeroContaDestino());
		if (conta == null) {
			resposta = Response.status(STATUS_CODE_NOT_FOUND).build();
			logTed.setStatus(StatusTransacao.CONTA_NAO_EXISTE);
		}
		else {
			double saldoAntesDoTED = conta.getSaldo();
			conta.setSaldo(saldoAntesDoTED + tedRequest.getValor());
			resposta = Response.status(STATUS_CODE_OK).build();
			logTed.setStatus(StatusTransacao.SUCESSO);
		}
		Singleton.INSTANCE.getLogTedDAO().add(logTed);
		return resposta;
	}

}
