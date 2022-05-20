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

import com.main.dto.BookDto;
import com.main.entity.Book;
import com.main.service.BookService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AutherControllerTest {

	@Autowired
	private TestRestTemplate rest;
	
	@MockBean
	private BookService service;
	
	@Test
	public void insertBookTest() {
		Book book = new Book(4, "dummy", 0, null, null, false);
		Mockito.when(service.insert(book)).thenReturn(book);
		ResponseEntity<BookDto> dto = rest.postForEntity("/books", book, BookDto.class);
		assertEquals(dto.getStatusCodeValue(), 200);	
	}
	@Test
	public void updateBookTest() {
		Book book = new Book(4, "dummy", 0, null, null, false);
		Mockito.when(service.update(book)).thenReturn(book);
		HttpEntity<Book> entity = new HttpEntity<Book>(book);
		ResponseEntity<BookDto> dto = rest.
				exchange("/books",HttpMethod.PUT ,entity, BookDto.class);
		assertEquals(dto.getStatusCodeValue(), 200);	
	}
}
