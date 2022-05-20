package com.main.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.main.entity.Role;
import com.main.repository.RoleRepo;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class RoleRepoTest {

	@Autowired
	private RoleRepo repo;
	private long dummyId=0;
	
	//insert dummy row with deleted equal true,i.e this row will not be retrieved by queries(soft delete)
	@Test
	public void insertDummyEntityTest() {
		
		Role role = repo.save(new Role(0,"dummy"));
		dummyId = role.getId();
		assertEquals(role.getName(), "dummy");
	}
	@AfterAll
	public void removedummyRow() {
		repo.deleteById(dummyId);
	}
	
}
