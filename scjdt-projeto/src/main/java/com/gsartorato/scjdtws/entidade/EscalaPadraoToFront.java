package com.gsartorato.scjdtws.entidade;

public class EscalaPadraoToFront {
	private int id_escala;
	
	private int id_programacao;
	
	private String nome_programacao;
	
	private int id_colaborador;
	
	private int status;
	
	private String inicio_horario;
	
	private String fim_horario;
	
	private String nome_colaborador;
	
	public String getNome_colaborador() {
		return nome_colaborador;
	}

	public void setNome_colaborador(String nome_colaborador) {
		this.nome_colaborador = nome_colaborador;
	}

	public String getNome_programacao() {
		return nome_programacao;
	}

	public void setNome_programacao(String nome_programacao) {
		this.nome_programacao = nome_programacao;
	}

	public int getId_escala() {
		return id_escala;
	}

	public void setId_escala(int id_escala) {
		this.id_escala = id_escala;
	}

	public int getId_programacao() {
		return id_programacao;
	}

	public void setId_programacao(int id_programacao) {
		this.id_programacao = id_programacao;
	}

	public int getId_colaborador() {
		return id_colaborador;
	}

	public void setId_colaborador(int id_colaborador) {
		this.id_colaborador = id_colaborador;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

}
