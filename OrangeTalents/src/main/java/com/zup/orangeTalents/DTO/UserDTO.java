package com.zup.orangeTalents.DTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.springframework.lang.NonNull;

import com.zup.orangeTalents.entities.User;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Insira um nome.")
	private String name;

	@NotBlank
	@Email(message = "E-mail inválido.")
	private String email;

	@NotBlank
	@Pattern(regexp = "[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}", message = "CPF em formato inválido 000.000.000-00")
	private String cpf;

	@NonNull
	@Past(message = "Data de nascimento inválida, é preciso ser menor que a data de hoje.")
	private LocalDate birthday;

	private Set<CarDTO> cars = new HashSet<>();

	public UserDTO() {
	}

	public UserDTO(String name, String email, String cpf, String birthday, Set<CarDTO> cars) {
		this.name = name;
		this.email = email;
		this.cpf = cpf;
		this.birthday = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.cars = cars;
	}

	public UserDTO(User user) {
		name = user.getName();
		email = user.getEmail();
		cpf = user.getCpf();
		birthday = user.getBirthday();
		cars = user.getCars().stream().map(x -> new CarDTO(x)).collect(Collectors.toSet());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public Set<CarDTO> getCars() {
		return cars;
	}

}
