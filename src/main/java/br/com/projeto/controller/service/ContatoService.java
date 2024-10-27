package br.com.projeto.controller.service;

import java.util.ArrayList;

import br.com.projeto.model.Contato;
import br.com.projeto.model.dao.ContatoDAO;

/**
 * A classe {@code ContatoService} é uma classe que irá prover recursos de chamada ao Banco de Dados.
 * Bem, como irá implementar regras de negócio.
 * 
 * @author Daniel Luiz Coelho dos Santos.
 * @since 26/10-2024.
 */
public class ContatoService {
	
	// Declaração de atributos da classe.
	private ContatoDAO dao;
	
	/*
	 * Contrutor da classe. Será onde iremos instanciar outras classes. Isso
	 * ocorrerá quando a classe ContatoDAO for instanciado.
	 */
	public ContatoService() {
		this.dao = new ContatoDAO();
	}

	/**
	 * Chamando o método da classe {@link ContatoDAO}, que irá gravar o registro no Banco de Dados.
	 *
	 * @author Daniel Luiz Coelho dos Santos.
	 *
	 * @param contato
	 */
	public void inserirContato(Contato contato){
		this.dao.insert(contato);
	}
	

	/**
	 * Chamando o método da classe {@link ContatoDAO}, que irá listar os registros do Banco de Dados.
	 *
	 * @author Daniel Luiz Coelho dos Santos.
	 * 
	 * @return {@code ArrayList}
	 */
	public ArrayList<Contato> listarContatos() {
		return this.dao.findAll();
	}
	
	/**
	 * Chamando o método da classe {@link ContatoDAO}, que irá selecionar um registro do Banco de Dados.
	 * 
	 * @author Daniel Luiz Coelho dos Santos.
	 * 
	 * @param {@code Contato}
	 * @return {@code Contato}
	 */
	public Contato selecionarContato(Contato contato) {
		return this.dao.findById(contato);
	}
	
	/**
	 * Chamando o método da classe {@link ContatoDAO}, que irá atualizar um registro do Banco de Dados.
	 *
	 * @author Daniel Luiz Coelho dos Santos.
	 * 
	 * @param {@code Contato}
	 */
	public void alterarContato(Contato contato) {
		this.dao.update(contato);
	}
	
	/**
	 * Chamando o método da classe {@link ContatoDAO}, que irá excluir um registro do Banco de Dados.
	 *
	 * @param {@code Contato}
	 */
	public void deletarContato(Contato contato) {
		this.dao.delete(contato);
	}

}
