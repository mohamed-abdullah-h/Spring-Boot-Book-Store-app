package com.main.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.main.entity.Book;
import com.main.exceptions.IDException;
import com.main.repository.BookRepo;

@SpringBootTest
public class BookServiceTest {

	@Autowired
	private BookService service;
	@MockBean
	private BookRepo repo;

	@Test
	public void updateEntityWithUnvalidIdTest() {
		Book book = new Book(0, "dummy", 0, null, null, false);
		Mockito.when(repo.save(book)).thenReturn(book);

		assertThrowsExactly(IDException.class, () -> {
			service.update(book);
		});

	}

	@Test
	public void updateEntityWithValidIdTest() {
		Book book = new Book(4, "dummy", 0, null, null, false);
		Mockito.when(repo.save(book)).thenReturn(book);
		Mockito.when(repo.findById(Mockito.anyLong())).thenReturn(Optional.of(book));
		Book returnedBook = service.update(book);
		assertEquals(book.getName(), returnedBook.getName());
	}

	@Test
	public void insertBookWithUnvalidIdTest() {
		Book book = new Book(5, "dummy", 0, null, null, false);
		Mockito.when(repo.findById(Mockito.anyLong())).thenReturn(Optional.of(book));
		Mockito.when(repo.save(book)).thenReturn(book);
		
		assertThrowsExactly(IDException.class, () -> {
			service.insert(book);
		});

	}

	@Test
	public void insertBookWithValidIdTest() {
		Book book = new Book(0, "dummy", 0, null, null, false);
		Mockito.when(repo.findById(Mockito.anyLong())).thenReturn(Optional.of(book));
		Mockito.when(repo.save(book)).thenReturn(book);
		Book returnedBook = service.insert(book);
		assertEquals(book.getName(), returnedBook.getName());

	}

	

}
