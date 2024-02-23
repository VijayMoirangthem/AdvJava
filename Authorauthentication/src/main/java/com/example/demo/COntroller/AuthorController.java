package com.example.demo.COntroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Author;
import com.example.demo.Model.Book;
import com.example.demo.Services.AuthorService;

@RestController
@RequestMapping(value="/api")

public class AuthorController {
	@Autowired
	private AuthorService dservice;
	@PostMapping("/register")
	public ResponseEntity<String> createAuthor(@Validated @RequestBody Author author) {
		try {
		Book book = author.getBook();
		
		 // Establish the bi-directional relationship
		book.setAuthor(author);
        author.setBook(book);
				
        Author registeredDealer = dservice.registerAuthor(author);
	        if (registeredDealer!= null) {
	            return ResponseEntity.ok("Registration successful");
	        } else {
	            return ResponseEntity.badRequest().body("Registration failed");
	        }
		}
		catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An Error Ocurred: "+e.getMessage());
			
		}
	}
}
