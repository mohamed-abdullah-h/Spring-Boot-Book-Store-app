package com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.entity.Role;
import com.main.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	private RoleService service;
	
	@GetMapping("/{id}")
	public Role findById(@PathVariable long id) {
		Role role = service.findById(id);
		return role;

	}

	@GetMapping
	public List<Role> getAll() {
		List<Role> roles = service.getAll();
		return roles;
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable long id) {
		service.deleteById(id);
	}

	@PostMapping
	public Role insert(@RequestBody Role role) {
		Role savedRole = service.insert(role);
		return savedRole;

	}
	
	@PutMapping
	public Role update(@RequestBody Role role) {
		Role updatedRole = service.update(role);
		return updatedRole;

	}

}
