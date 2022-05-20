package com.main.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

	@Override
	@EntityGraph(attributePaths = {"authors","users"})
	public List<Book> findAll();
	
	public List<Book> findByName(String name);
	
	//restore the corresponding row from the state of(soft delete)
	@Modifying
	@Transactional
	@Query("update Book B set B.is_deleted = false where B.id = :id")
	public void restoreId(long id);

}
