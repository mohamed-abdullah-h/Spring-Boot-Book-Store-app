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

import com.main.dto.AuthorDto;
import com.main.entity.Author;
import com.main.mapper.AuthorMapper;
import com.main.service.AuthorService;

@RestController
@RequestMapping("/authors")
@Validated
public class AuthorController {

	@Autowired
	private AuthorService service;
	
	@Autowired
	private AuthorMapper authorMapper;
	
	@GetMapping("/{id}")
	public AuthorDto findById(@PathVariable long id) {
		
		Author author = service.findById(id);
		AuthorDto dto = authorMapper.maptoDto(author); 
		return dto;
	}
	@GetMapping
	public List<AuthorDto> getAll(){
		List<Author> authors = service.getAll();
		List<AuthorDto> dtos = authorMapper.maptoDtos(authors);
		return dtos;
	}
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable long id) {
		service.deleteById(id);
	}
	@PostMapping
	public AuthorDto insert(@Valid @RequestBody Author author) {
		Author savedAuthor = service.insert(author);
		AuthorDto dto = authorMapper.maptoDto(savedAuthor); 
		return dto;
	}
	@PutMapping
	public AuthorDto update(@Valid @RequestBody Author author) {
		Author savedAuthor = service.update(author);
		AuthorDto dto = authorMapper.maptoDto(savedAuthor); 
		return dto;
	}
	@GetMapping("/book/{id}")
	public List<AuthorDto> findByBookId(@PathVariable long id){
		List<Author> authors = service.findByBookId(id);
		List<AuthorDto> dtos = authorMapper.maptoDtos(authors);
		return dtos;
	}
}
