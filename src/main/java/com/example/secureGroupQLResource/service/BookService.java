package com.example.secureGroupQLResource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.secureGroupQLResource.model.Book;
import com.example.secureGroupQLResource.model.BookRepo;

@Service
public class BookService {
	
	@Autowired
	private BookRepo bookRepo;
	
	public List<Book> getBooks() {
		return bookRepo.findAll();
	}
	
	public Book getBookDetail(long id) {
		return bookRepo.findById(id).orElse(null);
	}
	
	public Book addBook(final String name, final String author) {
		return bookRepo.save(Book.builder().name(name).author(author).build());
	}

	public List<Book> getBookByAuthor(final String author) {
		return bookRepo.findByAuthor(author);
	}
}
