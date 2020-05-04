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


import com.gsartorato.scjdtws.dao.VeiculoDAO;
import com.gsartorato.scjdtws.entidade.Veiculo;
import com.gsartorato.scjdtws.exception.RegraNegocioException;

@Path("veiculo")
public class VeiculoResource {
	
	private VeiculoDAO veicDAO;
	
	@PostConstruct
	private void init() {
		veicDAO = new VeiculoDAO();
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Veiculo> listarVeiculo(){
		List<Veiculo> veic = null;
		
		try {
			
			veic = veicDAO.listarVeiculos();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return veic;
	}
	
	@PUT
	@Path("/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String editarVeic(Veiculo veic, @PathParam("id") int idVeic) {
		String msg = "";
		try {
			veicDAO.editarVeiculo(veic, idVeic);
			msg = "nota editada com sucesso";
			
		}catch(Exception e){
			msg = "Erro ao editar a nota!";
			e.printStackTrace();
		}
		
		return msg;
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String removerVeic(@PathParam("id") int codVeic) {
		String msg = "";
		
		try {
			
			veicDAO.excluirVeiculo(codVeic);
			msg = "Veiculo Excluido com sucesso";
			
		}catch(Exception e) {
			msg = e.getMessage();
			e.printStackTrace();
		}

		return msg;
	}
	
	@GET
	@Path("/get/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Veiculo veic (@PathParam("id") int codveic) {
		
		Veiculo veic = null;
		
		try {
			veic = veicDAO.buscarPorId(codveic);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return veic;
		
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addVeic(Veiculo veic) throws Exception{
		String msg = "";
		
		try {
			
			veicDAO.addVeic(veic);

		    msg = "Veiculo cadastrado com sucesso: " + veic.getPREFIXOVEIC();
					
		}catch(RegraNegocioException e ) {
			
			msg = e.getMessage();
			e.printStackTrace();
			
		}
		
		return msg;

	}
}
