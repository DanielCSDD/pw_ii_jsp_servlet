package br.com.projeto.model;

import java.util.Objects;

/**
 * Classe Java, que representa um objeto do tipo {@Link Contato}.
 * 
 * @author Daniel Luiz Coelho dos Santos.
 * @since 26/10-2024.
 */
public class Contato {

	// Declaração de atributos da classe.
	private Long id;
	private String nome;
	private String telefone;
	private String celular;

	// Declaração do construtor default vazio.
	public Contato() {
	}

	// Declaração do construtor com parâmetros.
	// É utilizado para inicialização do objeto com tendo valores.
	public Contato(Long id, String nome, String telefone, String celular) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.celular = celular;
	}

	// Declaração dos métodos Acessore e modificadores da classe..
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	/*
	 * Ele retorna um valor inteiro que representa o código hash do objeto,
	 * permitindo a rápida comparação e localização de objetos em coleções baseadas
	 * em hash, como HashMap, HashSet, e Hashtable.
	 * 
	 */
	@Override
	public int hashCode() {
		return Objects.hash(celular, id, nome, telefone);
	}

	/*
	 * Ele compara o conteúdo de dois objetos, em vez de suas referências na memória
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contato other = (Contato) obj;
		return Objects.equals(celular, other.celular) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(telefone, other.telefone);
	}
}
