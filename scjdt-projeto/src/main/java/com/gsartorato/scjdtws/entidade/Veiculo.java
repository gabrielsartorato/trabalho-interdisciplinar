package com.gsartorato.scjdtws.entidade;


public class Veiculo {
	
	private int COD_VEICULO;
	
	private String PREFIXOVEIC;
	
	private int QTDASSENTOS;
	
	private String PLACAVEIC;
	
	private String OBSVEIC;

	public int getCOD_VEICULO() {
		return COD_VEICULO;
	}

	public void setCOD_VEICULO(int cOD_VEICULO) {
		COD_VEICULO = cOD_VEICULO;
	}

	public String getPREFIXOVEIC() {
		
		return PREFIXOVEIC;
	}

	public void setPREFIXOVEIC(String pREFIXOVEIC) {

		PREFIXOVEIC = pREFIXOVEIC;
	}

	public int getQTDASSENTOS() {
		return QTDASSENTOS;
	}

	public void setQTDASSENTOS(int qTDASSENTOS) {
		QTDASSENTOS = qTDASSENTOS;
	}

	public String getPLACAVEIC() {
		return PLACAVEIC;
	}

	public void setPLACAVEIC(String pLACAVEIC) {
		PLACAVEIC = pLACAVEIC;
	}

	public String getOBSVEIC() {
		return OBSVEIC;
	}

	public void setOBSVEIC(String oBSVEIC) {
		OBSVEIC = oBSVEIC;
	}

}
