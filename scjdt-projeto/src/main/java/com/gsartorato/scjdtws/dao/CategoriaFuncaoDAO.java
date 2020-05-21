package com.gsartorato.scjdtws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gsartorato.scjdtws.config.ConfigSingleton;
import com.gsartorato.scjdtws.config.DBConfig;
import com.gsartorato.scjdtws.entidade.CategoriaFuncao;

public class CategoriaFuncaoDAO {
	
	DBConfig conn = new DBConfig();
	
	CategoriaFuncao catfunc = null;
	
	ConfigSingleton conSing = ConfigSingleton.getInstancy();
	Connection conexao = conSing.getConexao();
	
	public void inserirCategoria(CategoriaFuncao catFnc) throws Exception, SQLException {
		
		Connection conexao = conSing.getConexao();
		
		String sql = "INSERT INTO \"categoriaFuncao\" (\"nomeCategoria\", \"salarioCategoria\", \"descricaoCategoria\")"
				+ "VALUES (?, ?, ?)";
		try {
			
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, catFnc.getNomeCategoria());
			stmt.setBigDecimal(2, catFnc.getSalarioCategoria());
			stmt.setString(3, catFnc.getDescricaoCategoria());
			
			stmt.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void editarCategoria(CategoriaFuncao catFnc, int idCat) throws Exception, SQLException {
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "UPDATE categoriaFuncao SET "
				+ "nomeCategoria = ?, "
				+ "salarioCategoria = ?, "
				+ "descricaoCategoria = ?" 
				+ "WHERE idCategoria = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, catFnc.getNomeCategoria());
		stmt.setBigDecimal(2, catFnc.getSalarioCategoria());
		stmt.setString(3, catFnc.getDescricaoCategoria());
		stmt.setInt(4, idCat);
		
		stmt.execute();
		
	}
	
	public void excluirCategoria(int idCategoria) throws Exception, SQLException {
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "DELETE FROM categoriaFuncao WHERE idCategoria = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, idCategoria);
		stmt.execute();
		
	}
	
	public List<CategoriaFuncao> listarCategoria() throws Exception, SQLException{
		List<CategoriaFuncao> listaCategoria = new ArrayList<CategoriaFuncao>();
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "SELECT * FROM categoriaFuncao";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			CategoriaFuncao catFnc = new CategoriaFuncao();
			
			catFnc.setIdCategoria(rs.getInt("idCategoria"));
			catFnc.setNomeCategoria(rs.getString("nomeCategoria"));
			catFnc.setSalarioCategoria(rs.getBigDecimal("salarioCategoria"));
			catFnc.setDescricaoCategoria(rs.getString("descricaoCategoria"));
			
			listaCategoria.add(catFnc);
			
		}
		
		return listaCategoria;
	}

}
