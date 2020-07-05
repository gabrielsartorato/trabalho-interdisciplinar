package com.gsartorato.scjdtws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;  

import com.gsartorato.scjdtws.config.DBConfig;
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
				
				Date dataInicioBanco = new SimpleDateFormat("dd/MM/yyyy").parse(data_inicio);

				//convertendo data do resource
				String[] data_inicio_resource = progFer.getData_inicio().split("-");
				String data_inicio_resource_format = data_inicio_resource[2]+"/"+data_inicio_resource[1]+"/"+data_inicio_resource[0];
				String[] data_fim_resource = progFer.getData_fim().split("-");
				String data_fim_resource_format = data_fim_resource[2]+"/"+data_fim_resource[1]+"/"+data_fim_resource[0];
				
				Date dataInicioResource = new SimpleDateFormat("dd/MM/yyyy").parse(data_inicio_resource_format);
				Date dataFimResource = new SimpleDateFormat("dd/MM/yyyy").parse(data_fim_resource_format);
				
				System.out.println(dataInicioResource);
				System.out.println(dataFimResource);
				if(dataInicioBanco.equals(dataInicioResource) || dataInicioResource.before(dataInicioBanco)) {
					throw new RegraNegocioException("Férias não pode ser menor que a data anterior das últimas férias");
				}
				
				if(dataFimResource.before(dataInicioResource)) {
					throw new RegraNegocioException("Data inicial não pode ser menor que a data final");
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
	
	public void alterarProgramacaoFerias(ProgramacaoFerias progFerias, int id_ferias) throws Exception, SQLException, RegraNegocioException {
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "UPDATE programacao_ferias SET id_colaborador = ?, data_inicio = ?::date, data_fim = ?::date WHERE id_ferias = ?";
		
		ProgramacaoFerias progChek = new ProgramacaoFerias();
		
		progChek = verificarSeExiste(progFerias.getId_colaborador());
		
		String dateNowString = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
		
		String[] data_inicio_bd = progChek.getData_inicio().split(" ");
		String[] data_inicio_format = data_inicio_bd[0].split("-");
		String data_inicio = data_inicio_format[2]+"/"+data_inicio_format[1]+"/"+data_inicio_format[0];
		
		Date dateNowDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateNowString);
		Date dateInicioBd = new SimpleDateFormat("dd/MM/yyyy").parse(data_inicio);
		
		if(dateNowDate.after(dateInicioBd)) {
			throw new RegraNegocioException("Programação de férias não pode ser editada após o inicio!");
		}
		
		if(progFerias.getData_inicio().equals("") || progFerias.getData_fim().equals("")) {
			throw new RegraNegocioException("Preencha todos os campos obrigatórios!");
		}
		
		try {

			if(progChek != null) {
				
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, progFerias.getId_colaborador());
				stmt.setString(2, progFerias.getData_inicio());
				stmt.setString(3, progFerias.getData_fim());
				stmt.setInt(4, id_ferias);
				
				stmt.execute();
				stmt.close();
				
			}
			
		} catch (RegraNegocioException e) {
			e.printStackTrace();
		}
	}
	
	public void excluirProgramacaoFerias(int id_ferias) throws Exception, SQLException {
		
		Connection conn = DBConfig.getConnection();
		
		ProgramacaoFerias progChek = new ProgramacaoFerias();
		
		progChek = verificarSeExiste(id_ferias);
				
		String[] data_inicio_bd = progChek.getData_inicio().split(" ");
		String[] data_inicio_format = data_inicio_bd[0].split("-");
		String data_inicio = data_inicio_format[2]+"/"+data_inicio_format[1]+"/"+data_inicio_format[0];
		
		String dateNowString = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
		
		Date dateNowDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateNowString);
		Date dateInicioBd = new SimpleDateFormat("dd/MM/yyyy").parse(data_inicio);
		
		if(dateNowDate.after(dateInicioBd)) {
			throw new RegraNegocioException("Programação de férias não pode ser exclúida após o inicio ou termino!");
		}
		
		String sql = "DELETE FROM programacao_ferias WHERE id_ferias = ?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id_ferias);
			
			stmt.execute();
			stmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
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
	
	public ProgramacaoFerias buscarById(int id_ferias) throws Exception, SQLException {
		
		Connection conn = DBConfig.getConnection();
		
		ProgramacaoFerias progF = null;
		
		String sql = "SELECT * FROM programacao_ferias WHERE id_ferias = ?";
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, id_ferias);
			
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
	
	public List<ProgramacaoFerias> listarProgramacaoFerias() throws Exception, SQLException {
		List<ProgramacaoFerias> listarProgramacao = new ArrayList<ProgramacaoFerias>();
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "SELECT * FROM programacao_ferias";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			ProgramacaoFerias progFerias = new ProgramacaoFerias();
			
			progFerias.setId_ferias(rs.getInt("id_ferias"));
			progFerias.setId_colaborador(rs.getInt("id_colaborador"));
			progFerias.setData_inicio(rs.getTimestamp("data_inicio").toString());
			progFerias.setData_fim(rs.getTimestamp("data_fim").toString());
			
			listarProgramacao.add(progFerias);
			
		}
		
		return listarProgramacao;
	}

}
