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

import com.main.dto.BookDto;
import com.main.entity.Book;
import com.main.mapper.BookMapper;
import com.main.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService service;
	@Autowired
	private BookMapper bookMapper;

	@GetMapping("/{id}")
	public BookDto findById(@PathVariable long id) {
		Book book = service.findById(id);
		BookDto dto = bookMapper.maptoDto(book);
		return dto;
	}

	@GetMapping
	public List<BookDto> getAll() {
		List<Book> books = service.getAll();
		List<BookDto> dtos = bookMapper.maptoDtos(books);
		return dtos;
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable long id) {
		service.deleteById(id);
	}

	@PostMapping
	public BookDto insert(@RequestBody Book book) {
		Book savedBook = service.insert(book);
		BookDto dto = bookMapper.maptoDto(savedBook);
		return dto;

	}

	@PutMapping
	public BookDto update(@RequestBody Book book) {
		Book savedBook = service.update(book);
		BookDto dto = bookMapper.maptoDto(savedBook);
		return dto;

	}

	/*
	 * @PostMapping public Book save(@RequestBody Book book) { Book savedBook =
	 * service.save(book);
	 * 
	 * return savedBook;
	 * 
	 * }
	 */

}
