package com.main.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.main.entity.SecurityUser;
import com.main.repository.SecurityUserRepo;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class SecurityUserRepoTest {

	@Autowired
	private SecurityUserRepo repo;
	private long dummyId=0;
	
	@Test
	public void insertDummyEntityTest() {
		
		SecurityUser user = repo.save(new SecurityUser(0,"dummy","moh@gmail.com","12345",true,false,null));
		dummyId = user.getId();
		assertEquals(user.getUserName(), "dummy");
	}
	@AfterAll
	public void removedummyRow() {
		repo.deleteById(dummyId);
	}
	
}
