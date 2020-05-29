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

import com.gsartorato.scjdtws.dao.FuncaoDAO;
import com.gsartorato.scjdtws.entidade.Funcao;

@Path("/funcao")
public class FuncaoResource {
	
	private FuncaoDAO fncDAO;
	
	@PostConstruct
	private void init() {
		fncDAO = new FuncaoDAO();
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addCategoria(Funcao fnc) {
		String msg = "";
		
		try {
			fncDAO.inserirFuncao(fnc);
			
			msg = "Função inserida com sucesso: " + fnc.getNome_funcao();
			
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
	public Response editarCategoria(Funcao fnc, @PathParam("id") int id_funcao) {
		String msg = "";
		
		try {
			
			fncDAO.editarFuncao(fnc, id_funcao);
			
			msg = "Função editada com sucesso";
			
			return Response.status(201).entity(msg).build();
			
		}catch (Exception e) {
			e.printStackTrace();
			
			msg = "Não foi possivel alterar a função, tente novamente!";
			
			return Response.status(401).entity(msg).build();
		}
		
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteCategoria(@PathParam("id") int id_funcao) {
		String msg = "";
		
		try {
			
			fncDAO.excluirFuncao(id_funcao);
			
			msg = "Função deleta com sucesso!";
			
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
	public List<Funcao> listaCategoria(){
		List<Funcao> listarFuncao = null;
		
		try {
			listarFuncao = fncDAO.listarFuncao();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return listarFuncao;
		
	}
	
	@GET
	@Path("/buscar/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") int id_funcao) {
		
		String msg = "";
		
		try {
			Funcao fnc = fncDAO.findById(id_funcao);
			
			return Response.status(201).entity(fnc).build();
		}
		catch (Exception e) {
			e.printStackTrace();
			msg = "Não foi possivel buscar a função";
			return Response.status(401).entity(msg).build();
		}
		
	}

}
