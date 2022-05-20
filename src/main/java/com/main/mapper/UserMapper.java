package com.main.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.main.dto.UserDto;
import com.main.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	
	User mapToUser(UserDto dto);
	UserDto mapToDto(User user);
	
	List<User> maptoAuthors(List<UserDto> dtos);
	List<UserDto> maptoDtos(List<User> users);
	@AfterMapping
	default void modifyBooks(@MappingTarget UserDto dto,User user) {
		Set<String> books = new HashSet<String>();
		user.getFav_books().forEach(x->books.add(x.getName()));
		dto.setFavourite_books(books);
	}
	
}
