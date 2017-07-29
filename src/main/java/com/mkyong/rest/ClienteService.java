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
import javax.ws.rs.core.Response.Status;

import com.mkyong.entidades.Cliente;
import com.mkyong.entidades.ContaCorrente;
import com.mkyong.entidades.LogDeposito;

import common.StatusDeposito;
import conversor.ClienteConversor;
import dao.ClienteDAO;
import dao.ContaCorrenteDAO;
import dto.ContaCorrenteDTO;
import factory.Singleton;
import request.DepositoRequest;

@Path("/cliente")
public class ClienteService {

	private static final int STATUS_CODE_NOT_FOUND = 404;
	private static final int STATUS_CODE_OK = 200;
	private static final int STATUS_CODE_ERROR = 500;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}/saldoContaCorrente")
	public Response consultarSaldo(final @PathParam("id") String id) {
		Response resposta = null;
		ClienteDAO clienteDAO = Singleton.INSTANCE.getClienteDAO();

		// TODO: e se parsing falhar?
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
				logDeposito.setStatus(StatusDeposito.CONTA_NAO_EXISTE);
			}
			else {
				double saldoAntesDoDeposito = contaCorrente.getSaldo();
				contaCorrente.setSaldo(saldoAntesDoDeposito + depositoRequest.getValor());
				resposta = Response.status(STATUS_CODE_OK).build();
				logDeposito.setStatus(StatusDeposito.SUCESSO);
			}
		}
		catch (Exception e) {
			resposta = Response.status(STATUS_CODE_ERROR).build();
			logDeposito.setStatus(StatusDeposito.EXCECAO);
			logDeposito.setMsgExcecao(e.getMessage());
		}
		Singleton.INSTANCE.getLogDepositoDAO().add(logDeposito);
		return resposta;
	}

}
