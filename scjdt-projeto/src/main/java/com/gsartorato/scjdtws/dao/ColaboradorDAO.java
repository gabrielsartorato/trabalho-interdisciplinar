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
		
		String sql = "INSERT INTO colaborador (nome_colaborador, data_nascimento, rg, cpf, email, carga_horaria, tipo_moradia, cep, rua, numero, complemento, bairro, cidade, estado, id_funcao, ativo, created_at) "
				+ "VALUES (?, ?::date, ?, ?, ?, ?::time, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ?)";
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, col.getNome_colaborador());
			stmt.setString(2, col.getData_nascimento());
			stmt.setString(3, col.getRg());
			stmt.setString(4, col.getCpf());
			stmt.setString(5, col.getEmail());
			stmt.setString(6, col.getCarga_horaria());
			stmt.setString(7, col.getTipo_moradia());
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
	
	public Colaborador buscarColaborador(int id_colaborador) throws Exception, SQLException {
		
		Connection conn = DBConfig.getConnection();
		
		Colaborador colaborador = null;
		
		String sql = "SELECT * FROM colaborador WHERE id_colaborador = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id_colaborador);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			colaborador = new Colaborador();
			
			colaborador.setId_colaborador(rs.getInt("id_colaborador"));
			colaborador.setNome_colaborador(rs.getString("nome_colaborador"));
			colaborador.setData_nascimento(rs.getTimestamp("data_nascimento").toString());
			colaborador.setRg(rs.getString("rg"));
			colaborador.setCpf(rs.getString("cpf"));
			colaborador.setEmail(rs.getString("email"));
			colaborador.setCarga_horaria(rs.getTime("carga_horaria").toString());
			colaborador.setTipo_moradia(rs.getString("tipo_moradia"));
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
	
	public void editColaborador(Colaborador col, int id_colaborador) throws Exception, SQLException {
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "UPDATE colaborador "
				+ "SET nome_colaborador = ?, "
				+ "data_nascimento = ?::date, "
				+ "rg = ?, cpf = ?, "
				+ "email = ?, carga_horaria = ?::time, "
				+ "tipo_moradia = ?, cep = ?, "
				+ "rua =?, numero = ?, complemento = ?, "
				+ "bairro = ?, cidade = ?, estado = ?, "
				+ "id_funcao = ?, ativo = ? "
				+ "WHERE id_colaborador = ? ";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, col.getNome_colaborador());
			stmt.setString(2, col.getData_nascimento());
			stmt.setString(3, col.getRg());
			stmt.setString(4, col.getCpf());
			stmt.setString(5, col.getEmail());
			stmt.setString(6, col.getCarga_horaria());
			stmt.setString(7, col.getTipo_moradia());
			stmt.setString(8, col.getCep());
			stmt.setString(9, col.getRua());
			stmt.setString(10, col.getNumero());
			stmt.setString(11, col.getComplemento());
			stmt.setString(12, col.getBairro());
			stmt.setString(13, col.getCidade());
			stmt.setString(14, col.getEstado());
			stmt.setInt(15, col.getId_funcao());
			stmt.setInt(16, col.getAtivo());
			stmt.setInt(17, id_colaborador);
			
			stmt.execute();
			stmt.close();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Colaborador> listColaborador() throws Exception {
		List<Colaborador> listarColaborador = new ArrayList<Colaborador>();
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "SELECT * FROM colaborador";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Colaborador col = new Colaborador();
				
				col.setId_colaborador(rs.getInt("id_colaborador"));
				col.setNome_colaborador(rs.getString("nome_colaborador"));
				col.setData_nascimento(rs.getTimestamp("data_nascimento").toString());
				col.setRg(rs.getString("rg"));
				col.setCpf(rs.getString("cpf"));
				col.setEmail(rs.getString("email"));
				col.setCarga_horaria(rs.getTime("carga_horaria").toString());
				col.setTipo_moradia(rs.getString("tipo_moradia"));
				col.setCep(rs.getString("cep"));
				col.setRua(rs.getString("rua"));
				col.setNumero(rs.getString("numero"));
				col.setComplemento(rs.getString("complemento"));
				col.setBairro(rs.getString("bairro"));
				col.setCidade(rs.getString("cidade"));
				col.setEstado(rs.getString("estado"));
				col.setId_funcao(rs.getInt("id_funcao"));
				col.setAtivo(rs.getInt("ativo"));
				
				listarColaborador.add(col);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return listarColaborador;
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
