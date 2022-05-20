package com.main.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AuthorDto {

	private long id;
	@NotBlank(message = "{javax.validation.constraints.NotBlank.message.custom}")
	private String name;
	private int numOfBooks;
	private Set<String> books_names = new HashSet<String>();
}
