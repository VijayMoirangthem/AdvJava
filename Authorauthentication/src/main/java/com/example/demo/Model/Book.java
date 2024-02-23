package com.example.demo.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Book 
{

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	int BOokid;
	@OneToOne(mappedBy ="Book", cascade = CascadeType.ALL)
	String BookName;
	float price;
	
	private Author author ;
	public int getBOokid() {
		return BOokid;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public void setBOokid(int bOokid) {
		BOokid = bOokid;
	}
	public String getBookName() {
		return BookName;
	}
	public void setBookName(String bookName) {
		BookName = bookName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
