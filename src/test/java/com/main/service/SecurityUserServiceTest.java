package com.main.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.main.entity.SecurityUser;
import com.main.exceptions.IDException;
import com.main.repository.SecurityUserRepo;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class SecurityUserServiceTest {

	@Autowired
	private SecurityUserService service;
	
	@MockBean
	private SecurityUserRepo repo;
	 
	
	@Test
	public void inserEntityWithUnvalidIdTest() {
		SecurityUser user = new SecurityUser(2,"dummy","moh@gmail.com","12345",true,false,null);
		assertThrowsExactly(IDException.class, () -> {
			service.insert(user);
		});

	}

	@Test
	public void insertEntityWithValidIdTest() {
		SecurityUser user = new SecurityUser(0,"dummy","moh@gmail.com","12345",true,false,null);
		Mockito.when(repo.save(user)).thenReturn(user);
		SecurityUser returnedUser = service.insert(user);
		assertEquals(user.getUserName(), returnedUser.getUserName());

	}

	@Test
	public void updateEntityWithUnvalidIdTest() {
		SecurityUser user = new SecurityUser(0,"dummy","moh@gmail.com","12345",true,false,null);

		assertThrowsExactly(IDException.class, () -> {
			service.update(user);
		});

	}

	@Test
	public void updateEntityWithValidIdTest() {
		SecurityUser user = new SecurityUser(2,"dummy","moh@gmail.com","12345",true,false,null);
		Mockito.when(repo.findById(Mockito.anyLong())).thenReturn(Optional.of(user));
		Mockito.when(repo.save(user)).thenReturn(user);
		SecurityUser returnedUser = service.update(user);
		assertEquals(user.getUserName(), returnedUser.getUserName());

	}

	
}
