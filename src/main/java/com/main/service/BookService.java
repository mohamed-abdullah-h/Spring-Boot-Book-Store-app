package com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.entity.Book;
import com.main.exceptions.IDException;
import com.main.repository.BookRepo;

@Service
public class BookService {

	@Autowired
	private BookRepo repo;
	
	public Book findById(long id) {
		return repo.findById(id).get();
	}
	public List<Book> getAll(){
		return repo.findAll();
	}
	
	public void deleteById(long id) {
		repo.deleteById(id);
	}
	public Book insert(Book book) {
		if(book.getId()>0) {
			throw new IDException("Duplicate Id");
		}
		return repo.save(book);
	}
	public Book update(Book book) {
		if(book.getId()<=0) {
			throw new IDException("Id Not found Exception");
		}
		
		return repo.save(updateEntity(book));
	}
		
	
	private Book updateEntity(Book book) {
		Book persistedBook = repo.findById(book.getId()).get();
		if(book.getName()!=null && book.getName().trim().length()>0) {
			persistedBook.setName(book.getName());
		}
		if(book.getPrice() > 0 ) {
			persistedBook.setPrice(book.getPrice());
		}
		return persistedBook;
	}
}
