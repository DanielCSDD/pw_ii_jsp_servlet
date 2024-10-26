package br.com.projeto.model.dao;

import java.util.ArrayList;

/**
 * Interface Java, que tem por objetivo, fornecer um conjunto de métodos para os
 * principais operações a serem realizados em um Banco de Dados.
 * 
 * @author Daniel Luiz Coelho dos Santos.
 * @since 26/10-2024.
 */
public interface IDAO<T> {

	/**
	 * Método da interface que será utilizado para salvar objetos no Banco de Dados.
	 * 
	 * @author Daniel Luiz Coelho dos Santos.
	 * 
	 * @param {@code Object}
	 */
	void insert(T t);

	/**
	 * Método da interface que será utilizado para listar objetos do Banco de Dados.
	 * 
	 * @author Daniel Luiz Coelho dos Santos.
	 * 
	 * @param {@code ArrayList}
	 */
	ArrayList<T> findAll();

	/**
	 * Método da interface que será utilizado para recuperar um objeto do Banco de
	 * Dados.
	 * 
	 * @author Daniel Luiz Coelho dos Santos.
	 * 
	 * @param {@code Object}
	 * @return {@code Object}
	 */
	T findById(T t);

	/**
	 * Método da interface que será utilizado para atualizar um objeto no Banco de
	 * Dados.
	 * 
	 * @author Daniel Luiz Coelho dos Santos.
	 * 
	 * @param {@code Object}
	 */
	void update(T t);

	/**
	 * Método da interface que será utilizado para remover um objeto do Banco de
	 * Dados.
	 * 
	 * @author Daniel Luiz Coelho dos Santos.
	 * 
	 * @param {@code Object}
	 */
	void delete(T t);
}
