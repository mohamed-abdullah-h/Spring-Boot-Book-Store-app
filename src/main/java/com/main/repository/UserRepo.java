package com.main.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

	@Override
	@EntityGraph(attributePaths = { "fav_books" })
	public List<User> findAll();

	public List<User> findByName(String name);

	// restore the corresponding row from the state of(soft delete)
	@Modifying
	@Transactional
	@Query("update User U set U.is_deleted = false where U.id = :id")
	public void restoreId(long id);
}
