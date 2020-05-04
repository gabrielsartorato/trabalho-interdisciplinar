package com.gsartorato.scjdtws;

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

import com.gsartorato.scjdtws.dao.CategoriaFuncaoDAO;
import com.gsartorato.scjdtws.entidade.CategoriaFuncao;

@Path("/categoria")
public class CategoriaFuncaoResource {
	
	private CategoriaFuncaoDAO catDAO;
	
	@PostConstruct
	private void init() {
		catDAO = new CategoriaFuncaoDAO();
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addCategoria(CategoriaFuncao catFnc) {
		String msg = "";
		
		try {
			catDAO.inserirCategoria(catFnc);
			
			msg = "Categoria inserida com sucesso: " + catFnc.getNomeCategoria() + " " + catFnc.getSalarioCategoria();
			
			return Response.status(Response.Status.CREATED).entity(msg).build();
			
		}catch (Exception e) {
			e.printStackTrace();
			msg = "Não foi possivel adicinar a função";
			return Response.status(401).entity(msg).build();
		}
		
		
	}
	
	@PUT
	@Path("/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response editarCategoria(CategoriaFuncao catFnc, @PathParam("id") int idCat) {
		String msg = "";
		
		try {
			
			catDAO.editarCategoria(catFnc, idCat);
			
			msg = "Categoria editada com sucesso";
			
			return Response.status(201).entity(msg).build();
			
		}catch (Exception e) {
			e.printStackTrace();
			
			msg = "Não foi possivel alterar a categoria";
			
			return Response.status(401).entity(msg).build();
		}
		
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteCategoria(@PathParam("id") int idCategoria) {
		String msg = "";
		
		try {
			
			catDAO.excluirCategoria(idCategoria);
			
			msg = "Categoria deleta com sucesso!";
			
			return Response.status(201).entity(msg).build();
			
		}catch (Exception e) {
			e.printStackTrace();
			
			msg = "Não foi possivel excluir a categoria";
			
			return Response.status(401).entity(msg).build();
			
		}
		
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CategoriaFuncao> listaCategoria(){
		List<CategoriaFuncao> listaCategoria = null;
		
		try {
			listaCategoria = catDAO.listarCategoria();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return listaCategoria;
		
	}

}
