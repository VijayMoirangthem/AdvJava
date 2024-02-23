package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Author;



public interface AuthorRepository extends JpaRepository<Author, Integer> {

	Optional<Author> findByEmail(String email);

}
