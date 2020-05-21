package com.gsartorato.scjdtws.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConfigSingleton {
	
	private Connection conexao;
	private String url = "jdbc:postgresql://127.0.0.1:5432/scjdt"; 
	private String user = "postgres"; 
	private String password = "postgres";
	private static ConfigSingleton instacy;
	
	private ConfigSingleton() {
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver Ok!");
		}
		
		catch(Exception e) {
			System.out.println("Driver não encontrado, erro: " + e.getMessage());
		}
		
		try {
			this.conexao = DriverManager.getConnection(url, user, password);
			System.out.println("Conexão Ok!");
		}
		catch (Exception e) {
			System.out.println("Não foi possivel conectar, erro: " + e.getMessage());
		}
	}
	
	public static ConfigSingleton getInstancy() {
		if (instacy == null ) {
			instacy = new ConfigSingleton();
		}
		
		return instacy;
	}
	
	public Connection getConexao() {
		return this.conexao;
	}

}
