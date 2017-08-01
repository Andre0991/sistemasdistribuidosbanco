package com.mkyong.rest;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import com.mkyong.entidades.Banco;
import com.mkyong.entidades.LogConsultaBancoCentral;

import javax.ws.rs.core.MediaType;
import dao.BancoDAO;
import dao.LogConsultaBancoCentralDAO;
import factory.SingletonBanco;
import javax.ws.rs.core.Response;




@Path("/banco")
public class BancoCentralService {

			private static final int STATUS_CODE_NOT_FOUND = 404;
			private static final int STATUS_CODE_OK = 200;

			@GET
			@Produces(MediaType.APPLICATION_JSON)
			@Path("/{id}")
			public Response consultarBanco(final @PathParam("id") int id) {
				Response resposta = null;
				LogConsultaBancoCentral log = new LogConsultaBancoCentral();
				log.setHorario(new Date());
				log.setId(id);
				
				try {
					BancoDAO bancoDAO = SingletonBanco.INSTANCE.getBancoDAO();
					Banco banco = bancoDAO.findById(id);
					if (banco == null) {
						resposta = Response.status(STATUS_CODE_NOT_FOUND).build();
						log.setStatus(STATUS_CODE_NOT_FOUND);
					}
					else {
						resposta = Response.status(STATUS_CODE_OK).entity(banco).build();
						log.setStatus(STATUS_CODE_OK);
					}
				}
				catch (Exception e) {
					log.setMsgExcecao(e.getMessage());
				}
				SingletonBanco.INSTANCE.getLogConsultaBancoCentralDAO().add(log);
				return resposta;
			}

		}

		
		
		
	


