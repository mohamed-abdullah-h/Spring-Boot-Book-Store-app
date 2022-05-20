package com.main.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.main.entity.Author;
import com.main.repository.AuthorRepo;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class AuthorRepoTest {

	@Autowired
	private AuthorRepo repo;
	
	//insert dummy row with deleted equal true,i.e this row will not be retrieved by queries(soft delete)
	@Test
	public void insertDummyEntityTest() {
		
		Author author = repo.save(new Author(0,"DummyAuthor",0,true,null));
		assertEquals(author.getName(), "DummyAuthor");
	}
	@Test
	public void findDummyEntityTest() {
		
		List<Author> authors = repo.findByName("DummyAuthor");
		assert(authors.size()==0);
	}
	
}
