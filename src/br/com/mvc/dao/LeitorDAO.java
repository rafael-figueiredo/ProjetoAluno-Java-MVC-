package br.com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.mvc.model.Leitor;
import br.com.mvc.util.ConnectionFactory;

public class LeitorDAO { // CRUD
	
	private Leitor leitor;
	private Connection conn; // Conecta com o banco de dados
	private PreparedStatement ps; // Permite executar querys
	private ResultSet rs; // Tabela
	
	public LeitorDAO() throws Exception {
		try {
			conn = ConnectionFactory.getConnection();
		} catch (Exception e) {
			throw new Exception("Erro"+ e.getMessage());
		}
	}
	
	//M�todo Salvar
	public void salvar(Leitor leitor) throws Exception {
		try {
			String sql="INSERT INTO dadospessoais(RGM, NOME, DATA, CPF, EMAIL, ENDERECO, MUNICIPIO, UF, CELULAR, CURSO, CAMPUS, PERIODO) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, leitor.getRgm());
			ps.setString(2, leitor.getNome());
			ps.setString(3, leitor.getData());
			ps.setString(4, leitor.getCpf());
			ps.setString(5, leitor.getEmail());
			ps.setString(6, leitor.getEndereco());
			ps.setString(7, leitor.getMunicipio());
			ps.setString(8, leitor.getUf());
			ps.setString(9, leitor.getCelular());
			ps.setString(10, leitor.getCurso());
			ps.setString(11, leitor.getCampus());
			ps.setString(12, leitor.getPeriodo());
			ps.executeUpdate();
	
		}catch(Exception e) {
			throw new Exception("Erro ao salvar"+e.getMessage());
		}
	}
	
	//M�todo Alterar
	public void alterar(Leitor leitor) throws Exception {
		try {
			String sql="UPDATE dadospessoais SET NOME=?, DATA=?, CPF=?, EMAIL=?, ENDERECO=?, MUNICIPIO=?, UF=?, CELULAR=?, CURSO=?, CAMPUS=?, PERIODO=? "
					+ " WHERE RGM=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, leitor.getNome());
			ps.setString(2, leitor.getData());
			ps.setString(3, leitor.getCpf());
			ps.setString(4, leitor.getEmail());
			ps.setString(5, leitor.getEndereco());
			ps.setString(6, leitor.getMunicipio());
			ps.setString(7, leitor.getUf());
			ps.setString(8, leitor.getCelular());
			ps.setString(9, leitor.getCurso());
			ps.setString(10, leitor.getCampus());
			ps.setString(11, leitor.getPeriodo());
			ps.setInt(12, leitor.getRgm());
			ps.executeUpdate();
	
		}catch(Exception e) {
			throw new Exception("Erro ao alterar"+e.getMessage());
		}
	}
	
	//Met�do Excluir
	public void excluir(int rgm) throws Exception {
		try {
			String sql="DELETE FROM dadospessoais "
					+ " WHERE RGM=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rgm);
			ps.executeUpdate();
	
		}catch(Exception e) {
			throw new Exception("Erro ao excluir"+e.getMessage());
		}
	}
	
	//M�todo Consultar
	public Leitor consultar(int rgm) throws Exception {
		try {
			ps = conn.prepareStatement("SELECT * FROM dadospessoais WHERE RGM=?");
			ps.setInt(1, rgm);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String nome = rs.getString("NOME");
				String data = rs.getString("DATA");
				String cpf = rs.getString("CPF");
				String email = rs.getString("EMAIL");
				String endereco = rs.getString("ENDERECO");
				String municipio = rs.getString("MUNICIPIO");
				String uf = rs.getString("UF");
				String celular = rs.getString("CELULAR");
				String curso = rs.getString("CURSO");
				String campus = rs.getString("CAMPUS");
				String periodo = rs.getString("PERIODO");
				leitor = new Leitor(rgm, nome, data, cpf, email, endereco, municipio, uf, celular, curso, campus, periodo);
			}
			return leitor;
		
		}catch(Exception e) {
			throw new Exception("Erro ao listar" + e.getMessage());
		}
	}
}
