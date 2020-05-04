package com.gsartorato.scjdtws.entidade;

import java.math.BigDecimal;

public class CategoriaFuncao {
	
	private int idCategoria;
	
	private String nomeCategoria;
	
	private BigDecimal salarioCategoria;
	
	private String descricaoCategoria;

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public BigDecimal getSalarioCategoria() {
		return salarioCategoria;
	}

	public void setSalarioCategoria(BigDecimal salarioCategoria) {
		this.salarioCategoria = salarioCategoria;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

}
