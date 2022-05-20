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

import com.main.dto.UserDto;
import com.main.entity.User;
import com.main.mapper.UserMapper;
import com.main.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService service;
	@Autowired
	private UserMapper userMapper;

	@GetMapping("/{id}")
	public UserDto findById(@PathVariable long id) {
		User user = service.findById(id);
		UserDto dto = userMapper.mapToDto(user);
		return dto;

	}

	@GetMapping
	public List<UserDto> getAll() {
		List<User> users = service.getAll();
		List<UserDto> dtos = userMapper.maptoDtos(users);
		return dtos;
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable long id) {
		service.deleteById(id);
	}

	@PostMapping
	public UserDto insert(@RequestBody User user) {
		User savedUser = service.insert(user);
		UserDto dto = userMapper.mapToDto(savedUser);
		return dto;

	}
	
	@PutMapping
	public UserDto update(@RequestBody User user) {
		User savedUser = service.update(user);
		UserDto dto = userMapper.mapToDto(savedUser);
		return dto;

	}

}
