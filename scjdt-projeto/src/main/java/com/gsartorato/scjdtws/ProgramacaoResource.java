package com.gsartorato.scjdtws;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.gsartorato.scjdtws.dao.ProgramacaoHorariaDAO;
import com.gsartorato.scjdtws.entidade.ProgramacaoHoraria;

@Path("programacao")
public class ProgramacaoResource {
	
	private ProgramacaoHorariaDAO progDao;
	
	@PostConstruct
	private void init() {
		progDao = new ProgramacaoHorariaDAO();
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response inserirProgramacao(ProgramacaoHoraria progHor) {
		String msg = "";
		
		try {
			
			int id = progDao.inserirProgramacao(progHor);
			
			msg = "Programação incluida com sucesso, id: " + id;
			
			return Response.status(201).entity(msg).build();
		}
		
		catch (Exception e) {
			e.printStackTrace();
			msg = "Não foi possivel salvar a programação";
			return Response.status(401).entity(msg).build();
		}
	}

}
