package br.com.projeto.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe Java, que tem por objetivo, estabelecer a conexão com o Banco de
 * Dados.
 * 
 * @author Daniel Luiz Coelho dos Santos.
 * @since 26/10-2024.
 */
public class DAOFactory {

	/**
	 * Definição do driver do Banco de Dados. Será utilizado driver do Banco de
	 * Dados Mysql.
	 * 
	 * @author Daniel Luiz Coelho dos Santos.
	 */
	private String driver = "com.mysql.cj.jdbc.Driver";

	/**
	 * Definição da URL do Banco de Dados.
	 * 
	 * @author Daniel Luiz Coelho dos Santos.
	 */
	private String url = "jdbc:mysql://localhost:3306/contato?useTimezone=true&serverTimezone=UTC";

	/**
	 * Definição do usuário registrado no Banco de Dados. No caso, a pessoa que
	 * estrá fazendo uso deste projeto, deverá colocar seu usuário. Assim, evitando
	 * erros durante a execução da aplicação.
	 * 
	 * @author Daniel Luiz Coelho dos Santos.
	 */
	private String user = "root";

	/**
	 * Definição da senha do usuário registrado no Banco de Dados. No caso, a pessoa
	 * que estrá fazendo uso deste projeto, deverá colocar a senha registrada ao
	 * usuário do Banco de Dados. Assim, evitando erros durante a execução da
	 * aplicação.
	 * 
	 * @author Daniel Luiz Coelho dos Santos.
	 */
	private String password = "da19021989";

	/**
	 * Método que estabelecerá a conexão com o Banco de Dados. Para a conexão serão
	 * utilizados os valores presentes nas variáveis: url; user; password;
	 * 
	 * @author Daniel Luiz Coelho dos Santos.
	 * 
	 * @return {@code Connection}
	 */
	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * Método que será utilizado para encerrar a conexão com o Banco de Dados.
	 * 
	 * @author Daniel Luiz Coelho dos Santos.
	 * 
	 */
	public void getCloseConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
