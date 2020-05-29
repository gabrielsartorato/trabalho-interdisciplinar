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
import com.gsartorato.scjdtws.exception.RegraNegocioException;

@Path("categoria")
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
	public Response addLocal(CategoriaFuncao catFnc) throws Exception {
		String msg = "";
		
		System.out.println(catFnc.getNome_categoria());
		
		try {
			
			catDAO.inserirCategoria(catFnc);
			
			msg = "Categoria de função inserida com sucesso: " + catFnc.getNome_categoria();
			
			return Response.status(Response.Status.CREATED).entity(msg).build();
		}catch (RegraNegocioException e) {
			
			msg = e.getMessage();
			System.out.println(msg);
			
			return Response.status(401).entity(msg).build();
		}
	}
	
	@PUT
	@Path("/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response editLocal(CategoriaFuncao catFnc, @PathParam("id") int id_categoria) {
		String msg = "";
		
		try {
			
			catDAO.editarCategoria(catFnc, id_categoria);
			
			msg = "Categoria editada com sucesso!";
			
			return Response.status(201).entity(msg).build();
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
			msg = "Não foi possivel alterar a categoria de função, tente novamente!";
			
			return Response.status(401).entity(msg).build();
			
		}
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response excluirLocal(@PathParam("id") int id_categoria) {
		String msg = "";
		
		try {
			
			catDAO.excluirCategoria(id_categoria);
			
			msg = "Categoria exclíuda com sucesso";
			
			return Response.status(201).entity(msg).build();
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
			msg = "Não foi possivel excluir a categoria de função";
			
			return Response.status(401).entity(msg).build();
		}
		
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listCategoria(){
		List<CategoriaFuncao> listarCategoria = null;
		
		try {
			
			listarCategoria = catDAO.listarCategorias();
			
			return Response.status(201).entity(listarCategoria).build();
			
		}catch (Exception e) {
			e.printStackTrace();
			String msg = "Não foi possivel exibir a lista";
			return Response.status(401).entity(msg).build();
		}
		
	}
	
	@GET
	@Path("/buscar/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") int id_categoria) {
		
		String msg = "";
		
		try {
			CategoriaFuncao catFnc = catDAO.findById(id_categoria);
			
			return Response.status(201).entity(catFnc).build();
		}
		catch (Exception e) {
			e.printStackTrace();
			msg = "Não foi possivel buscar a categoria";
			return Response.status(401).entity(msg).build();
		}
		
	}
	
}
