package com.main.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.main.entity.User;
import com.main.exceptions.IDException;
import com.main.repository.UserRepo;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService service;
	@MockBean
	private UserRepo repo;
	
	@Test
	public void inserEntityWithUnvalidIdTest() {
		User user = new User(5, "dummy",null,false);
		assertThrowsExactly(IDException.class, () -> {
			service.insert(user);
		});

	}

	@Test
	public void insertEntityWithValidIdTest() {
		User user = new User(0, "dummy",null,false);
		Mockito.when(repo.save(user)).thenReturn(user);
		User returnedUser = service.insert(user);
		assertEquals(user.getName(), returnedUser.getName());

	}

	@Test
	public void updateEntityWithUnvalidIdTest() {
		User user = new User(0, "dummy",null,false);

		assertThrowsExactly(IDException.class, () -> {
			service.update(user);
		});

	}

	@Test
	public void updateEntityWithValidIdTest() {
		User user = new User(5, "dummy",null,false);
		Mockito.when(repo.findById(Mockito.anyLong())).thenReturn(Optional.of(user));
		Mockito.when(repo.save(user)).thenReturn(user);
		User returnedUser = service.update(user);
		assertEquals(user.getName(), returnedUser.getName());

	}

}
