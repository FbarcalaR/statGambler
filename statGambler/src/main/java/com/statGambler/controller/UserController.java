package com.statGambler.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.statGambler.model.User;
import com.statGambler.repository.UserRepository;

@RestController
@RequestMapping("api/v1/")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "users", method = RequestMethod.GET)
	public List<User> list() {
		return userRepository.findAll();
	}

	@RequestMapping(value = "users", method = RequestMethod.POST)
	public User create(@RequestBody User shipwreck) {
		return userRepository.saveAndFlush(shipwreck);
	}

	@RequestMapping(value = "users/{id}", method = RequestMethod.GET)
	public User get(@PathVariable Long id) {
		return userRepository.findOne(id);
	}

	@RequestMapping(value = "users/{id}", method = RequestMethod.PUT)
	public User update(@PathVariable Long id, @RequestBody User shipwreck) {
		User existingShipwreck = userRepository.findOne(id);
		BeanUtils.copyProperties(shipwreck, existingShipwreck);
		return userRepository.saveAndFlush(existingShipwreck);
	}

	@RequestMapping(value = "users/{id}", method = RequestMethod.DELETE)
	public User delete(@PathVariable Long id) {
		User existingShipwreck = userRepository.findOne(id);
		userRepository.delete(existingShipwreck);
		return existingShipwreck;
	}
	
}
