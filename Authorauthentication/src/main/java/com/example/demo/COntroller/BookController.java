package com.example.demo.COntroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.Model.Book;
import com.example.demo.Services.BookServices;

@RestController
@RequestMapping(value="/api")

public class BookController 
{
	@Autowired
	private BookServices pservice;
	@PostMapping("/book")
	public ResponseEntity<Book> saveProduct(@Validated @RequestBody Book book) {
		try {
			Book p=pservice.saveBook(book);
				return ResponseEntity.status(HttpStatus.CREATED).body(p);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@GetMapping("/book")
	public ResponseEntity<List<Book>> getAllbook(){
		try {
			List<Book> book= pservice.listAll();
			return ResponseEntity.ok(book);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	@GetMapping("/book/{id}")
	public ResponseEntity<Book> getProductById(@PathVariable(value="id") int pId)
				throws com.example.demo.Exception.ResourceNotFoundException {
		Book p=pservice.getSingleBook(pId).
					orElseThrow(() -> new com.example.demo.Exception.ResourceNotFoundException("Product Not Found for this Id :"+pId));
			
			return ResponseEntity.ok().body(p);
	}
	@PutMapping("/products/{id}")
	public ResponseEntity<Book> updateProduct(@PathVariable(value="id") int pId,
			@Validated @RequestBody Book p) throws com.example.demo.Exception.ResourceNotFoundException {
		
		Book book=pservice.getSingleBook(pId).
				orElseThrow(() -> new com.example.demo.Exception.ResourceNotFoundException("Product Not Found for this Id :"+pId));
		//Update product with new values
		book.setBookName(p.getBookName());
		
		book.setPrice(p.getPrice());
		
		
		final Book updatedBook=pservice.saveBook(book);
		return ResponseEntity.ok().body(updatedBook);
}
	@DeleteMapping("/products/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteProduct(@PathVariable(value="id") int pId)
			throws com.example.demo.Exception.ResourceNotFoundException{
		
		pservice.getSingleBook(pId).
		orElseThrow(() -> new com.example.demo.Exception.ResourceNotFoundException("Product Not found for thid Id :"+pId));
		
		pservice.deleteBook(pId);
		
		Map<String,Boolean> response=new HashMap<String, Boolean>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}