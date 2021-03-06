package com.zup.orangeTalents.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.zup.orangeTalents.entities.Car;
import com.zup.orangeTalents.entities.Dia;

public class CarDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Insira uma marca.")
	private String marca;
	@NotBlank(message = "Insira um modelo.")
	private String modelo;
	@NotBlank(message = "Insira um ano.")
	private String ano;

	private String valor;

	private Dia diaRodizio;

	private Boolean rodizioAtivo;

	public CarDTO() {
	}

	public CarDTO(String marca, String modelo, String ano) {
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
	}

	public CarDTO(Car car) {
		this.marca = car.getMarca();
		this.modelo = car.getModelo();
		this.ano = car.getAno();
		this.valor = car.getValor();
		this.diaRodizio = car.getDiaRodizio();
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Dia getDiaRodizio() {
		return diaRodizio;
	}

	public void setDiaRodizio(Dia diaRodizio) {
		this.diaRodizio = diaRodizio;
	}

	public Boolean getRodizioAtivo() {
		return rodizioAtivo;
	}

	public void setRodizioAtivo(Boolean rodizioAtivo) {
		this.rodizioAtivo = rodizioAtivo;
	}

}
