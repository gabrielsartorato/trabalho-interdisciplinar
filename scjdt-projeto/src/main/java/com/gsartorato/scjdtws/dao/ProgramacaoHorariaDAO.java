package com.gsartorato.scjdtws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
				id = rs.getInt("id_programacao");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return id;
	}
	
	public String editarProgramacao(int id_programacao, ProgramacaoHoraria progHor) throws SQLException, Exception {
		
		Connection conn = DBConfig.getConnection();
		
		String msg = "";
		
		String sql = "UPDATE programacao_horaria "
				+ "SET nome_programacao = ?, "
				+ "inicio_horario = ?::time, "
				+ "fim_horario = ?::time, "
				+ "descricao = ?, "
				+ "id_local_inicio = ?, "
				+ "status_programacao = ? "
				+ "WHERE id_programacao = ?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, progHor.getNome_programacao());
			stmt.setString(2, progHor.getInicio_horario());
			stmt.setString(3, progHor.getFim_horario());
			stmt.setString(4, progHor.getDescricao());
			stmt.setInt(5, progHor.getId_local_inicio());
			stmt.setInt(6, progHor.getStatus_programacao());
			stmt.setInt(7, id_programacao);
			
			stmt.execute();
			stmt.close();
			
			msg = "Programação editada com sucesso";
		}
		catch (Exception e) {
			e.printStackTrace();
			msg = "Não foi possivel alterar a programação, tente novamente";
		}
		
		return msg;
		
	}
	
	public ProgramacaoHoraria findById(int id_programacao) throws Exception, SQLException {
		
		ProgramacaoHoraria progH = null;
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "SELECT * FROM programacao_horaria WHERE id_programacao = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id_programacao);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			progH = new ProgramacaoHoraria();
			progH.setId_programacao(rs.getInt("id_programacao"));
			progH.setNome_programacao(rs.getString("nome_programacao"));
			progH.setInicio_horario(rs.getTime("inicio_horario").toString());
			progH.setFim_horario(rs.getTime("fim_horario").toString());
			progH.setDescricao(rs.getString("descricao"));
			progH.setId_local_inicio(rs.getInt("id_local_inicio"));
			progH.setStatus_programacao(rs.getInt("status_programacao"));
			
		}
		
		return progH;
	}
	
	public String excluirProgramacao(int id_programacao) throws Exception, SQLException {
		
		Connection conn = DBConfig.getConnection();
		String msg = "";
		
		String sql = "DELETE FROM programacao_horaria WHERE id_programacao = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id_programacao);
			
			stmt.execute();
			stmt.close();
			
			msg = "Programação excluída com sucesso!";
		}
		catch (Exception e) {
			e.printStackTrace();
			msg = "Não foi possível excluir a programação, tente novamente";
		}
		
		return msg;
	}
	
	public List<ProgramacaoHoraria> listarProgramacao() throws Exception, SQLException {
		List<ProgramacaoHoraria> listarProgramacao = new ArrayList<ProgramacaoHoraria>();
		
		Connection conn = DBConfig.getConnection();
		ProgramacaoHoraria progH = null;
		
		String sql = "SELECT * FROM programacao_horaria";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			
			while(rs.next()) {
				progH = new ProgramacaoHoraria();
				progH.setId_programacao(rs.getInt("id_programacao"));
				progH.setNome_programacao(rs.getString("nome_programacao"));
				progH.setInicio_horario(rs.getTime("inicio_horario").toString());
				progH.setFim_horario(rs.getTime("fim_horario").toString());
				progH.setDescricao(rs.getString("descricao"));
				progH.setId_local_inicio(rs.getInt("id_local_inicio"));
				progH.setStatus_programacao(rs.getInt("status_programacao"));
				
				listarProgramacao.add(progH);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return listarProgramacao;
	}

}
