package com.gsartorato.scjdtws;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	
	@PUT
	@Path("/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response editarProgramacao(@PathParam("id") int id_programacao, ProgramacaoHoraria proHor) throws SQLException, Exception {
		String msg = "";
		
		try {
			msg = progDao.editarProgramacao(id_programacao, proHor);
			return Response.status(201).entity(msg).build();
		}
		catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
			return Response.status(401).entity(msg).build();
		}
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deletarProgramcao(@PathParam("id") int id_programacao) {
		String msg;
		
		try {
			msg = progDao.excluirProgramacao(id_programacao);
			
			return Response.status(201).entity(msg).build();
		}
		catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
			return Response.status(401).entity(msg).build();
		}
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarProgramacao() {
		String msg = "";
		List<ProgramacaoHoraria> listarProgramacao = null;
		
		try {
			listarProgramacao = progDao.listarProgramacao();
			
			return Response.status(201).entity(listarProgramacao).build();
		}
		catch (Exception e) {
			e.printStackTrace();
			msg = "Não foi possivel listar as programações";
			return Response.status(401).entity(msg).build();
		}
	}

}
