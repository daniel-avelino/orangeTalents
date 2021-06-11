package com.zup.orangeTalents.config;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.zup.orangeTalents.entities.User;
import com.zup.orangeTalents.services.UserService;

@Configuration
public class Configs implements CommandLineRunner {

	@Autowired
	private UserService service;

	@Override
	public void run(String... args) throws Exception {
		User user = new User(null, "teste", "teste@teste", "123123", Calendar.getInstance());
		service.insertUser(user);
	}

}
