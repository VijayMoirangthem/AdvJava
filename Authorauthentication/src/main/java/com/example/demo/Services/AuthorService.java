package com.example.demo.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.Model.Author;
import com.example.demo.Repository.AuthorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class AuthorService 
{
	@Autowired
	private AuthorRepository drepo;
	
	public Author registerAuthor(Author d) {
		return drepo.save(d);
	}
	public Optional<Author> loginAuthor(String email) {

		return drepo.findByEmail(email); // Invoke Custom method
	}
	
	
	
}
