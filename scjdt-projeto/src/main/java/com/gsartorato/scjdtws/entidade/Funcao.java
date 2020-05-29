package com.gsartorato.scjdtws.entidade;

import java.math.BigDecimal;

public class Funcao {
	
	private int id_funcao;
	
	private String nome_funcao;
	
	private BigDecimal salario_funcao;
	
	private String descricao_funcao;
	
	private int id_categoria;

	public int getId_funcao() {
		return id_funcao;
	}

	public void setId_funcao(int id_funcao) {
		this.id_funcao = id_funcao;
	}

	public String getNome_funcao() {
		return nome_funcao;
	}

	public void setNome_funcao(String nome_funcao) {
		this.nome_funcao = nome_funcao;
	}

	public BigDecimal getSalario_funcao() {
		return salario_funcao;
	}

	public void setSalario_funcao(BigDecimal salario_funcao) {
		this.salario_funcao = salario_funcao;
	}

	public String getDescricao_funcao() {
		return descricao_funcao;
	}

	public void setDescricao_funcao(String descricao_funcao) {
		this.descricao_funcao = descricao_funcao;
	}

	public int getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}

}
