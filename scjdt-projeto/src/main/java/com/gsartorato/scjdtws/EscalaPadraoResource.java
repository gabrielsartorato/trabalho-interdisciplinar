package com.gsartorato.scjdtws;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.gsartorato.scjdtws.dao.EscalaPadraoDAO;
import com.gsartorato.scjdtws.entidade.EscalaPadrao;

@Path("/escala")
public class EscalaPadraoResource {

	private EscalaPadraoDAO escalaDao;
	
	@PostConstruct
	public void init() {
		escalaDao = new EscalaPadraoDAO();
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addEscala(EscalaPadrao escalaPadrao) {
		String msg = "";
		int id_escala = 0;
		
		try {
			id_escala = escalaDao.inserirEscala(escalaPadrao);
			
			msg = "Escala incluída com sucesso, id: " + id_escala;
			
			return Response.status(200).entity(msg).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(200).entity(e.getMessage()).build();
		}
	}
	
}
