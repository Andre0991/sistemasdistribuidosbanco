package com.mkyong.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mkyong.entidades.Cliente;
import com.mkyong.entidades.LogDeposito;
import com.mkyong.entidades.LogSaque;
import com.mkyong.entidades.LogTed;
import com.mkyong.entidades.LogTransferencia;

import factory.Singleton;

@Path("/cliente")
public class ClienteService {

	private static final String TED_RECEIVER_NAME = "tedReceiver";
	private static final int STATUS_CODE_OK = 200;
	private static final int STATUS_CODE_NOT_FOUND = 404;
	private static final int STATUS_CODE_UNPROCESSABLE_ENTITY = 422;
	private static final int STATUS_CODE_ERROR = 500;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		List<Cliente> clientes = Singleton.INSTANCE.getClienteDAO().findAll();
		return Response.status(STATUS_CODE_OK).entity(clientes).build();
	}

	@GET
	@Path("/depositos/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllDepositosFromCliente(final @PathParam("id") String id) {
		List<LogDeposito> depositos = Singleton.INSTANCE.getLogDepositoDAO().findByCliente(Long.parseLong(id));
		return Response.status(STATUS_CODE_OK).entity(depositos).build();
	}
	
	
	@GET
	@Path("/saques/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllSaquesFromCliente(final @PathParam("id") String id) {
		List<LogSaque> saques = Singleton.INSTANCE.getLogSaqueDAO().findByCliente(Long.parseLong(id));
		return Response.status(STATUS_CODE_OK).entity(saques).build();
	}
	
	/*o findByCliente traz o cliente se ele for sender ou receiver, 
	em uma transferencia só pode ser um dos dois, logo traz todas as transferencias em que um cliente participa */
	
	@GET
	@Path("/transferencia/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllTransferenciasFromCliente(final @PathParam("id") String id) {
		List<LogTransferencia> transferencias = Singleton.INSTANCE.getLogTransferenciaDAO().findByCliente(Long.parseLong(id));
		return Response.status(STATUS_CODE_OK).entity(transferencias).build();
	}
	
	// o findByCliente traz o cliente se ele for o sender ou receiver
	@GET
	@Path("/TED/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllTEDSFromCliente(final @PathParam("id") String id) {
		List<LogTed> teds = Singleton.INSTANCE.getLogTedDAO().findByCliente(Long.parseLong(id));
		return Response.status(STATUS_CODE_OK).entity(teds).build();
	}

}
