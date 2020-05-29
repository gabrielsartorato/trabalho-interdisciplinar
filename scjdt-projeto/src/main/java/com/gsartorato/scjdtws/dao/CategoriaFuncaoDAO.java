package com.gsartorato.scjdtws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gsartorato.scjdtws.config.DBConfig;
import com.gsartorato.scjdtws.entidade.CategoriaFuncao;
import com.gsartorato.scjdtws.exception.RegraNegocioException;

public class CategoriaFuncaoDAO {
	
	DBConfig conn = new DBConfig();
	
	CategoriaFuncaoDAO localDAO = null;
	
	public void inserirCategoria(CategoriaFuncao cateFnc) throws Exception {
		
		Date date = new Date();
		
		long time = date.getTime();
		
		Timestamp ts = new Timestamp(time);
		
		Connection conn  =  DBConfig.getConnection();
		
		if(findByName(cateFnc.getNome_categoria()) == null) {
			try {
				
				String sql = "INSERT INTO categoria_funcao (nome_categoria, created_at) VALUES (?, ?)";
				
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, cateFnc.getNome_categoria());
				stmt.setTimestamp(2, ts);
				stmt.execute();
				stmt.close();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			throw new RegraNegocioException("Nome para categoria já existente!");
		}
	}
	
	public void editarCategoria(CategoriaFuncao catFnc, int id_funcao) throws Exception {
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "UPDATE categoria_funcao SET nome_categoria = ? where id_categoria = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, catFnc.getNome_categoria());
		stmt.setInt(2, id_funcao);
		
		stmt.execute();
		stmt.close();
		
	}
	
	public void excluirCategoria(int id_categoria) throws Exception {
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "DELETE FROM categoria_funcao WHERE id_categoria = ?";
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id_categoria);
			stmt.execute();
			stmt.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public CategoriaFuncao findById(int id_categoria) throws Exception {
		
		CategoriaFuncao catFnc = null;
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "Select * FROM categoria_funcao where id_categoria = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id_categoria);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			catFnc = new CategoriaFuncao();
			
			catFnc.setId_categoria(rs.getInt("id_categoria"));
			catFnc.setNome_categoria(rs.getString("nome_categoria"));
		}
		
		return catFnc;
		
	}
	
	public List<CategoriaFuncao> listarCategorias() throws Exception{
		List<CategoriaFuncao> listarCategorias = new ArrayList<CategoriaFuncao>();
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "SELECT * FROM categoria_funcao";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			CategoriaFuncao catFnc = new CategoriaFuncao();
			
			catFnc.setId_categoria(rs.getInt("id_categoria"));
			catFnc.setNome_categoria(rs.getString("nome_categoria"));
			
			listarCategorias.add(catFnc);
		}
		
		return listarCategorias;
	}

	public CategoriaFuncao findByName(String name) throws Exception {
		
		CategoriaFuncao catFnc = null;
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "Select * from categoria_funcao where nome_categoria = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, name);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			catFnc = new CategoriaFuncao();
			
			catFnc.setNome_categoria(rs.getString("nome_categoria"));
		}
		
		return catFnc;
		
	}
}
