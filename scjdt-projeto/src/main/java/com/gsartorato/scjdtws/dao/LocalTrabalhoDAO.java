package com.gsartorato.scjdtws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gsartorato.scjdtws.config.DBConfig;
import com.gsartorato.scjdtws.entidade.LocalTrabalho;
import com.gsartorato.scjdtws.exception.RegraNegocioException;

public class LocalTrabalhoDAO {
	
	DBConfig conn = new DBConfig();
	
	LocalTrabalhoDAO localDAO = null;
	
	public void inserirLocal(LocalTrabalho localT) throws Exception {
		
		Date date = new Date();
		
		long time = date.getTime();
		
		Timestamp ts = new Timestamp(time);
		
		Connection conn  =  DBConfig.getConnection();
		
		if(findByName(localT.getNome_local_trabalho()) == null) {
			try {
				
				String sql = "INSERT INTO local_trabalho (nome_local_trabalho, created_at) VALUES (?, ?)";
				
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, localT.getNome_local_trabalho());
				stmt.setTimestamp(2, ts);
				stmt.execute();
				stmt.close();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			throw new RegraNegocioException("Nome para local já inserido!");
		}
	}
	
	public void editarLocal(LocalTrabalho localT, int idLocal) throws Exception {
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "UPDATE local_trabalho SET nome_local_trabalho = ? WHERE id_local_trabalho = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, localT.getNome_local_trabalho());
		stmt.setInt(2, idLocal);
		
		stmt.execute();
		stmt.close();
		
	}
	
	public void excluirLocal(int idLocal) throws Exception {
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "DELETE FROM local_trabalho WHERE id_local_trabalho = ?";
		
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
		
		String sql = "Select * from local_trabalho where id_local_trabalho = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, idLocal);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			localT = new LocalTrabalho();
			
			localT.setId_local_trabalho(rs.getInt("id_local_trabalho"));
			localT.setNome_local_trabalho(rs.getString("nome_local_trabalho"));
		}
		
		return localT;
		
	}
	
	public List<LocalTrabalho> listarLocal() throws Exception{
		List<LocalTrabalho> listarLocal = new ArrayList<LocalTrabalho>();
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "SELECT * FROM local_trabalho";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			LocalTrabalho localT = new LocalTrabalho();
			
			localT.setId_local_trabalho(rs.getInt("id_local_trabalho"));
			localT.setNome_local_trabalho(rs.getString("nome_local_trabalho"));
			
			listarLocal.add(localT);
		}
		
		return listarLocal;
	}

	public LocalTrabalho findByName(String name) throws Exception {
		
		LocalTrabalho localT = null;
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "Select * from local_trabalho where nome_local_trabalho = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, name);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			localT = new LocalTrabalho();
			
			localT.setNome_local_trabalho(rs.getString("nome_local_trabalho"));
		}
		
		return localT;
		
	}
}
