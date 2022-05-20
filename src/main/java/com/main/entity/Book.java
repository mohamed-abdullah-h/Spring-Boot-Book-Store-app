package com.main.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

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
@SQLDelete(sql = "update book B set B.is_deleted=true where B.id=id")
@Where(clause = "is_deleted=false")
public class Book implements Serializable{

	
	private static final long serialVersionUID = -2331670182391414396L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank(message = "{javax.validation.name}")
	private String name;
	@Min(value = 5,message = "{javax.validation.price}")
	private double price;
	@ManyToMany(mappedBy = "fav_books")
	private Set<User> users = new HashSet<User>();
	@ManyToMany(mappedBy = "books")
	private Set<Author> authors = new HashSet<Author>();
	private boolean is_deleted;

	public void addAuthor(Author author) {
		authors.add(author);
	}
	
	
}
