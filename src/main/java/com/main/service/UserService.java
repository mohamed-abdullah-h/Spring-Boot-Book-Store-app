package com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.entity.User;
import com.main.exceptions.IDException;
import com.main.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo repo;
	
	public User findById(long id) {
		return repo.findById(id).get();
	}
	public List<User> getAll(){
		return repo.findAll();
	}
	
	public void deleteById(long id) {
		repo.deleteById(id);
	}
	public User insert(User user) {
		if(user.getId()>0) {
			throw new IDException("Duplicate ID");
		}
		return repo.save(user);
	}
	public User update(User user) {
		if(user.getId()<=0) {
			throw new IDException("Id Not found Exception");
		}
		
		return repo.save(updateEntity(user));
	
	}
	private User updateEntity(User user) {
		User persistedUser = repo.findById(user.getId()).get();
		if(user.getName()!=null && user.getName().trim().length()>0) {
			persistedUser.setName(user.getName());
		}
		if(user.getFav_books() !=null) {
			persistedUser.setFav_books(user.getFav_books());
		}
		return persistedUser;
	}
}
