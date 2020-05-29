package com.gsartorato.scjdtws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gsartorato.scjdtws.config.ConfigSingleton;
import com.gsartorato.scjdtws.config.DBConfig;
import com.gsartorato.scjdtws.entidade.Funcao;

public class FuncaoDAO {
	
	DBConfig conn = new DBConfig();
	
	Funcao catfunc = null;
	
	ConfigSingleton conSing = ConfigSingleton.getInstancy();
	Connection conexao = conSing.getConexao();
	
	public void inserirFuncao(Funcao fnc) throws Exception, SQLException {
		
		Connection conexao = conSing.getConexao();
		
		Date date = new Date();
		Long created_at = date.getTime();
		Timestamp created = new Timestamp(created_at);
		
		String sql = "INSERT INTO funcao (nome_funcao, salario_funcao, descricao_funcao, id_categoria, created_at)"
				+ "VALUES (?, ?, ?, ?, ?)";
		try {
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, fnc.getNome_funcao());
			stmt.setBigDecimal(2, fnc.getSalario_funcao());
			stmt.setString(3, fnc.getDescricao_funcao());
			stmt.setInt(4, fnc.getId_categoria());
			stmt.setTimestamp(5, created);
			
			stmt.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void editarFuncao(Funcao fnc, int id_funcao) throws Exception, SQLException {
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "UPDATE funcao SET "
				+ "nome_funcao = ?, "
				+ "salario_funcao = ?, "
				+ "descricao_funcao = ?, "
				+ "id_categoria = ?" 
				+ "WHERE id_funcao = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, fnc.getNome_funcao());
		stmt.setBigDecimal(2, fnc.getSalario_funcao());
		stmt.setString(3, fnc.getDescricao_funcao());
		stmt.setInt(4, fnc.getId_categoria());
		stmt.setInt(5, id_funcao);
		
		stmt.execute();
		
	}
	
	public void excluirFuncao(int id_funcao) throws Exception, SQLException {
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "DELETE FROM funcao WHERE id_funcao = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id_funcao);
		stmt.execute();
		
	}
	
	public List<Funcao> listarFuncao() throws Exception, SQLException{
		List<Funcao> listaFuncao = new ArrayList<Funcao>();
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "SELECT * FROM funcao";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Funcao fnc = new Funcao();
			
			fnc.setId_funcao(rs.getInt("id_funcao"));
			fnc.setNome_funcao(rs.getString("nome_funcao"));
			fnc.setSalario_funcao(rs.getBigDecimal("salario_funcao"));
			fnc.setDescricao_funcao(rs.getString("descricao_funcao"));
			fnc.setId_categoria(rs.getInt("id_categoria"));
			
			listaFuncao.add(fnc);
			
		}
		
		return listaFuncao;
	}
	
	public Funcao findById(int id_funcao) throws Exception, SQLException {
		Connection conn = DBConfig.getConnection();
		
		Funcao fnc = null;
		
		String sql = "SELECT * FROM funcao WHERE id_funcao = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id_funcao);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			fnc = new Funcao();
			
			fnc.setId_funcao(rs.getInt("id_funcao"));
			fnc.setNome_funcao(rs.getString("nome_funcao"));
			fnc.setSalario_funcao(rs.getBigDecimal("salario_funcao"));
			fnc.setDescricao_funcao(rs.getString("descricao_funcao"));
			fnc.setId_categoria(rs.getInt("id_categoria"));

		}
		
		return fnc;
	}

}
