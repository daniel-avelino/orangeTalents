package com.zup.orangeTalents.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zup.orangeTalents.entities.Car;
import com.zup.orangeTalents.entities.Fipe;
import com.zup.orangeTalents.entities.User;
import com.zup.orangeTalents.feign.FipeFeign;
import com.zup.orangeTalents.repositories.CarRepository;
import com.zup.orangeTalents.services.exceptions.AnoException;
import com.zup.orangeTalents.services.exceptions.MarcasException;
import com.zup.orangeTalents.services.exceptions.ModelosException;

@Service
public class FeignService {

	@Autowired
	private FipeFeign fipe;

	@Autowired
	private CarRepository repository;

	public Car findCarPrice(Car car, User user) {
		Fipe marca = fipe.getMarcas().stream()
				.filter(x -> x.getNome().toLowerCase().contains(car.getMarca().toLowerCase()))
				.collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
					if (list.size() != 1) {
						throw new MarcasException("Mais de um elemento retornado");
					}
					return list.get(0);
				}));

		Fipe modelo = fipe.getModelos(marca.getCodigo()).getModelos().stream()
				.filter(x -> x.getNome().toLowerCase().contains(car.getModelo().toLowerCase()))
				.collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
					if (list.size() != 1) {
						throw new ModelosException("Mais de um elemento retornado");
					}
					return list.get(0);
				}));

		Fipe ano = fipe.getModelsAnos(marca.getCodigo(), modelo.getCodigo()).stream()
				.filter(x -> x.getNome().contains(car.getAno()))
				.collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
					if (list.size() != 1) {
						throw new AnoException("Mais de um elemento retornado");
					}
					return list.get(0);
				}));

		car.setValor(fipe.getCars(marca.getCodigo(), modelo.getCodigo(), ano.getCodigo()).getValor());
		car.setUser(user);
		car.setDiaRodizio(setRodizio(car.getAno()));
		car.setRodizioAtivo(rodizioAtivo(car.getDiaRodizio()));
		repository.save(car);

		return car;

	}

	public DayOfWeek setRodizio(String ano) {
		int lastDigit = Character.getNumericValue(ano.charAt(3));
		DayOfWeek day = null;
		switch (lastDigit) {
		case 0:
		case 1:
			day = DayOfWeek.MONDAY;
			break;
		case 2:
		case 3:
			day = DayOfWeek.TUESDAY;
			break;
		case 4:
		case 5:
			day = DayOfWeek.WEDNESDAY;
			break;
		case 6:
		case 7:
			day = DayOfWeek.THURSDAY;
			break;
		case 8:
		case 9:
			day = DayOfWeek.FRIDAY;
			break;
		}
		return day;
	}

	public boolean rodizioAtivo(DayOfWeek day) {
		if (day == LocalDate.now().getDayOfWeek()) {
			return true;
		} else {
			return false;
		}
	}
}
