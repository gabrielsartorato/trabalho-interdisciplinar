package com.gsartorato.scjdtws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gsartorato.scjdtws.config.DBConfig;
import com.gsartorato.scjdtws.entidade.EscalaPadrao;
import com.gsartorato.scjdtws.entidade.ProgramacaoHoraria;
import com.gsartorato.scjdtws.exception.RegraNegocioException;

public class EscalaPadraoDAO {
	
	DBConfig conn = new DBConfig();
	
	EscalaPadrao escala = null;
	
	public int inserirEscala(EscalaPadrao escPadrao) throws Exception, SQLException {
		
		Connection conn = DBConfig.getConnection();
		
		int id_escala = 0;
		
		Date date = new Date();
		Long created_at = date.getTime();
		Timestamp created = new Timestamp(created_at);
		
		int resultadoVerificacao = 0;
		
		resultadoVerificacao = verificarSeExisteEscalaNoMesmoHorario(escPadrao.getId_colaborador(), escPadrao.getId_programacao());
		
		if(resultadoVerificacao == 0) {
			throw new RegraNegocioException("Conflito de horário");
		}
		
		String sql = "INSERT INTO escala_padrao (id_programacao, id_colaborador, status, created_at) VALUES (?, ?, 1, ?) RETURNING id_escala";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, escPadrao.getId_programacao());
			stmt.setInt(2, escPadrao.getId_colaborador());
			stmt.setTimestamp(3, created);
			
			ResultSet rs = stmt.executeQuery();

			if(rs.next()) {
				id_escala = rs.getInt("id_escala");
			}
			return id_escala;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public void editarEscalaPadrao(EscalaPadrao escPadrao, int id_escala) throws Exception, SQLException {
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "UPDATE escala_padrao SET status = ? WHERE id_escala = ?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, escPadrao.getStatus());
			stmt.setInt(2, id_escala);
			
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public int verificarSeExisteEscalaNoMesmoHorario(int id_colaborador, int id_programacao) throws Exception, SQLException {
		List<ProgramacaoHoraria> listProgramacaoHoraria = new ArrayList<ProgramacaoHoraria>();
		ProgramacaoHoraria progHora = null;
		ProgramacaoHoraria progHorariaId = null;
		
		Connection conn = DBConfig.getConnection();
		
		String sqlDataProgramacao = "SELECT inicio_horario, fim_horario FROM programacao_horaria WHERE id_programacao = ?";
		
		PreparedStatement prepareProgHoraria = conn.prepareStatement(sqlDataProgramacao);
		prepareProgHoraria.setInt(1, id_programacao);
		
		ResultSet rsProg = prepareProgHoraria.executeQuery();
		
		if(rsProg.next()) {
			progHorariaId = new ProgramacaoHoraria();
			
			progHorariaId.setInicio_horario(rsProg.getTime("inicio_horario").toString());
			progHorariaId.setFim_horario(rsProg.getTime("fim_horario").toString());
			
		}
		
		String sql = "SELECT id_programacao FROM escala_padrao WHERE id_colaborador = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id_colaborador);
		
		ResultSet rs = stmt.executeQuery();
		
		String sql2 = "";
		while(rs.next()) {
			sql2 = "SELECT inicio_horario, fim_horario FROM programacao_horaria WHERE id_programacao = ?";
			
			PreparedStatement stmt2 = conn.prepareStatement(sql2);
			stmt2.setInt(1, rs.getInt("id_programacao"));
			
			ResultSet rs2 = stmt2.executeQuery();
			
			while(rs2.next()) {
				progHora = new ProgramacaoHoraria();
				
				progHora.setInicio_horario(rs2.getTime("inicio_horario").toString());
				progHora.setFim_horario(rs2.getTime("fim_horario").toString());
				
				listProgramacaoHoraria.add(progHora);
			}
			
		}
		
		Date horarioProgramcaoInicioString = new SimpleDateFormat("HH:mm").parse(progHorariaId.getInicio_horario());
		Time horarioProgramcaoInicioTime = new Time(horarioProgramcaoInicioString.getTime());
		
		Date horarioProgramcaoFimString = new SimpleDateFormat("HH:mm").parse(progHorariaId.getFim_horario());
		Time horarioProgramcaoFimTime = new Time(horarioProgramcaoFimString.getTime());
		
		for(ProgramacaoHoraria p : listProgramacaoHoraria) {
			
			Date horaInicioString = new SimpleDateFormat("HH:mm").parse(p.getInicio_horario());
			Time horaInicioTime = new Time(horaInicioString.getTime());
			
			Date horaFimString = new SimpleDateFormat("HH:mm").parse(p.getFim_horario());
			Time horaFimTime = new Time(horaFimString.getTime());
			
			if(horaInicioTime.equals(horarioProgramcaoInicioTime)) {
				return 0;
			}
			
			if((horaInicioTime.compareTo(horarioProgramcaoInicioTime) < 0) && (horaFimTime.compareTo(horarioProgramcaoInicioTime) > 0)) {
				return 0;
			}
			
			if(horarioProgramcaoInicioTime.before(horaFimTime) && (horarioProgramcaoFimTime.after(horaInicioTime))) {
				return 0;
			}
		}
		
		return 1;
	}

}
