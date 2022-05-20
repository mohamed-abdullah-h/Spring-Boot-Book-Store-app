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

import com.main.entity.Role;
import com.main.exceptions.IDException;
import com.main.repository.RoleRepo;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class RoleServiceTest {

	@Autowired
	private RoleService service;
	@MockBean
	private RoleRepo repo;
	@Test
	public void inserEntityWithUnvalidIdTest() {
		Role role = new Role(2,"dummy");
		assertThrowsExactly(IDException.class, () -> {
			service.insert(role);
		});

	}

	@Test
	public void insertEntityWithValidIdTest() {
		Role role = new Role(0,"dummy");
		Mockito.when(repo.save(role)).thenReturn(role);
		Role returnedRole = service.insert(role);
		assertEquals(role.getName(), returnedRole.getName());

	}

	@Test
	public void updateEntityWithUnvalidIdTest() {
		Role role = new Role(0,"dummy");

		assertThrowsExactly(IDException.class, () -> {
			service.update(role);
		});

	}

	@Test
	public void updateEntityWithValidIdTest() {
		Role role = new Role(3,"dummy");
		Mockito.when(repo.findById(Mockito.anyLong())).thenReturn(Optional.of(role));
		Mockito.when(repo.save(role)).thenReturn(role);
		
		Role returnedRole = service.update(role);
		assertEquals(role.getName(), returnedRole.getName());

	}
}
