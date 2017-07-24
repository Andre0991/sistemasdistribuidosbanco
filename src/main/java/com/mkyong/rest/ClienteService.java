package com.mkyong.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.mkyong.entidades.Cliente;

import dao.ClienteDAO;

@Path("/cliente")
public class ClienteService {

	@GET
	@Path("/{id}/saldo")
	public Response consultarSaldo(@PathParam("id") String id) {
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.findById(Long.parseLong(id));
		String output = cliente.getSaldoContaCorrente().toString();
 
		return Response.status(200).entity(output).build();
 
	}

}
