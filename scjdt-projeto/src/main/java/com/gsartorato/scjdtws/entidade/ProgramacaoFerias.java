package com.gsartorato.scjdtws.entidade;

public class ProgramacaoFerias {
	
	private int id_ferias;
	
	private int id_colaborador;
	
	private String data_inicio;
	
	private String data_fim;

	public int getId_ferias() {
		return id_ferias;
	}

	public void setId_ferias(int id_ferias) {
		this.id_ferias = id_ferias;
	}

	public int getId_colaborador() {
		return id_colaborador;
	}

	public void setId_colaborador(int id_colaborador) {
		this.id_colaborador = id_colaborador;
	}

	public String getData_inicio() {
		return data_inicio;
	}

	public void setData_inicio(String data_inicio) {
		this.data_inicio = data_inicio;
	}

	public String getData_fim() {
		return data_fim;
	}

	public void setData_fim(String data_fim) {
		this.data_fim = data_fim;
	}
	
}
