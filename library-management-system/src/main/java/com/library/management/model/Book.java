package com.library.management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="books")
public class Book {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank private String title;
 @NotBlank private String author;
 @Column(unique=true, nullable=false) private String isbn;
 private boolean available=true;
 public Book() {}
 public Book(String title,String author,String isbn){this.title=title;this.author=author;this.isbn=isbn;}
 public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public String getAuthor(){return author;} public void setAuthor(String v){author=v;} public String getIsbn(){return isbn;} public void setIsbn(String v){isbn=v;}
 public boolean isAvailable(){return available;} public void setAvailable(boolean v){available=v;}
}
