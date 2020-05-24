package com.gsartorato.scjdtws;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
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
import com.gsartorato.scjdtws.dao.ColaboradorDAO;
import com.gsartorato.scjdtws.entidade.CategoriaFuncao;
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
			
			msg = "Colaborador criado com sucesso: " + col.getNomeColaborador();
			
			return Response.status(Response.Status.CREATED).entity(msg).build();
			
		}catch (RegraNegocioException e) {
			logger.error("Erro ao tentar inserir: " + e.getMessage());
			e.printStackTrace();
			msg = e.getMessage();
			return Response.status(401).entity(msg).build();
		}
		
	}


}
