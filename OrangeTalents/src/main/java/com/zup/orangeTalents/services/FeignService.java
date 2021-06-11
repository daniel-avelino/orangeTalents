package com.zup.orangeTalents.services;

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
				.filter(x -> x.getNome().equalsIgnoreCase(car.getAno()))
				.collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
					return list.get(0);
				}));
		String price = fipe.getCars(marca.getCodigo(), modelo.getCodigo(), ano.getCodigo()).getValor();
		car.setValor(price);
		car.setUser(user);
		repository.save(car);

		return car;
	}

}
