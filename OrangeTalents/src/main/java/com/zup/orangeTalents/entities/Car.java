package com.zup.orangeTalents.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.zup.orangeTalents.DTO.CarDTO;

@Entity
@Table(name = "tb_car")
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class Car implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String marca;
	private String modelo;
	private String ano;

	private String valor;

	private Dia diaRodizio;


	@JsonIgnore
	@ManyToOne
	private User user;

	public Car() {
	}

	public Car(String marca, String modelo, String ano) {
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
	}
	
	public Car(CarDTO dto) {
		this.id = null;
		this.marca = dto.getMarca();
		this.modelo = dto.getModelo();
		this.ano = dto.getAno();
		this.valor = dto.getValor();
	}

	public Car(Long id, String marca, String modelo, String ano, String valor, User user) {
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
		this.valor = valor;
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Dia getDiaRodizio() {
		return diaRodizio;
	}

	public void setDiaRodizio(Dia diaRodizio) {
		this.diaRodizio = diaRodizio;
	}
}
