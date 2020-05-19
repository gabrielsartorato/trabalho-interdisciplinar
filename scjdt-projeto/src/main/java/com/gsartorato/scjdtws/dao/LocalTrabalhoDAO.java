package com.gsartorato.scjdtws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gsartorato.scjdtws.config.DBConfig;
import com.gsartorato.scjdtws.entidade.LocalTrabalho;

public class LocalTrabalhoDAO {
	
	DBConfig conn = new DBConfig();
	
	LocalTrabalhoDAO localDAO = null;
	
	public void inserirLocal(LocalTrabalho localT) throws Exception {
		
		Connection conn  =  DBConfig.getConnection();
		
		try {
			
			String sql = "INSERT INTO \"localTrabalho\" (\"nomeLocalTrabalho\") VALUES (?)";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, localT.getNomeLocalTrabalho());
			stmt.execute();
			stmt.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editarLocal(LocalTrabalho localT, int idLocal) throws Exception {
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "UPDATE \"localTrabalho\" SET \"nomeLocalTrabalho\" = ? where \"idLocalTrabalho\" = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, localT.getNomeLocalTrabalho());
		stmt.setInt(2, idLocal);
		
		stmt.execute();
		stmt.close();
		
	}
	
	public void excluirLocal(int idLocal) throws Exception {
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "DELETE FROM \"localTrabalho\" WHERE \"idLocalTrabalho\" = ?";
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idLocal);
			stmt.execute();
			stmt.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public LocalTrabalho findById(int idLocal) throws Exception {
		
		LocalTrabalho localT = null;
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "Select * from \"localTrabalho\" where \"idLocalTrabalho\" = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, idLocal);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			localT = new LocalTrabalho();
			
			localT.setIdLocalTrabalho(rs.getInt("idLocalTrabalho"));
			localT.setNomeLocalTrabalho(rs.getString("nomeLocalTrabalho"));
		}
		
		return localT;
		
	}
	
	public List<LocalTrabalho> listarLocal() throws Exception{
		List<LocalTrabalho> listarLocal = new ArrayList<LocalTrabalho>();
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "SELECT * FROM \"localTrabalho\"";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			LocalTrabalho localT = new LocalTrabalho();
			
			localT.setIdLocalTrabalho(rs.getInt("idLocalTrabalho"));
			localT.setNomeLocalTrabalho(rs.getString("nomeLocalTrabalho"));
			
			listarLocal.add(localT);
		}
		
		return listarLocal;
	}

}
