package com.main.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.main.dto.AuthorDto;
import com.main.entity.Author;

@Mapper(componentModel = "spring" )
public interface AuthorMapper {
	
	
	Author maptoAuthor(AuthorDto dto);
	AuthorDto maptoDto(Author author);
	List<Author> maptoAuthors(List<AuthorDto> dtos);
	List<AuthorDto> maptoDtos(List<Author> authors);
	
	@AfterMapping
	default void modifyBooksNames(@MappingTarget AuthorDto dto,Author author) {
		
		Set<String> booksNames = new HashSet<String>();
		author.getBooks().forEach(x->booksNames.add(x.getName()));
		dto.setBooks_names(booksNames);
	}

}
