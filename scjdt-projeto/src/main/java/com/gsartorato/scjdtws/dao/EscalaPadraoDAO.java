package com.gsartorato.scjdtws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.gsartorato.scjdtws.config.DBConfig;
import com.gsartorato.scjdtws.entidade.EscalaPadrao;

public class EscalaPadraoDAO {
	
	DBConfig conn = new DBConfig();
	
	EscalaPadrao escala = null;
	
	public void inserirEscala(EscalaPadrao escPadrao) throws Exception, SQLException {
		
		int id_escala = 0 ;
		
		Connection conn = DBConfig.getConnection();
		
		Date date = new Date();
		Long created_at = date.getTime();
		Timestamp created = new Timestamp(created_at);
		
		String sql = "INSERT INTO escala_padrao (id_programacao, id_colaborador, created_at) VALUES (?, ?, ?) RETURNING id_escala";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, escPadrao.getId_programacao());
			stmt.setInt(2, escPadrao.getId_colaborador());
			stmt.setTimestamp(3, created);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				id_escala = rs.getInt("id_escala");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
