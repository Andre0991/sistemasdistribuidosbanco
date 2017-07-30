package com.mkyong.rest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import com.mkyong.entidades.Banco;
import javax.ws.rs.core.MediaType;
import dao.BancoDAO;
import factory.SingletonBanco;
import javax.ws.rs.core.Response;




@Path("/banco")
public class BancoService {

			private static final int STATUS_CODE_NOT_FOUND = 404;
			private static final int STATUS_CODE_OK = 200;

			@GET
			@Produces(MediaType.APPLICATION_JSON)
			@Path("/{id}")
			public Response consultarBanco(final @PathParam("id") int id) {
				Response resposta = null;
				
				BancoDAO bancoDAO = SingletonBanco.INSTANCE.getBancoDAO();
				Banco banco = bancoDAO.findById(id);
				
				if (banco == null) {
					resposta = Response.status(STATUS_CODE_NOT_FOUND).build();
				}
				else {
					resposta = Response.status(STATUS_CODE_OK).entity(banco).build();
				}
				return resposta;
			}

		}

		
		
		
	


