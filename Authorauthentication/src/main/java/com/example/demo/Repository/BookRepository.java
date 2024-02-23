package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Book;



public interface BookRepository extends JpaRepository<Book, Integer>
{
	public Optional<Book> findByEmail(String email);
}
