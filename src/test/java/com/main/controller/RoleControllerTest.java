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

import com.main.entity.Role;
import com.main.service.RoleService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RoleControllerTest {

	@Autowired
	private TestRestTemplate rest;
	
	@MockBean
	private RoleService service;
	
	@Test
	public void insertAuthorTest() {
		Role role = new Role(0,"dummy");
		Mockito.when(service.insert(role)).thenReturn(role);
		ResponseEntity<Role> dto = rest.postForEntity("/roles", role, Role.class);
		assertEquals(dto.getStatusCodeValue(), 200);	
	}
	@Test
	public void updateAuthorTest() {
		Role role = new Role(0,"dummy");
		Mockito.when(service.update(role)).thenReturn(role);
		HttpEntity<Role> entity = new HttpEntity<Role>(role);
		ResponseEntity<Role> dto = rest.
				exchange("/roles",HttpMethod.PUT ,entity, Role.class);
		assertEquals(dto.getStatusCodeValue(), 200);	
	}
}
