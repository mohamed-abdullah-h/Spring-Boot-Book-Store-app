package com.main.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.main.dto.UserDto;
import com.main.entity.User;
import com.main.service.UserService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

	@Autowired
	private TestRestTemplate rest;
	
	@MockBean
	private UserService service;
	
	@Test
	public void insertUserTest() {
		User user = new User(5, "dummy",null,false);
		Mockito.when(service.insert(user)).thenReturn(user);
		ResponseEntity<UserDto> dto = rest.postForEntity("/users", user, UserDto.class);
		assertEquals(dto.getStatusCodeValue(), 200);	
	}
	@Test
	public void updateUserTest() {
		User user = new User(5, "dummy",null,false);
		Mockito.when(service.update(user)).thenReturn(user);
		HttpEntity<User> entity = new HttpEntity<User>(user);
		ResponseEntity<UserDto> dto = 
				rest.exchange("/users",HttpMethod.PUT ,entity, UserDto.class);
		assertEquals(dto.getStatusCodeValue(), 200);	
	}
}
