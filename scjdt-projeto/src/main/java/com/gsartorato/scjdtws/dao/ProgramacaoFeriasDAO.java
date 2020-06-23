package com.gsartorato.scjdtws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;  

import com.gsartorato.scjdtws.config.DBConfig;
import com.gsartorato.scjdtws.entidade.EscalaPadrao;
import com.gsartorato.scjdtws.entidade.ProgramacaoFerias;
import com.gsartorato.scjdtws.exception.RegraNegocioException;

public class ProgramacaoFeriasDAO {
	
	DBConfig conn = new DBConfig();
	
	ProgramacaoFeriasDAO progFerDAO = null;
	
	public int inserirProgramacaoFerias(ProgramacaoFerias progFer) throws Exception, SQLException {
		
		int id_programacao = 0 ;
		
		Connection conn = DBConfig.getConnection();
		
		Date date = new Date();
		Long created_at = date.getTime();
		Timestamp created = new Timestamp(created_at);
		
		String sql = "INSERT INTO programacao_ferias (id_colaborador, data_inicio, data_fim, created_at) VALUES (?, ?::date, ?::date, ?) RETURNING id_ferias";
		
		try {
			ProgramacaoFerias progChe = new ProgramacaoFerias();
			
			progChe = verificarSeExiste(progFer.getId_colaborador());
			
			if(progChe != null) {
				//Convertendo datas do banco de dados
				String[] data_inicio_bd = progChe.getData_inicio().split(" ");
				String[] data_inicio_format = data_inicio_bd[0].split("-");
				String data_inicio = data_inicio_format[2]+"/"+data_inicio_format[1]+"/"+data_inicio_format[0];
				
				String[] data_fim_bd = progChe.getData_fim().split(" ");
				String[] data_fim_format = data_fim_bd[0].split("-");
				String data_fim = data_fim_format[2]+"/"+data_fim_format[1]+"/"+data_fim_format[0];
				
				Date dataInicioBanco = new SimpleDateFormat("dd/MM/yyyy").parse(data_inicio);
				Date dataFimBanco = new SimpleDateFormat("dd/MM/yyyy").parse(data_fim);
				
				//convertendo data do resource
				String[] data_inicio_resource = progFer.getData_inicio().split("-");
				String data_inicio_resource_format = data_inicio_resource[2]+"/"+data_inicio_resource[1]+"/"+data_inicio_resource[0];
				String[] data_fim_resource = progFer.getData_fim().split("-");
				String data_fim_resource_format = data_fim_resource[2]+"/"+data_fim_resource[1]+"/"+data_fim_resource[0];
				
				Date dataInicioResource = new SimpleDateFormat("dd/MM/yyyy").parse(data_inicio_resource_format);
				Date dataFimResource = new SimpleDateFormat("dd/MM/yyyy").parse(data_fim_resource_format);
				
				if(dataInicioBanco.equals(dataInicioResource) || dataInicioResource.before(dataInicioBanco)) {
					throw new RegraNegocioException("Férias não pode ser menor que a data anterior das últimas férias");
				}
			}

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, progFer.getId_colaborador());
			stmt.setString(2, progFer.getData_inicio());
			stmt.setString(3, progFer.getData_fim());
			stmt.setTimestamp(4, created);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				id_programacao = rs.getInt("id_ferias");
			}
			
			return id_programacao;
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public ProgramacaoFerias verificarSeExiste(int id_colaborador) throws Exception, SQLException {
		
		Connection conn = DBConfig.getConnection();
		
		ProgramacaoFerias progF = null;
		
		String sql = "SELECT * FROM programacao_ferias WHERE id_colaborador = ? ORDER BY id_ferias DESC LIMIT 1";
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, id_colaborador);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				progF = new ProgramacaoFerias();
				progF.setId_ferias(rs.getInt("id_ferias"));
				progF.setId_colaborador(rs.getInt("id_colaborador"));
				progF.setData_inicio(rs.getTimestamp("data_inicio").toString());
				progF .setData_fim(rs.getTimestamp("data_fim").toString());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return progF;
	}

}
