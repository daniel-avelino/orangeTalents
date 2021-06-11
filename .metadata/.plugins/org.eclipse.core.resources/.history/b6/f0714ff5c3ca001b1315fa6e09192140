package com.zup.orangeTalents.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zup.orangeTalents.DTO.UserDTO;
import com.zup.orangeTalents.entities.User;
import com.zup.orangeTalents.factory.UserFactory;
import com.zup.orangeTalents.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserFactory factory;

	public void insertUser(User user) {
		repository.save(user);
	}

	public UserDTO findUserbyEmail(String email) throws Exception {
		try {
			User user = repository.findByEmail(email).get();
			UserDTO dto = factory.newUserDTO(user);
			return dto;
		} catch (Exception e) {
			throw new Exception("problems");
		}
	}
	
	

}
