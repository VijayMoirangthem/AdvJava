package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.Model.Book;
import com.example.demo.Repository.BookRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookServices 
{
	@Autowired
	private BookRepository prepo;
	
	public Book saveBook(Book p) {
		return prepo.save(p);   
	}
	
	 public List<Book> listAll(){
	 		
	 		return prepo.findAll(); 
	 	}
		public Optional<Book> getSingleBook(int id){
			return prepo.findById(id); 
		}
		public void deleteBook(int id) {
			 prepo.deleteById(id); 
		}
	
	
}
