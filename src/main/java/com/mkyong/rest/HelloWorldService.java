package com.mkyong.rest;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
 
@Path("/hello")
public class HelloWorldService {
 
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
 
<<<<<<< HEAD
		String output = "Iasmin says : " + msg;
=======
		String output = "Teste: modificado - mensgem: " + msg;
>>>>>>> 23a02d9f77428118852dd31dd8b12a621945a222
 
		return Response.status(200).entity(output).build();
 
	}
 
}