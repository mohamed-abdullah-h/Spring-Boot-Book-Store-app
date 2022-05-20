package com.main.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.main.entity.Author;
import com.main.exceptions.IDException;
import com.main.repository.AuthorRepo;

@Service
@Transactional
public class AuthorService {

	@Autowired
	private AuthorRepo repo;

	// @Cacheable(value = {"By_Author_ID"},key="#id")
	public Author findById(long id) {
		Author author = repo.findById(id).get();
		return author;
	}

	@Cacheable(value = { "All_Authors" })
	public List<Author> getAll() {
		return repo.findAll();
	}

//	@Caching(evict = {@CacheEvict(value = {"By_Author_ID"},key = "#id")
//					,@CacheEvict(value = {"All_Authors"},allEntries = true)})		
	public void deleteById(long id) {
		repo.deleteById(id);
	}

//	@CacheEvict(value = {"All_Authors"})
	public Author insert(Author author) {
		if (author.getId() > 0) {
			throw new IDException("Duplicate Id");
		}

		return repo.save(author);
	}

	public Author update(Author author) {
		if (author.getId() <= 0) {
			throw new IDException("Id Not found Exception");
		}

		return repo.save(updateEntity(author));
	}

	private Author updateEntity(Author author) {
		Author persistedAuthor = repo.findById(author.getId()).get();
		if (author.getName() != null && author.getName().trim().length() > 0) {
			persistedAuthor.setName(author.getName());
		}
		if (author.getBooks() != null) {
			persistedAuthor.setBooks(author.getBooks());
		}
		return persistedAuthor;
	}
//	@Cacheable(value = {"By_Book_ID"},key="#id")
	public List<Author> findByBookId(long id) {
		return repo.findByBookId(id);
	}
}
