package com.main.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.entity.SecurityUser;
import com.main.service.SecurityUserService;

@RestController
@RequestMapping("/secUsers")
@Validated
public class SecurityUserController {

	@Autowired
	private SecurityUserService service;
	
	@GetMapping("/{id}")
	public SecurityUser findById(@PathVariable long id) {
		SecurityUser user = service.findById(id);
		return user;

	}

	@GetMapping
	public List<SecurityUser> getAll() {
		List<SecurityUser> users = service.getAll();
		return users;
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable long id) {
		service.deleteById(id);
	}

	@PostMapping
	public SecurityUser insert(@Valid @RequestBody SecurityUser user) {
		SecurityUser savedUser = service.insert(user);
		return savedUser;

	}
	
	@PutMapping
	public SecurityUser update(@Valid @RequestBody SecurityUser user) {
		SecurityUser UpdatedUser = service.update(user);
		return UpdatedUser;

	}

}
