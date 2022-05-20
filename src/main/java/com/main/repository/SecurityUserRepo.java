package com.main.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.SecurityUser;

@Repository
public interface SecurityUserRepo extends JpaRepository<SecurityUser,Long> {
	
	@EntityGraph(attributePaths = {"roles"})
	public SecurityUser findByUserName(String userName);
	
}
