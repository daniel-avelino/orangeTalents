package com.zup.orangeTalents.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Models implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Fipe> modelos = new ArrayList<>();
	private List<Fipe> anos = new ArrayList<>();

	public Models() {
	}

	public Models(List<Fipe> modelos, List<Fipe> anos) {
		super();
		this.modelos = modelos;
		this.anos = anos;
	}

	public List<Fipe> getModelos() {
		return modelos;
	}

	public List<Fipe> getAnos() {
		return anos;
	}
}
