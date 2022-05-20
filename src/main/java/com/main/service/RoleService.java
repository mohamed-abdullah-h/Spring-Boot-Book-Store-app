package com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.entity.Role;
import com.main.exceptions.IDException;
import com.main.repository.RoleRepo;

@Service
public class RoleService {

	@Autowired
	private RoleRepo repo;
	
	public Role findById(long id) {
		return repo.findById(id).get();
	}
	public List<Role> getAll(){
		return repo.findAll();
	}
	
	public void deleteById(long id) {
		repo.deleteById(id);
	}
	public Role insert(Role role) {
		if(role.getId()>0) {
			throw new IDException("Duplicate ID");
		}
		return repo.save(role);
	}
	public Role update(Role role) {
		if(role.getId()<=0) {
			throw new IDException("Id Not found Exception");
		}
		
		return repo.save(updateEntity(role));
	
	}
	private Role updateEntity(Role role) {
		Role persistedRole = repo.findById(role.getId()).get();
		if(role.getName()!=null && role.getName().trim().length()>0) {
			persistedRole.setName(role.getName());
		}
		
		return persistedRole;
	}
}
