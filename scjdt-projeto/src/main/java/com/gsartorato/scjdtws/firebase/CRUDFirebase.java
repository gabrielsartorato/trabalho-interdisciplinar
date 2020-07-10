package com.gsartorato.scjdtws.firebase;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.gsartorato.scjdtws.entidade.Colaborador;

public class CRUDFirebase {
	
	private static Firestore db = null;
	
	public CRUDFirebase() {
		ConexaoFirebase conexionFirebase = new ConexaoFirebase();
		db = conexionFirebase.iniciarFirebase();
	}
	
	public boolean adicionarFirebase(Colaborador colaborador) {
		
		boolean key = false;
		
		Map<String, Object> docColaborador = new HashMap<>();
		docColaborador.put("nome", colaborador.getNome_colaborador());
		docColaborador.put("data_nascimento", colaborador.getData_nascimento());
		docColaborador.put("rg", colaborador.getRg());
		docColaborador.put("cpf", colaborador.getCpf());
		docColaborador.put("carga_horaria", colaborador.getCarga_horaria());
		docColaborador.put("email", colaborador.getEmail());
		docColaborador.put("cep", colaborador.getCep());
		docColaborador.put("rua", colaborador.getRua());
		docColaborador.put("numero", colaborador.getNumero());
		docColaborador.put("complemento", colaborador.getComplemento());
		docColaborador.put("tipo_moradia", colaborador.getTipo_moradia());
		docColaborador.put("bairro", colaborador.getBairro());
		docColaborador.put("cidade", colaborador.getCidade());
		docColaborador.put("estado", colaborador.getEstado());
		docColaborador.put("status", colaborador.getAtivo());

		ApiFuture<WriteResult> future = db.collection("colaborador").document(UUID.randomUUID().toString()).set(docColaborador);
		
		try {
			System.out.println("Update time : " + future.get().getUpdateTime());
			key = true;
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return key;
	}

}
