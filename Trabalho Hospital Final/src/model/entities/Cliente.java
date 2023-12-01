package model.entities;

public class Cliente {
	protected String nome;
	protected String idade;
	protected String plano;
	protected String senha;

	public Cliente(String nome, String idade, String plano, String senha) {
		this.nome = nome;
		this.idade = idade;
		this.plano = plano;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getPlano() {
		return plano;
	}

	public void setPlano(String plano) {
		this.plano = plano;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String toString() {
		return nome + " " + idade + " " + plano + " " + senha;
	}
}