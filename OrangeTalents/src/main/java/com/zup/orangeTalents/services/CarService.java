package com.zup.orangeTalents.services;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zup.orangeTalents.DTO.CarDTO;
import com.zup.orangeTalents.entities.Car;
import com.zup.orangeTalents.entities.Dia;
import com.zup.orangeTalents.entities.Fipe;
import com.zup.orangeTalents.entities.User;
import com.zup.orangeTalents.feign.FipeFeign;
import com.zup.orangeTalents.repositories.CarRepository;
import com.zup.orangeTalents.services.exceptions.AnoException;
import com.zup.orangeTalents.services.exceptions.MarcasException;
import com.zup.orangeTalents.services.exceptions.ModelosException;

@Service
public class CarService {

	@Autowired
	private FipeFeign fipe;

	@Autowired
	private CarRepository repository;

	public Car findCarPrice(CarDTO newCar, User user) {
		Car car = new Car(newCar);

		Fipe marca = fipe.getMarcas().stream()
				.filter(x -> x.getNome().toLowerCase().contains(car.getMarca().toLowerCase()))
				.collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
					if (list.size() != 1) {
						throw new MarcasException("Mais de uma marca retornada");
					}
					return list.get(0);
				}));

		Fipe modelo = fipe.getModelos(marca.getCodigo()).getModelos().stream()
				.filter(x -> x.getNome().toLowerCase().contains(car.getModelo().toLowerCase()))
				.collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
					if (list.size() != 1) {
						throw new ModelosException("Mais de um modelo retornado");
					}
					return list.get(0);
				}));

		Fipe ano = fipe.getModelsAnos(marca.getCodigo(), modelo.getCodigo()).stream()
				.filter(x -> x.getNome().contains(car.getAno()))
				.collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
					if (list.size() != 1) {
						throw new AnoException("Mais de um ano retornado");
					}
					return list.get(0);
				}));

		setCarUser(car, user, marca.getCodigo(), modelo.getCodigo(), ano.getCodigo());

		return car;
	}

	public void setCarUser(Car car, User user, String marca, String modelo, String ano) {
		car.setValor(fipe.getCars(marca, modelo, ano).getValor());
		car.setUser(user);
		car.setDiaRodizio(setRodizio(car.getAno()));
		repository.save(car);
	}

	public Dia setRodizio(String ano) {
		int lastDigit = Character.getNumericValue(ano.charAt(3));
		Dia day = null;
		switch (lastDigit) {
		case 0:
		case 1:
			day = Dia.SEGUNDA_FEIRA;
			break;
		case 2:
		case 3:
			day = Dia.TER??A_FEIRA;
			break;
		case 4:
		case 5:
			day = Dia.QUARTA_FEIRA;
			break;
		case 6:
		case 7:
			day = Dia.QUINTA_FEIRA;
			break;
		case 8:
		case 9:
			day = Dia.SEXTA_FEIRA;
			break;
		}
		return day;
	}

	public CarDTO setRodizioAtivo(CarDTO car) {
		car.setRodizioAtivo(rodizioAtivo(car.getDiaRodizio()));
		return car;
	}

	public boolean rodizioAtivo(Dia day) {
		if (day.getValue() == LocalDate.now().getDayOfWeek().getValue()) {
			return true;
		} else {
			return false;
		}
	}
}
