package com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.main.entity.SecurityUser;
import com.main.exceptions.IDException;
import com.main.repository.SecurityUserRepo;
import com.main.security.SecurityUserDetails;

@Service

public class SecurityUserService implements UserDetailsService{

	@Autowired
	private SecurityUserRepo repo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public SecurityUser findById(long id) {
		return repo.findById(id).get();
	}
	public List<SecurityUser> getAll(){
		return repo.findAll();
	}
	
	public void deleteById(long id) {
		repo.deleteById(id);
	}
	public SecurityUser insert(SecurityUser user) {
		if(user.getId()>0) {
			throw new IDException("Duplicate ID");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repo.save(user);
	}
	public SecurityUser update(SecurityUser user) {
		if(user.getId()<=0) {
			throw new IDException("Id Not found Exception");
		}
		
		return repo.save(updateEntity(user));
	
	}
	private SecurityUser updateEntity(SecurityUser user) {
		SecurityUser persistedUser = repo.findById(user.getId()).get();
		if(user.getUserName()!=null && user.getUserName().trim().length()>0) {
			persistedUser.setUserName(user.getUserName());
		}
		if(user.getEmail()!=null && user.getEmail().trim().length()>0) {
			persistedUser.setEmail(user.getEmail());
		}
		if(user.getPassword()!=null && user.getPassword().trim().length()>0) {
			persistedUser.setPassword(user.getPassword());
		}
		
		return persistedUser;
	}
	
	public SecurityUser findByName(String name) {
		SecurityUser user =repo.findByUserName(name);
		return user;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SecurityUser user = findByName(username);
		SecurityUserDetails userDetails = new SecurityUserDetails(user);
		return userDetails;
	}
}
