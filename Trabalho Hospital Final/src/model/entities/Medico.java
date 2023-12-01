package model.entities;

public class Medico {
	private String nome;
	private int crm;
	private String especialidade;
	private String planoAtendido;
	private String senha;

	public Medico(String nome, String especialidade, String planoAtendido, String senha, int crm) {
		this.nome = nome;
		this.especialidade = especialidade;
		this.planoAtendido = planoAtendido;
		this.senha = senha;
		this.crm = crm;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getPlanoAtendido() {
		return planoAtendido;
	}

	public void setPlanoAtendido(String planoAtendido) {
		this.planoAtendido = planoAtendido;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getCrm() {
		return crm;
	}

	public void setCrm(int crm) {
		this.crm = crm;
	}

	@Override
	public String toString() {
		return nome + " " + especialidade + " " + planoAtendido + " " + senha + " " + crm;
	}
}