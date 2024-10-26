package br.com.projeto.controller.service;

import java.util.ArrayList;

import br.com.projeto.model.Contato;
import br.com.projeto.model.dao.ContatoDAO;

public class ContatoService {
	
	/** 
	 * Instanciando a classe DAO. 
	 * */
	private ContatoDAO dao;
	
	/** 
	 * Construtor da classe service. 
	 */
	public ContatoService() {
		this.dao = new ContatoDAO();
	}

	/**
	 * Chamando o método da classe DAO, que irá gravar o registro no Banco de Dados.
	 *
	 * @param contato
	 */
	public void inserirContato(Contato contato){
		this.dao.insert(contato);
	}
	

	/**
	 * Chamando o método da classe DAO, que irá listar os registros do Banco de Dados.
	 *
	 * @return ArrayList<Contato>
	 */
	public ArrayList<Contato> listarContatos() {
		return this.dao.findAll();
	}
	
	/**
	 * Chamando o método da classe DAO, que irá selecionar um registro do Banco de Dados.
	 *
	 * @return Contato contato
	 */
	public Contato selecionarContato(Contato contato) {
		return this.dao.findById(contato);
	}
	
	/**
	 * Chamando o método da classe DAO, que irá atualizar um registro do Banco de Dados.
	 *
	 * @return Contato contato
	 */
	public void alterarContato(Contato contato) {
		this.dao.update(contato);
	}
	
	/**
	 * Chamando o método da classe DAO, que irá excluir um registro do Banco de Dados.
	 *
	 * @return Contato contato
	 */
	public void deletarContato(Contato contato) {
		this.dao.delete(contato);
	}

}
