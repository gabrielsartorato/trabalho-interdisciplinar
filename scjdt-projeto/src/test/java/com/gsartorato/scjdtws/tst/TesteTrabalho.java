package com.gsartorato.scjdtws.tst;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.gsartorato.scjdtws.entidade.Colaborador;
import com.gsartorato.scjdtws.entidade.Usuario;


public class TesteTrabalho {
	
	@Test
	public void verificarSeTodososCamposEstaoPreenchidos() {
		
		Colaborador colaborador = new Colaborador();
		
		colaborador.setNome_colaborador("Teste");
		colaborador.setData_nascimento("07/08/1994");
		colaborador.setRg("338887772");
		colaborador.setCpf("44455566677");
		colaborador.setEmail("email@email.com");
		colaborador.setCarga_horaria("08:48");
		colaborador.setTipo_moradia("Modaria Qualquer");
		colaborador.setCep("Cep Qualquer");
		colaborador.setRua("Rua Qualquer");
		colaborador.setNumero("Numero Qualquer");
		colaborador.setComplemento("Complemento Qualquer");
		colaborador.setBairro("Bairro Qualquer");
		colaborador.setCidade("Cidade Qualquer");
		colaborador.setEstado("Estado Qualquer");
		colaborador.setId_funcao(1);
		colaborador.setAtivo(1);
				
		assertNotNull(colaborador.getNome_colaborador());
		assertNotNull(colaborador.getData_nascimento());
		assertNotNull(colaborador.getRg());
		assertNotNull(colaborador.getCpf());
		assertNotNull(colaborador.getEmail());
		assertNotNull(colaborador.getCarga_horaria());
		assertNotNull(colaborador.getTipo_moradia());
		assertNotNull(colaborador.getCep());
		assertNotNull(colaborador.getRua());
		assertNotNull(colaborador.getNumero());
		assertNotNull(colaborador.getComplemento());
		assertNotNull(colaborador.getBairro());
		assertNotNull(colaborador.getCidade());
		assertNotNull(colaborador.getEstado());
		assertNotNull(colaborador.getId_funcao());
		assertNotNull(colaborador.getAtivo());
	}
	
	@Test
	public void verificarSeDataDeNascimentoEValida() {
		
		Colaborador colaborador = new Colaborador();
		
		colaborador.setData_nascimento("07/08/1994");
		
		int validar = 0;
		
		String[] data = colaborador.getData_nascimento().split("/");
		
		if(Integer.parseInt(data[0]) > 31 || Integer.parseInt(data[0]) < 0) {
			validar = 1;
		}
		
		if(Integer.parseInt(data[1]) > 12 || Integer.parseInt(data[1]) < 0) {
			validar = 1;
		}
		
		assertEquals(0, validar);
	}
	
	@Test
	public void verificarSeCpftemOnzeDigitos() {
		Colaborador colaborador = new Colaborador();
		
		colaborador.setCpf("11111111111");
		
		int cpfTamanho = colaborador.getCpf().length();
		
		assertEquals(11, cpfTamanho);
	}
	
	@Test
	public void verificarSeNomeTemEstaComSobrenome() {
		Colaborador colaborador = new Colaborador();
		
		colaborador.setNome_colaborador("Gabriel Murilo Sartorato");
		
		String[] nomeConfere = colaborador.getNome_colaborador().split(" ");
		
		assertNotNull(nomeConfere[1]);
	}
	
	@Test
	public void verificarSeUsuarioESenhaBatem() {
		Usuario usuario = new Usuario();
		
		String usuarioCheck = "teste", usuarioSenha = "teste";
		
		usuario.setNome_usuario("teste");
		usuario.setSenha("teste");
		
		assertEquals(usuarioCheck, usuario.getNome_usuario());
		assertEquals(usuarioSenha, usuario.getSenha());
	}
	
}
