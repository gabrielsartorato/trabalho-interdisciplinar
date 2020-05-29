package com.gsartorato.scjdtws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.gsartorato.scjdtws.config.DBConfig;
import com.gsartorato.scjdtws.entidade.ProgramacaoHoraria;

public class ProgramacaoHorariaDAO {
	
	DBConfig conn = new DBConfig();
	
	ProgramacaoHorariaDAO progDAO = null;
	
	public int inserirProgramacao(ProgramacaoHoraria progHor) throws Exception, SQLException {
		
		Date date = new Date();
		long time = date.getTime();
		Timestamp created = new Timestamp(time);
		
		int id = 0;
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "INSERT INTO programacao_horaria (nome_programacao, inicio_horario, fim_horario, descricao, id_local_inicio, status_programacao, created_at) "
				+ "VALUES (?, ?::time, ?::time, ?, ?, ?, ?) RETURNING id_programacao";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, progHor.getNome_programacao());
			stmt.setString(2, progHor.getInicio_horario());
			stmt.setString(3, progHor.getFim_horario());
			stmt.setString(4, progHor.getDescricao());
			stmt.setInt(5, progHor.getId_local_inicio());
			stmt.setInt(6, progHor.getStatus_programacao());
			stmt.setTimestamp(7, created);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				id = rs.getRow();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return id;
	}

}
