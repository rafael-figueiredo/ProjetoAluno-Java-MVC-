package br.com.mvc.model;

public class Leitor2 {
	//Atributos
	private String disciplina;
	private String semestre;
	private double notas;
	private int faltas;
	private Leitor leitor;

	//Construtores
	public Leitor2() {
	}
	
	public Leitor2(String disciplina, String semestre, double notas, int faltas, Leitor leitor) {
		this.disciplina = disciplina;
		this.semestre = semestre;
		this.notas = notas;
		this.faltas = faltas;
		this.leitor = leitor;
	}
	
	//Getters e Setters
	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public double getNotas() {
		return notas;
	}

	public void setNotas(double notas) {
		this.notas = notas;
	}

	public int getFaltas() {
		return faltas;
	}

	public void setFaltas(int faltas) {
		this.faltas = faltas;
	}
	public Leitor getLeitor() {
		return leitor;
	}

	public void setLeitor(Leitor leitor) {
		this.leitor = leitor;
	}
}
