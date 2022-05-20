package com.main.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.main.entity.Book;
import com.main.repository.BookRepo;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class BookRepoTest {

	@Autowired
	private BookRepo repo;
	
	//insert dummy row with deleted equal true,i.e this row will not be retrieved by queries(soft delete)
	@Test
	public void insertDummyEntityTest() {
		
		Book book = repo.save(new Book(0,"DummyBook",6,null,null,true));
		assertEquals(book.getName(), "DummyBook");
	}
	@Test
	public void findDummyEntityTest() {
		
		List<Book> books = repo.findByName("DummyBook");
		assert(books.size()==0);
	}
	// insert Book with unValid price (price must > 5)
	@Test
	public void insertBookWithUnValidPriceTest() {
		Book book = new Book(5, "dummy", 4, null, null, false);
		
		assertThrows(RuntimeException.class,()->{
			 repo.save(book);
		});

	}

	
}
