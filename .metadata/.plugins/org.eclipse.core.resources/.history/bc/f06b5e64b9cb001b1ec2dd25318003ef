package com.zup.orangeTalents.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zup.orangeTalents.DTO.UserDTO;
import com.zup.orangeTalents.entities.Car;
import com.zup.orangeTalents.services.UserService;

@RestController
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping
	public ResponseEntity<?> insertUser(@RequestBody UserDTO user) {
		service.insertUser(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PostMapping(path = "/{email}")
	public ResponseEntity<?> insertCarUser(@PathVariable String email, @RequestBody Car car) {
		service.newCar(car, email);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<?> findUserByEmail(@RequestParam("email") String email) {
		UserDTO user = service.findUserbyEmail(email);
		return ResponseEntity.ok().body(user);
	}
}
