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

import com.main.entity.SecurityUser;
import com.main.service.SecurityUserService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SecurityUserControllerTest {

	@Autowired
	private TestRestTemplate rest;
	
	@MockBean
	private SecurityUserService service;
	
	@Test
	public void insertAuthorTest() {
		SecurityUser user = new SecurityUser(2,"dummy","moh@gmail.com","12345",true,false,null);
		Mockito.when(service.insert(user)).thenReturn(user);
		ResponseEntity<SecurityUser> dto = rest.postForEntity("/secUsers", user, SecurityUser.class);
		assertEquals(dto.getStatusCodeValue(), 200);	
	}
	@Test
	public void updateAuthorTest() {
		SecurityUser user = new SecurityUser(2,"dummy","moh@gmail.com","12345",true,false,null);
		Mockito.when(service.update(user)).thenReturn(user);
		HttpEntity<SecurityUser> entity = new HttpEntity<SecurityUser>(user);
		ResponseEntity<SecurityUser> dto = rest.
				exchange("/secUsers",HttpMethod.PUT ,entity, SecurityUser.class);
		assertEquals(dto.getStatusCodeValue(), 200);	
	}
}
