package com.main.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.main.entity.Author;
import com.main.exceptions.IDException;
import com.main.repository.AuthorRepo;

@SpringBootTest
public class AutherServiceTest {

	@Autowired
	private AuthorService service;
	@MockBean
	private AuthorRepo repo;
	
	@Test
	public void inserEntityWithUnvalidIdTest() {
		Author author = new Author(5, "dummy", 0, false, null);
		Mockito.when(repo.save(author)).thenReturn(author);

		assertThrowsExactly(IDException.class, () -> {
			service.insert(author);
		});

	}

	@Test
	public void insertEntityWithValidIdTest() {
		Author author = new Author(0, "dummy", 0, false, null);
		Mockito.when(repo.save(author)).thenReturn(author);
		Author returnedAuthor = service.insert(author);
		assertEquals(author.getName(), returnedAuthor.getName());

	}

	@Test
	public void updateEntityWithUnvalidIdTest() {
		Author author = new Author(0, "dummy", 0, false, null);
		Mockito.when(repo.save(author)).thenReturn(author);

		assertThrowsExactly(IDException.class, () -> {
			service.update(author);
		});

	}

	@Test
	public void updateEntityWithValidIdTest() {
		Author author = new Author(3, "dummy", 0, false, null);
		Mockito.when(repo.findById(Mockito.anyLong())).thenReturn(Optional.of(author));
		Mockito.when(repo.save(author)).thenReturn(author);
		Author returnedAuthor = service.update(author);
		assertEquals(author.getName(), returnedAuthor.getName());

	}

}
