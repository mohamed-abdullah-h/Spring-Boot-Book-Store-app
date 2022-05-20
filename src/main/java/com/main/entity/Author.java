package com.main.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SQLDelete(sql = "update author A set A.is_deleted=true where A.id=?")
@Where(clause = "is_deleted=false")
public class Author implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6072253744890173346L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank(message = "{javax.validation.name}")
	private String name;
	@Formula(value = "(SELECT COUNT(*) FROM authors_books books where books.author_id=id)")
	private int numOfBooks;
	private boolean is_deleted;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "authors_books",joinColumns = {@JoinColumn(referencedColumnName = "id",name = "author_id")}
	,inverseJoinColumns = {@JoinColumn(referencedColumnName = "id",name = "books_id")})
	private Set<Book> books = new HashSet<Book>();
	
	
}
