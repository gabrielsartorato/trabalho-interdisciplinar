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

import com.gsartorato.scjdtws.dao.ProgramacaoFeriasDAO;
import com.gsartorato.scjdtws.entidade.ProgramacaoFerias;
import com.gsartorato.scjdtws.exception.RegraNegocioException;
import com.sun.xml.internal.stream.Entity;

@Path("/ferias")
public class ProgramcaoFeriasResource {
	
	private ProgramacaoFeriasDAO programacaoFeriasDAO;
	
	@PostConstruct
	private void init() {
		programacaoFeriasDAO = new ProgramacaoFeriasDAO();
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response inserirProgFerias(ProgramacaoFerias progFer) {
		String msg = "";
		int programacao_id;
		
		try {
			programacao_id = programacaoFeriasDAO.inserirProgramacaoFerias(progFer);
			
			if(programacao_id == 0) {
				msg = "Não foi possivel inserir a programação de férias";
				return Response.status(400).entity(msg).build();
			}
			
			msg = "Programação inserida com sucesso, código: " + programacao_id;
			return Response.status(200).entity(msg).build();
			
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}
	
	@PUT
	@Path("/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response editarProgramacao(@PathParam("id") int id_ferias, ProgramacaoFerias progFerias) throws SQLException, Exception, RegraNegocioException {
		String msg = "";
		
		try {
			programacaoFeriasDAO.alterarProgramacaoFerias(progFerias, id_ferias);
			
			msg = "Programação de férias alterada com sucesso";
			
			return Response.status(200).entity(msg).build();
			
		} catch (RegraNegocioException e) {
			e.printStackTrace();
			msg = e.getMessage();
			return Response.status(400).entity(msg).build();
		}
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleterProgramacaoFerias(@PathParam("id") int id_ferias) {
		String msg = "";
		
		try {
			programacaoFeriasDAO.excluirProgramacaoFerias(id_ferias);
			
			msg = "Programação excluída com sucesso";
			
			return Response.status(200).entity(msg).build();
			
		} catch (RegraNegocioException e) {
			e.printStackTrace();
			msg = e.getMessage();
			return Response.status(200).entity(msg).build();
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
			return Response.status(200).entity(msg).build();
		}
	}
	
	@GET
	@Path("/list")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarProgramacaoFerias() {
		List<ProgramacaoFerias> listarColaborador = null;
		
		try {
			
			listarColaborador = programacaoFeriasDAO.listarProgramacaoFerias();
			
			return Response.status(200).entity(listarColaborador).build();
		} catch (Exception e) {
			String msg = e.getMessage();
			return Response.status(400).entity(msg).build();
		}
	}
}
