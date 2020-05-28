package com.gsartorato.scjdtws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import com.gsartorato.scjdtws.config.DBConfig;
import com.gsartorato.scjdtws.entidade.Colaborador;
import com.gsartorato.scjdtws.exception.RegraNegocioException;

public class ColaboradorDAO {
	
	DBConfig conn = new DBConfig();
	
	Colaborador colaborador = null;
	
	
	public void inserirColaborador(Colaborador col) throws Exception, SQLException {
		
		Connection conn = DBConfig.getConnection();
		
		Date date = new Date();
		Long created_at = date.getTime();
		Timestamp created = new Timestamp(created_at);

		if(checkIfExistActive(col) == 0) {
			throw new RegraNegocioException("Colaborador já existe ativo!");
		}
		
		String sql = "INSERT INTO colaborador (\"nomeColaborador\", \"dataNascimento\", rg, cpf, email, \"cargaHoraria\", \"tipoMoradia\", cep, rua, numero, complemento, bairro, cidade, estado, id_funcao, ativo, created_at) "
				+ "VALUES (?, ?::date, ?, ?, ?, ?::time, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ?)";
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, col.getNomeColaborador());
			stmt.setString(2, col.getDataNascimento());
			stmt.setString(3, col.getRg());
			stmt.setString(4, col.getCpf());
			stmt.setString(5, col.getEmail());
			stmt.setString(6, col.getCargaHoraria());
			stmt.setString(7, col.getTipoMoradia());
			stmt.setString(8, col.getCep());
			stmt.setString(9, col.getRua());
			stmt.setString(10, col.getNumero());
			stmt.setString(11, col.getComplemento());
			stmt.setString(12, col.getBairro());
			stmt.setString(13, col.getCidade());
			stmt.setString(14, col.getEstado());
			stmt.setInt(15, col.getId_funcao());
			stmt.setInt(16, col.getAtivo());
			stmt.setTimestamp(17, created);

			stmt.execute();
			stmt.close();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Colaborador buscarColaborador(int idColaborador) throws Exception, SQLException {
		
		Connection conn = DBConfig.getConnection();
		
		Colaborador colaborador = null;
		
		String sql = "SELECT * FROM colaborador WHERE \"idColaborador\" = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, idColaborador);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			colaborador = new Colaborador();
			
			colaborador.setIdColaborador(rs.getInt("idColaborador"));
			colaborador.setNomeColaborador(rs.getString("nomeColaborador"));
			colaborador.setDataNascimento(rs.getTimestamp("dataNascimento").toString());
			colaborador.setRg(rs.getString("rg"));
			colaborador.setCpf(rs.getString("cpf"));
			colaborador.setEmail(rs.getString("email"));
			colaborador.setCargaHoraria(rs.getTime("cargaHoraria").toString());
			colaborador.setTipoMoradia(rs.getString("tipoMoradia"));
			colaborador.setCep(rs.getString("cep"));
			colaborador.setRua(rs.getString("rua"));
			colaborador.setNumero(rs.getString("numero"));
			colaborador.setComplemento(rs.getString("complemento"));
			colaborador.setBairro(rs.getString("bairro"));
			colaborador.setCidade(rs.getString("cidade"));
			colaborador.setEstado(rs.getString("estado"));
			colaborador.setId_funcao(rs.getInt("id_funcao"));
			colaborador.setAtivo(rs.getInt("ativo"));
		}
		
		return colaborador;
	}
	
	public void editColaborador(Colaborador col, int idColaborador) throws Exception, SQLException {
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "UPDATE colaborador "
				+ "SET \"nomeColaborador\" = ?, "
				+ "\"dataNascimento\" = ?::date, "
				+ "rg = ?, cpf = ?, "
				+ "email = ?, \"cargaHoraria\" = ?::time, "
				+ "\"tipoMoradia\" = ?, cep = ?, "
				+ "rua =?, numero = ?, complemento = ?, "
				+ "bairro = ?, cidade = ?, estado = ?, "
				+ "id_funcao = ?, ativo = ? "
				+ "WHERE \"idColaborador\" = ? ";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, col.getNomeColaborador());
			stmt.setString(2, col.getDataNascimento());
			stmt.setString(3, col.getRg());
			stmt.setString(4, col.getCpf());
			stmt.setString(5, col.getEmail());
			stmt.setString(6, col.getCargaHoraria());
			stmt.setString(7, col.getTipoMoradia());
			stmt.setString(8, col.getCep());
			stmt.setString(9, col.getRua());
			stmt.setString(10, col.getNumero());
			stmt.setString(11, col.getComplemento());
			stmt.setString(12, col.getBairro());
			stmt.setString(13, col.getCidade());
			stmt.setString(14, col.getEstado());
			stmt.setInt(15, col.getId_funcao());
			stmt.setInt(16, col.getAtivo());
			stmt.setInt(17, idColaborador);
			
			stmt.execute();
			stmt.close();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int checkIfExistActive(Colaborador col) throws Exception, SQLException {
		
		Connection conn = DBConfig.getConnection();
		
		int ativo = 0;
		
		String sql = "SELECT * FROM colaborador WHERE rg = ? OR cpf = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, col.getRg());
		stmt.setString(2, col.getCpf());
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			ativo = rs.getInt("ativo");
		}
		
		if(ativo == 1) {
			return 0;
		}
		
		return 1;
	}
	
}
