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

@Service
public class FeignService {

	@Autowired
	private FipeFeign fipe;

	@Autowired
	private CarRepository repository;

	public Car findCarPrice(Car car, User user) {
		Fipe marca = fipe.getMarcas().stream().filter(x -> x.getNome().equalsIgnoreCase(car.getMarca()))
				.collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
					return list.get(0);
				}));

		Fipe modelo = fipe.getModelos(marca.getCodigo()).getModelos().stream()
				.filter(x -> x.getNome().equalsIgnoreCase(car.getModelo()))
				.collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
					return list.get(0);
				}));

		Fipe ano = fipe.getModelsAnos(marca.getCodigo(), modelo.getCodigo()).stream()
				.filter(x -> x.getNome().contains(car.getAno()))
				.collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
					return list.get(0);
				}));
		String price = fipe.getCars(marca.getCodigo(), modelo.getCodigo(), ano.getCodigo()).getValor();
		car.setValor(price);
		car.setUser(user);
		// car.setDiaRodizio(setRodizio(car.getAno()));
		car.setDiaRodizio(DayOfWeek.FRIDAY);
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
			System.out.println("entrou 1: " + lastDigit);
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
		System.out.println("DAY: " + day);

		System.out.println("TODAY: " + LocalDate.now().getDayOfWeek());
		if (day == LocalDate.now().getDayOfWeek()) {
			return true;
		} else {
			return false;
		}
	}
}
