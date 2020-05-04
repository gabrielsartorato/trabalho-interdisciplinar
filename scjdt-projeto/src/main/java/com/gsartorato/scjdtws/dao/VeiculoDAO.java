package com.gsartorato.scjdtws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gsartorato.scjdtws.config.DBConfig;
import com.gsartorato.scjdtws.entidade.Veiculo;
import com.gsartorato.scjdtws.exception.RegraNegocioException;


public class VeiculoDAO {
	
	public List<Veiculo> listarVeiculos() throws Exception{
		List<Veiculo> listaVeiculo = new ArrayList<Veiculo>();
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "SELECT * FROM CAD_VEICULO";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Veiculo veic = new Veiculo();
			
			veic.setCOD_VEICULO(rs.getInt("COD_VEICULO"));
			veic.setPREFIXOVEIC(rs.getString("PREFIXOVEIC"));
			veic.setQTDASSENTOS(rs.getInt("QTDASSENTOS"));
			veic.setPLACAVEIC(rs.getString("PLACAVEIC"));
			veic.setOBSVEIC(rs.getString("OBSVEIC"));
			
			listaVeiculo.add(veic);
			
		}
		 return listaVeiculo;
	}
	
	public void addVeic(Veiculo veic) throws Exception {
		
		Connection conexao = DBConfig.getConnection();
		
		int verificaPrefixo = validarPrefixo(veic.getPREFIXOVEIC());
		
		if(verificaPrefixo == 0) {
			throw new RegraNegocioException("Prefixo já cadastrado");
		}
		
		int verificaPlaca = validarPlaca(veic.getPLACAVEIC());
		
		if(verificaPlaca == 0) {
			throw new RegraNegocioException("Placa já existente");
		}
	
		if(verificaPrefixo == 1 && verificaPlaca == 1) {
			String sql = "INSERT INTO CAD_VEICULO(PREFIXOVEIC, QTDASSENTOS, PLACAVEIC, OBSVEIC) VALUES(?, ?, ?, ?)";
	
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, veic.getPREFIXOVEIC());
			statement.setLong(2, veic.getQTDASSENTOS());
			statement.setString(3, veic.getPLACAVEIC());
			statement.setString(4, veic.getOBSVEIC());
			statement.execute();
		}
		
	}
	
	public Veiculo buscarPorId(int cod_veic) throws ClassNotFoundException, Exception{
		
		Veiculo veic = null;
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "SELECT * FROM CAD_VEICULO WHERE COD_VEICULO = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, cod_veic);
		ResultSet rs = stmt.executeQuery(); 
		
		if(rs.next()) {
			
			veic = new Veiculo();
			
			veic.setCOD_VEICULO(rs.getInt("COD_VEICULO"));
			veic.setPREFIXOVEIC(rs.getString("PREFIXOVEIC"));
			veic.setQTDASSENTOS(rs.getInt("QTDASSENTOS"));
			veic.setPLACAVEIC(rs.getString("PLACAVEIC"));
			veic.setOBSVEIC(rs.getString("PLACAVEIC"));
			
		}
		
		return veic;
		
	}
	
	public void editarVeiculo(Veiculo veic, int cod_veic) throws Exception {
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "UPDATE CAD_VEICULO SET PREFIXOVEIC = ?, "
				+ "QTDASSENTOS = ?, "
				+ "PLACAVEIC = ?, "
				+ "OBSVEIC = ? "
				+ "WHERE COD_VEICULO = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, veic.getPREFIXOVEIC());
		stmt.setLong(2, veic.getQTDASSENTOS());
		stmt.setString(3, veic.getPLACAVEIC());
		stmt.setString(4, veic.getOBSVEIC());
		stmt.setInt(5, cod_veic);
		
		stmt.execute();
		
	}
	
	public void excluirVeiculo(int codVeic) throws Exception {
		Connection conn = DBConfig.getConnection();
		
		String sql = "DELETE FROM CAD_VEICULO WHERE COD_VEICULO = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, codVeic);
		
		stmt.execute();
		
		
	}
	
	public Veiculo buscarPorPrefixo(String prefixo) throws Exception {
		
		Veiculo veic = null;
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "SELECT PREFIXOVEIC FROM CAD_VEICULO WHERE PREFIXOVEIC = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, prefixo);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			veic = new Veiculo();
			
			veic.setCOD_VEICULO(rs.getInt("COD_VEICULO"));
			veic.setPREFIXOVEIC(rs.getString("PREFIXOVEIC"));
			veic.setQTDASSENTOS(rs.getInt("QTDASSENTOS"));
			veic.setPLACAVEIC(rs.getString("PLACAVEIC"));
			veic.setOBSVEIC(rs.getString("OBSVEIC"));
		}
		
		return veic;
		
	}
	
	
	//método para validar se prefixo do veiculo já existe
	public int validarPrefixo(String prefixo) throws Exception {
		Veiculo veic = null;
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "SELECT PREFIXOVEIC FROM CAD_VEICULO WHERE PREFIXOVEIC = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, prefixo);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			veic = new Veiculo();
			
			veic.setPREFIXOVEIC(rs.getString("PREFIXOVEIC"));
			
			if(veic.getPREFIXOVEIC() == null || veic.getPREFIXOVEIC() == "") {
				return 1;
			}else
				return 0;
		}		
		
		return 1;
	}
	
	//método para verificar se placa já está cadastrada
	public int validarPlaca(String placa) throws Exception {
		
		Veiculo veic = null;
		
		Connection conn = DBConfig.getConnection();
		
		String sql = "SELECT PLACAVEIC FROM CAD_VEICULO WHERE PLACAVEIC = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, placa);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			veic = new Veiculo();
			
			veic.setPLACAVEIC(rs.getString("PLACAVEIC"));
			
			if(veic.getPLACAVEIC() == null || veic.getPLACAVEIC() == "") {
				return 1;
			}else
				return 0;
		}
		
		return 1;
	}

}
