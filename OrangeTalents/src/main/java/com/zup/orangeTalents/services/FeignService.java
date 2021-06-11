package com.zup.orangeTalents.services;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zup.orangeTalents.entities.Car;
import com.zup.orangeTalents.entities.Fipe;
import com.zup.orangeTalents.feign.FipeFeign;

@Service
public class FeignService {

	private FipeFeign fipe;

	public Car findCarPrice(Car car) {
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
		Double valor = fipe.getCars(marca.getCodigo(), modelo.getCodigo(), ano.getCodigo()).getValor();
		car.setValor(valor);

		return car;
	}
}
