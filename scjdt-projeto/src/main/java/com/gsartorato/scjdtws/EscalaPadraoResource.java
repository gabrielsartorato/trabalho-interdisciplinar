package com.gsartorato.scjdtws;

import java.util.ArrayList;
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

import com.gsartorato.scjdtws.dao.EscalaPadraoDAO;
import com.gsartorato.scjdtws.entidade.EscalaPadrao;
import com.gsartorato.scjdtws.entidade.EscalaPadraoToFront;

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
	
	@PUT
	@Path("/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response editEscala(@PathParam("id") int id_escala, EscalaPadrao escPadrao) {
		String msg = "";
		
		try {
			escalaDao.editarEscalaPadrao(escPadrao, id_escala);
			
			msg = "Escala editada com sucesso";
			
			return Response.status(200).entity(msg).build();
			
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
			
			return Response.status(401).entity(msg).build();
		}
	}
	
	@GET
	@Path("/buscar/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPorColaborador(@PathParam("id") int id_colaborador) {
		String msg = "";

		List<EscalaPadraoToFront> listarEscala = new ArrayList<EscalaPadraoToFront>();
		
		try {
			listarEscala = escalaDao.listarPorColaborador(id_colaborador);
			
			return Response.status(200).entity(listarEscala).build();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			msg = e.getMessage();
			
			return Response.status(401).entity(msg).build();
		}
	}
	
	
}
