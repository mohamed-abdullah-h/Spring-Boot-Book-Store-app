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

import com.main.dto.AuthorDto;
import com.main.entity.Author;
import com.main.service.AuthorService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

	@Autowired
	private TestRestTemplate rest;
	
	@MockBean
	private AuthorService service;
	
	@Test
	public void insertAuthorTest() {
		Author author = new Author(0, "dummy", 0, false, null);
		Mockito.when(service.insert(author)).thenReturn(author);
		ResponseEntity<AuthorDto> dto = rest.postForEntity("/authors", author, AuthorDto.class);
		assertEquals(dto.getStatusCodeValue(), 200);	
	}
	@Test
	public void updateAuthorTest() {
		Author author = new Author(0, "dummy", 0, false, null);
		Mockito.when(service.update(author)).thenReturn(author);
		HttpEntity<Author> entity = new HttpEntity<Author>(author);
		ResponseEntity<AuthorDto> dto = rest.
				exchange("/authors",HttpMethod.PUT ,entity, AuthorDto.class);
		assertEquals(dto.getStatusCodeValue(), 200);	
	}
}
