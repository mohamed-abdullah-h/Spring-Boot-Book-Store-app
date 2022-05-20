package com.main.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.main.entity.User;
import com.main.repository.UserRepo;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class UserRepoTest {

	@Autowired
	private UserRepo repo;
	
	@Test
	public void insertDummyEntityTest() {
		
		User user = repo.save(new User(0,"DummyUser",null,true));
		assertEquals(user.getName(), "DummyUser");
	}
	@Test
	public void findDummyEntityTest() {
		
		List<User> users = repo.findByName("DummyUser");
		assert(users.size()==0);
	}
	
}
