package br.com.projeto.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.projeto.model.Contato;

/**
 * Classe Java, que tem por objetivo, implementar os métodos da interface IDAO.
 * Bem, como, realizar as ações de: {@link #insert(Contato)};
 * {@link #findAll()}; {@link #findById(Contato)}; {@link #update(Contato)};
 * {@link #delete(Contato)};
 * 
 * @author Daniel Luiz Coelho dos Santos.
 * @since 26/10-2024.
 */
public class ContatoDAO implements IDAO<Contato> {

	// Declaração de atributos da classe.
	private DAOFactory connetionDataBase;

	// Declaração de constantes contendo comandos SQL para realizar ações no Banco
	// de Dados.
	private final String SQL_INSERT = "INSERT INTO contatos (nome,telefone,celular) VALUES (?,?,?)";
	private final String SQL_ORDER_BY_NOME = "SELECT * FROM contatos";
	private final String SQL_SELECT_BY_ID = "SELECT * FROM contatos WHERE id = ?";
	private final String SQL_UPDATE = "UPDATE contatos SET nome=?,telefone=?,celular=? WHERE id=?";
	private final String SQL_DELETE = "DELETE FROM contatos WHERE id=?";

	/*
	 * Contrutor da classe. Será onde iremos instanciar outras classes. Isso
	 * ocorrerá quando a classe ContatoDAO for instanciado.
	 */
	public ContatoDAO() {
		this.connetionDataBase = new DAOFactory();
	}

	/**
	 * Método que irá recuperar a conexão com o Banco de Dados. A conexão será
	 * fornecida pela classe @DAOFactory.
	 *
	 * @author Daniel Luiz Coelho dos Santos.
	 * 
	 * @return {@link Connection}
	 */
	public Connection getConnection() {
		return this.connetionDataBase.getConnection();
	}

	/**
	 * Método que irá inserir um objeto do tipo {@link Contato}.
	 *
	 * @author Daniel Luiz Coelho dos Santos.
	 * 
	 * @param {@code Contato}
	 */
	@Override
	public void insert(Contato contato) {
		try {
			Connection con = getConnection();
			PreparedStatement pst = con.prepareStatement(SQL_INSERT);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getTelefone());
			pst.setString(3, contato.getCelular());
			pst.executeUpdate();
			closeConnection(con);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 * Método que irá retornar uma lista de objeto do tipo {@link Contato}.
	 *
	 * @author Daniel Luiz Coelho dos Santos.
	 * 
	 * @param {@code Contato}
	 * @return {@code ArrayList}
	 */
	@Override
	public ArrayList<Contato> findAll() {
		ArrayList<Contato> contatos = new ArrayList<>();
		try {
			Connection con = getConnection();
			PreparedStatement pst = con.prepareStatement(SQL_ORDER_BY_NOME);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Long id = Long.parseLong(rs.getString(1));
				String nome = rs.getString(2);
				String telefone = rs.getString(3);
				String celular = rs.getString(4);
				contatos.add(new Contato(id, nome, telefone, celular));
			}
			closeConnection(con);
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * Método que irá retornar um objeto do tipo {@link Contato}.
	 *
	 * @author Daniel Luiz Coelho dos Santos.
	 * 
	 * @param {@code Contato}
	 * @return {@code Contato}
	 */
	@Override
	public Contato findById(Contato contato) {
		try {
			Connection con = getConnection();
			PreparedStatement pst = con.prepareStatement(SQL_SELECT_BY_ID);
			pst.setString(1, String.valueOf(contato.getId()));
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				contato.setId(Long.parseLong(rs.getString(1)));
				contato.setNome(rs.getString(2));
				contato.setTelefone(rs.getString(3));
				contato.setCelular(rs.getString(4));
			}
			closeConnection(con);
			return contato;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	/**
	 * Método que irá atualizar um objeto do tipo {@link Contato}.
	 *
	 * @author Daniel Luiz Coelho dos Santos.
	 * 
	 * @param {@code Contato}
	 * @return void
	 */
	@Override
	public void update(Contato contato) {
		try {
			Connection con = getConnection();
			PreparedStatement pst = con.prepareStatement(SQL_UPDATE);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getTelefone());
			pst.setString(3, contato.getCelular());
			pst.setString(4, String.valueOf(contato.getId()));
			pst.executeUpdate();
			closeConnection(con);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 * Método que irá excluir um objeto do tipo {@link Contato}.
	 *
	 * @author Daniel Luiz Coelho dos Santos.
	 * 
	 * @param {@code Contato}
	 */
	@Override
	public void delete(Contato contato) {
		try {
			Connection con = getConnection();
			PreparedStatement pst = con.prepareStatement(SQL_DELETE);
			pst.setString(1, String.valueOf(contato.getId()));
			pst.executeUpdate();
			closeConnection(con);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 * Método que irá fechar uma conexão com o Banco de Dados.
	 *
	 * @author Daniel Luiz Coelho dos Santos.
	 * 
	 * @param {@code Connection}
	 */
	public void closeConnection(Connection connection) {
		this.connetionDataBase.getCloseConnection(connection);

	}
}
