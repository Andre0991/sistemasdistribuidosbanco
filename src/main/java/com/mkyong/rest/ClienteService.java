package com.mkyong.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mkyong.entidades.Cliente;

import conversor.ClienteConversor;
import dao.ClienteDAO;
import dto.ContaCorrenteDTO;

@Path("/cliente")
public class ClienteService {

	private static final int STATUS_CODE_NOT_FOUND = 404;
	private static final int STATUS_CODE_OK = 200;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}/saldoContaCorrente")
	public Response consultarSaldo(@PathParam("id") String id) {
		Response resposta = null;
		ClienteDAO clienteDAO = new ClienteDAO();
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

}
