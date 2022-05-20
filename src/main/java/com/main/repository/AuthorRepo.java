package com.main.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.entity.Author;

@Repository
public interface AuthorRepo extends JpaRepository<Author,Long> {

	@Query(value = "select author from Author author join author.books bs where bs.id = :bookId")
	@EntityGraph(attributePaths = {"books"})
	public List<Author> findByBookId(long bookId); 
	
	@Override
	@EntityGraph(attributePaths = {"books"})
	public List<Author> findAll();
	
	public List<Author> findByName(String name);
	//restore the corresponding row from the state of(soft delete)
	@Modifying
	@Transactional
	@Query("update Author A set A.is_deleted = false where A.id = :id")
	public void restoreId(long id);
}
