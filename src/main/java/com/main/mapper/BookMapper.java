package com.main.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.main.dto.BookDto;
import com.main.entity.Book;

@Mapper(componentModel = "spring" )
public interface BookMapper {
	
	@Mapping(target ="authors",ignore = true )
	Book maptoAuthor(BookDto dto);
	@Mapping(target ="authors",ignore = true )
	BookDto maptoDto(Book book);
	List<Book> mapToBooks(List<BookDto> dtos);
	List<BookDto> maptoDtos(List<Book> books);
	
	@AfterMapping
	default void modifyBooksNames(@MappingTarget BookDto dto,Book book) {
		
		Set<String> authorNames = new HashSet<String>();
		book.getAuthors().forEach(x->authorNames.add(x.getName()));
		dto.setAuthors(authorNames);
	}

}
