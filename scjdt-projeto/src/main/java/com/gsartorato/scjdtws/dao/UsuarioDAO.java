package com.gsartorato.scjdtws.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.gsartorato.scjdtws.config.DBConfig;
import com.gsartorato.scjdtws.entidade.Usuario;
import com.gsartorato.scjdtws.exception.RegraNegocioException;

public class UsuarioDAO {

	
	DBConfig conn = new DBConfig();
	
	Usuario usuario = null;
	
	public void inserirUsuario(Usuario usuario) throws Exception {
		
		Date date = new Date();
		
		long time = date.getTime();
		
		Timestamp ts = new Timestamp(time);
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "INSERT INTO usuario (\"nomeUsuario\", senha, created_at) VALUES (?, ?, ?)";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, usuario.getNomeUsuario());
		stmt.setString(2, usuario.getSenha());
		stmt.setTimestamp(3, ts);
		stmt.execute();
		
	}
	
	public void editarUsuario(Usuario usuario, int idUsuario) throws Exception {
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "UPDATE usuario "
				+ "SET \"nomeUsuario\" = ?, "
				+ "senha = ? "
				+ "WHERE \"idUsuario\" = ? ";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, usuario.getNomeUsuario());
		stmt.setString(2, usuario.getSenha());
		stmt.setInt(3, idUsuario);
		stmt.execute();	
		
	}
	
	public void excluirUsuario(int idUsuario) throws Exception {
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "DELETE FROM usuario WHERE \"idUsuario\" = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, idUsuario);
		
		stmt.execute();
		
	}
	
	public List<Usuario> listarUsuarios () throws Exception {
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "SELECT * FROM usuario";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Usuario usuario = new Usuario();
			
			usuario.setIdUsuario(rs.getInt("idUsuario"));
			usuario.setNomeUsuario(rs.getString("nomeUsuario"));
			usuario.setSenha(rs.getString("senha"));
			
			listaUsuario.add(usuario);
		}
		
		return listaUsuario;
	}
	
	public Usuario autenticarUsuario(Usuario usuario) throws Exception {
		
		String usuario1 = usuario.getNomeUsuario();
		String senha1 = usuario.getSenha();
		
		if(usuario.getNomeUsuario().isEmpty() || usuario.getSenha().isEmpty()) {
			throw new RegraNegocioException("Usuario e senha não podem ser vazios");
		}else {
		
			Connection conn = DBConfig.getConnection();
			
			String sql = "SELECT * from usuario where \"nomeUsuario\" = ? and senha = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, usuario1);
			stmt.setString(2, senha1);
			ResultSet rs = null;
			rs = stmt.executeQuery();
			
			Usuario usuarioAutenticar = new Usuario();
			
			if(rs.next()) {
				usuarioAutenticar.setIdUsuario(rs.getInt("idUsuario"));
				usuarioAutenticar.setNomeUsuario(rs.getString("nomeUsuario"));
				usuarioAutenticar.setSenha(rs.getString("senha"));
				
				usuario.setIdUsuario(usuarioAutenticar.getIdUsuario());

			}
			
			if(usuarioAutenticar.getNomeUsuario() == null) {
				throw new RegraNegocioException("Usuario ou senha incorreta!");
			}
			if(usuarioAutenticar.getSenha() == null) {
				throw new RegraNegocioException("Usuario ou senha incorreta!");
			}
			
		}
		
		return usuario;
		
	}
	
	public Usuario findById(int idUsuario) throws Exception, SQLException {
		
		Connection conn = DBConfig.getConnection();
		
		Usuario usuario = null;
		
		String sql = "SELECT * FROM usuario WHERE \"idUsuario\" = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, idUsuario);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			usuario = new Usuario();
			
			usuario.setIdUsuario(rs.getInt("idUsuario"));
			usuario.setNomeUsuario(rs.getString("nomeUsuario"));
			usuario.setSenha(rs.getString("senha"));
		}
		
		return usuario;
		
	}
	
}
