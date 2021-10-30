package br.com.mvc.util;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class ConnectionFactory{
	
	public static Connection getConnection() throws Exception{
		// m�todo getConnection - n�o ir� tratar erros
		try {
			// indica o DB mysql e aponta para o driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Conex�o com DB
			String login = "root";
			String senha = "";
			String url = "jdbc:mysql://localhost:3306/cadastroalunonotas";
			return DriverManager.getConnection(url, login, senha);
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
