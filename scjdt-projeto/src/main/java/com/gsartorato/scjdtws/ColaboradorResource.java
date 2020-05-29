package com.gsartorato.scjdtws;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.gsartorato.scjdtws.dao.ColaboradorDAO;
import com.gsartorato.scjdtws.entidade.Colaborador;
import com.gsartorato.scjdtws.exception.RegraNegocioException;

import org.apache.log4j.Logger;

@Path("/colaborador")
public class ColaboradorResource {
	
	final static Logger logger = Logger.getLogger(ColaboradorResource.class);
	
	private ColaboradorDAO colDao;
	
	@PostConstruct
	private void init() {
		colDao = new ColaboradorDAO();
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addCategoria(Colaborador col) throws Exception {
		String msg = "";
		
		try {
			colDao.inserirColaborador(col);
			
			msg = "Colaborador criado com sucesso: " + col.getNome_colaborador();
			
			return Response.status(Response.Status.CREATED).entity(msg).build();
			
		}catch (RegraNegocioException e) {
			logger.error("Erro ao tentar inserir: " + e.getMessage());
			
			msg = e.getMessage();
			return Response.status(401).entity(msg).build();
		}
		
	}
	
	@GET
	@Path("/buscar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarColaborador(@PathParam("id") int idColaborador) throws SQLException, Exception {
		String msg = "";
		try {			
			Colaborador colaborador = colDao.buscarColaborador(idColaborador);
			
			return Response.status(201).entity(colaborador).build();
		}
		catch (Exception e) {
			e.printStackTrace();
			msg = "Erro ao buscaro colaborador!";
			return Response.status(401).entity(msg).build();
		}
	}
	
	@PUT
	@Path("/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response editColaborador(Colaborador col,@PathParam("id") int idColaborador) {
		String msg = "";
		
		try {
			colDao.editColaborador(col, idColaborador);
			
			msg = "Colaborador Editado com sucesso!";
			
			return Response.status(201).entity(msg).build();
		}
		catch (Exception e) {
			e.printStackTrace();
			msg = "Não foi possivel alterar o colaborador!";
			return Response.status(401).entity(msg).build();
		}
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listColaborador(){
		List<Colaborador> listarColaborador = null;
		
		try {
			
			listarColaborador = colDao.listColaborador();
			
			return Response.status(201).entity(listarColaborador).build();
			
		}catch (Exception e) {
			e.printStackTrace();
			String msg = "Não foi possivel exibir a lista";
			return Response.status(401).entity(msg).build();
		}
		
	}


}
