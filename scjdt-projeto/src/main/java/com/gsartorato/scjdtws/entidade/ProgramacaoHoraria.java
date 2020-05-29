package com.gsartorato.scjdtws.entidade;

public class ProgramacaoHoraria {
	
	private int id_programacao;
	
	private String nome_programacao;
	
	private String inicio_horario;
	
	private String fim_horario;
	
	private String descricao;
	
	private int id_local_inicio;
	
	private int status_programacao;

	public int getId_programacao() {
		return id_programacao;
	}

	public void setId_programacao(int id_programacao) {
		this.id_programacao = id_programacao;
	}

	public String getNome_programacao() {
		return nome_programacao;
	}

	public void setNome_programacao(String nome_programacao) {
		this.nome_programacao = nome_programacao;
	}

	public String getInicio_horario() {
		return inicio_horario;
	}

	public void setInicio_horario(String inicio_horario) {
		this.inicio_horario = inicio_horario;
	}

	public String getFim_horario() {
		return fim_horario;
	}

	public void setFim_horario(String fim_horario) {
		this.fim_horario = fim_horario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getId_local_inicio() {
		return id_local_inicio;
	}

	public void setId_local_inicio(int id_local_inicio) {
		this.id_local_inicio = id_local_inicio;
	}

	public int getStatus_programacao() {
		return status_programacao;
	}

	public void setStatus_programacao(int status_programacao) {
		this.status_programacao = status_programacao;
	}
}
