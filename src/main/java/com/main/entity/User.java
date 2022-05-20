package com.main.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@SQLDelete(sql = "update user U set U.is_deleted=true where U.id=id")
@Where(clause = "is_deleted=false")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5182768048395824143L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank(message = "{javax.validation.name}")
	private String name;
	@ManyToMany()
	@JoinTable(name = "fav_user_books",joinColumns = {@JoinColumn(referencedColumnName = "id",name = "user_id")}
							,inverseJoinColumns = {@JoinColumn(referencedColumnName = "id",name = "book_id")})
	private Set<Book> fav_books= new HashSet<Book>();
	private boolean is_deleted;

	
	
}
