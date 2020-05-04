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


import com.gsartorato.scjdtws.dao.LocalTrabalhoDAO;
import com.gsartorato.scjdtws.entidade.LocalTrabalho;

@Path("localtrabalho")
public class LocalTrabalhoResource {

	private LocalTrabalhoDAO localDAO;
	
	@PostConstruct
	private void init() {
		localDAO = new LocalTrabalhoDAO();
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addLocal(LocalTrabalho localT) {
		String msg = "";
		
		try {
			
			localDAO.inserirLocal(localT);
			
			msg = "Local de trabalho inseiro com sucesso: " + localT.getNomeLocalTrabalho();
			
			return Response.status(Response.Status.CREATED).entity(msg).build();
		}catch (Exception e) {
			e.printStackTrace();
			msg = "Não foi possivel inserir o local de trabalho";
			
			return Response.status(401).entity(msg).build();
		}
	}
	
	@PUT
	@Path("/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response editLocal(LocalTrabalho localT, @PathParam("id") int idLocal) {
		String msg = "";
		
		try {
			
			localDAO.editarLocal(localT, idLocal);
			
			msg = "Local de Trabalho alterado com sucesso";
			
			return Response.status(201).entity(msg).build();
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
			msg = "Não foi possivel alterar o local de trabalho";
			
			return Response.status(401).entity(msg).build();
			
		}
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response excluirLocal(@PathParam("id") int idLocal) {
		String msg = "";
		
		try {
			
			localDAO.excluirLocal(idLocal);
			
			msg = "Local exclíudo com sucesso";
			
			return Response.status(201).entity(msg).build();
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
			msg = "Não foi possivel excluir o local de Trabalho";
			
			return Response.status(401).entity(msg).build();
		}
		
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listLocalTrabalho(){
		List<LocalTrabalho> listarLocal = null;
		
		try {
			
			listarLocal = localDAO.listarLocal();
			
			return Response.status(201).entity(listarLocal).build();
			
		}catch (Exception e) {
			e.printStackTrace();
			String msg = "Não foi possivel exibir a lista";
			return Response.status(401).entity(msg).build();
		}
		
	}
	
}
