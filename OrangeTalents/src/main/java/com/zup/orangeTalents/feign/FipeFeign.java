package com.zup.orangeTalents.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.zup.orangeTalents.entities.Car;
import com.zup.orangeTalents.entities.Fipe;
import com.zup.orangeTalents.entities.Models;

@FeignClient(url = "https://parallelum.com.br/fipe/api/v1/carros", name = "fipe")
public interface FipeFeign {

	@GetMapping(path = "/marcas")
	List<Fipe> getMarcas();

	@GetMapping(path = "/marcas/{codMarca}/modelos")
	Models getModelos(@PathVariable String codMarca);

	@GetMapping(path = "/marcas/{codMarca}/modelos/{codCarro}/anos")
	List<Fipe> getModelsAnos(@PathVariable String codMarca, @PathVariable String codCarro);

	@GetMapping(path = "/marcas/{codMarca}/modelos/{codCarro}/anos/{codAno}")
	Car getCars(@PathVariable String codMarca, @PathVariable String codCarro, @PathVariable String codAno);
}
