package com.zup.orangeTalents.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zup.orangeTalents.DTO.CarDTO;
import com.zup.orangeTalents.DTO.UserDTO;
import com.zup.orangeTalents.entities.User;
import com.zup.orangeTalents.repositories.UserRepository;
import com.zup.orangeTalents.services.exceptions.ConstraintException;
import com.zup.orangeTalents.services.exceptions.NotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private CarService feign;

	public void insertUser(UserDTO dto) {
		try {
			User user = new User(dto);
			repository.save(user);
		} catch (DataIntegrityViolationException e) {
			throw new ConstraintException("Usuário duplicado");
		}
	}

	@Transactional(readOnly = true)
	public UserDTO findUserbyEmail(String email) {
		User user = repository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
		UserDTO dto = new UserDTO(user);
		dto.getCars().forEach((CarDTO car) -> {
			feign.setRodizioAtivo(car);
		});
		return dto;
	}

	@Transactional
	public void newCar(CarDTO car, String email) {
		User user = repository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
		user.insertCarUser(feign.findCarPrice(car, user));
		repository.save(user);
	}

}
