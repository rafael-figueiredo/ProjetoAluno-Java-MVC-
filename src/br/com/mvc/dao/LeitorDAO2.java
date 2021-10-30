package br.com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import br.com.mvc.model.Leitor2;
import br.com.mvc.util.ConnectionFactory;

public class LeitorDAO2 {	// CRUD
	private Connection conn2; // Conecta com o banco de dados
	private PreparedStatement ps2; // Permite executar querys
	
	public LeitorDAO2() throws Exception {
		try {
			conn2 = ConnectionFactory.getConnection();
		} catch (Exception e) {
			throw new Exception("Erro"+ e.getMessage());
		}
	}
	
	public void salvar2(Leitor2 leitor2) throws Exception {
		try {
			String sql2="INSERT INTO notasfaltas(DISCIPLINA, SEMESTRE, NOTA, FALTA, RGM, NOME, CURSO) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			ps2 = conn2.prepareStatement(sql2);
			ps2.setString(1, leitor2.getDisciplina());
			ps2.setString(2, leitor2.getSemestre());
			ps2.setDouble(3, leitor2.getNotas());
			ps2.setInt(4, leitor2.getFaltas());
			ps2.setInt(5, leitor2.getLeitor().getRgm());
			ps2.setString(6, leitor2.getLeitor().getNome());
			ps2.setString(7, leitor2.getLeitor().getCurso());
			ps2.executeUpdate();
	
		}catch(Exception e) {
			throw new Exception("Erro ao salvar"+e.getMessage());
		}
	}
	
	public void alterar2(Leitor2 leitor2) throws Exception {
		try {
			String sql2="UPDATE notasfaltas SET DISCIPLINA=?, SEMESTRE=?, NOTA=?, FALTA=?, NOME=?, CURSO=? "
					+ "WHERE RGM=?";
			ps2 = conn2.prepareStatement(sql2);
			ps2.setString(1, leitor2.getDisciplina());
			ps2.setString(2, leitor2.getSemestre());
			ps2.setDouble(3, leitor2.getNotas());
			ps2.setInt(4, leitor2.getFaltas());
			ps2.setString(5, leitor2.getLeitor().getNome());
			ps2.setString(6, leitor2.getLeitor().getCurso());
			ps2.setInt(7, leitor2.getLeitor().getRgm());
			ps2.executeUpdate();
	
		}catch(Exception e) {
			throw new Exception("Erro ao Alterar"+e.getMessage());
		}
	}
	
	public void excluir2(int rgm) throws Exception {
		try {
			String sql2="DELETE FROM notasfaltas "
					+ " WHERE RGM=?";
			ps2 = conn2.prepareStatement(sql2);
			ps2.setInt(1, rgm);
			ps2.executeUpdate();
	
		}catch(Exception e) {
			throw new Exception("Erro ao excluir"+e.getMessage());
		}
	}
}
