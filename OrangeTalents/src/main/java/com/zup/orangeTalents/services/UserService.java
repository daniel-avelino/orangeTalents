package com.zup.orangeTalents.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zup.orangeTalents.DTO.UserDTO;
import com.zup.orangeTalents.entities.Car;
import com.zup.orangeTalents.entities.User;
import com.zup.orangeTalents.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private FeignService feign;

	public void insertUser(User user) {
		repository.saveAndFlush(user);
	}

	public UserDTO findUserbyEmail(String email) throws Exception {
		try {
			User user = repository.findByEmail(email).get();
			UserDTO dto = new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getCpf(), user.getBirthday(),
					user.getCars());
			return dto;
		} catch (Exception e) {
			throw new Exception("problems");
		}
	}

	public void newCar(Car car, String email) {
		User user = repository.findByEmail(email).get();
		user.insertCarUser(feign.findCarPrice(car,user));
		repository.saveAndFlush(user);
	}

}
