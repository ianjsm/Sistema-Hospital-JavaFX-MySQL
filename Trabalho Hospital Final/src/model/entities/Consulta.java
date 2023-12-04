package model.entities;

import java.sql.Date;

public class Consulta {
	protected int id;
	protected String crm_Medico;
	protected String cpf;
	protected Date data;
	protected String comentario;

	public Consulta(int id, String cpf, Date data) {
		this.id = id;
		this.cpf = cpf;
		this.data = data;
	}

	public Consulta(int id, String crm_Medico, String cpf, Date data) {
		this.id = id;
		this.crm_Medico = crm_Medico;
		this.cpf = cpf;
		this.data = data;
	}

	public Consulta(int id, String crm_Medico, String cpf, Date data, String comentario) {
		this.id = id;
		this.crm_Medico = crm_Medico;
		this.cpf = cpf;
		this.data = data;
		this.comentario = comentario;
	}

	public int getId() {
		return id;
	}

	public String getCpf() {
		return cpf;
	}

	public Date getData() {
		return data;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getCrm_Medico() {
		return crm_Medico;
	}

	public void setCrm_Medico(String crm_Medico) {
		this.crm_Medico = crm_Medico;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Override
	public String toString() {
		return "Consulta id=" + id + ", crm =" + crm_Medico + ", cpf =" + cpf + ", data =" + data + ", comentario ="
				+ comentario;
	}
}