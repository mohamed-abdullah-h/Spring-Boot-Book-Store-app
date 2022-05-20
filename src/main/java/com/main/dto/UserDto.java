package com.main.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

	private long id;
	private String name;

	private Set<String> favourite_books = new HashSet<String>();
}
