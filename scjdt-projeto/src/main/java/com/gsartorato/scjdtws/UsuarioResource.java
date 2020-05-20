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

import com.gsartorato.scjdtws.dao.UsuarioDAO;
import com.gsartorato.scjdtws.entidade.Usuario;
import com.gsartorato.scjdtws.exception.RegraNegocioException;

@Path("usuario")
public class UsuarioResource {
	
	private UsuarioDAO usuarioDAO;
	
	@PostConstruct
	private void init() {
		usuarioDAO = new UsuarioDAO();
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addUsuario(Usuario usuario) throws Exception {
		String msg = "";
		
		try {
			
			usuarioDAO.inserirUsuario(usuario);
			
			msg = "Usuario criado com sucesso: " + usuario.getNomeUsuario();
			
			return Response.status(Response.Status.CREATED).entity(msg).build();
		}catch (Exception e) {
			e.printStackTrace();
			msg = "Não foi possivel criar o usuario.";
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
		}
		
	}
	
	@PUT
	@Path("/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response editarUsuario(Usuario usuario, @PathParam("id") int idUsuario) {
		
		String msg = "";
		
		try {
			
			usuarioDAO.editarUsuario(usuario, idUsuario);
			
			msg = "Usuario alterado com Sucesso";
			
			return Response.status(201).entity(msg).build();
			
		}catch (Exception e) {
			e.printStackTrace();
			msg = "Não foi possivel alterar o usuario";
			return Response.status(401).entity(msg).build();
		}
		
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response removerUsuario(@PathParam("id") int idUsuario) {
		String msg = "";
		
		try {
			usuarioDAO.excluirUsuario(idUsuario);
			
			msg = "Usuario excluído com sucesso";
			
			return Response.status(201).entity(msg).build();
			
		}catch (Exception e) {
			e.printStackTrace();
			msg = "Não foi possivel excluir o usuario";
			 return Response.status(401).entity(msg).build();
		}
		
	}
	
	@POST
	@Path("/autenticar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response autenticarUsuario(Usuario usuario) throws Exception {
		String msg = "";
		try {
			usuarioDAO.autenticarUsuario(usuario);
			Usuario usuario2 = new Usuario();
			
			usuario2.setIdUsuario(usuario.getIdUsuario());
			usuario2.setNomeUsuario(usuario.getNomeUsuario());
			
			return Response.ok(usuario2).build();
			
		}catch (RegraNegocioException e) {
			msg = e.getMessage();
			System.out.println(msg);
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();

		}
		
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaUsuarios() {
		List<Usuario> listaUsuario = null;
		
		try {
			
			listaUsuario = usuarioDAO.listarUsuarios();
			
			return Response.status(201).entity(listaUsuario).build();
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
			String msg = "Não foi possivel listar os usuarios.";
			
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
			
		}
	}
	
	@GET
	@Path("/buscar/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") int idUsuario) {
		
		try {
			
			Usuario usuario = usuarioDAO.findById(idUsuario);
			
			return Response.status(201).entity(usuario).build();
			
		}catch (Exception e) {
			e.printStackTrace();
			String msg = "Usuário não encontrado";
			return Response.status(401).entity(msg).build();
		}
		
	}

}
