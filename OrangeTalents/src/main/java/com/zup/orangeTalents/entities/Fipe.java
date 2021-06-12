package com.zup.orangeTalents.entities;

import java.io.Serializable;

public class Fipe implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String codigo;

	public Fipe() {
	}

	public Fipe(String nome, String codigo) {
		super();
		this.nome = nome;
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "Fipe [nome=" + nome + ", codigo=" + codigo + "]";
	}

	
	
}
