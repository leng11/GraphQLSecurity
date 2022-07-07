package com.example.secureGroupQLResource.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.example.secureGroupQLResource.model.Book;
import com.example.secureGroupQLResource.security.Unsecured;
import com.example.secureGroupQLResource.service.BookService;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class QLQuery implements GraphQLQueryResolver {
	@Autowired
	private BookService bookService;
	
	@PreAuthorize("hasRole('BOOK_SERVICE_READER')")
	public List<Book> list() {	
		List<Book> books = books("ALL");
		log.info("return number of book: " + books.size());
		return books;
	}
	
	@PreAuthorize("hasRole('BOOK_SERVICE_READER')")
	public List<Book> books(final String bookNameFilter) {
		return bookService.getBooks().stream().
										filter(b-> {
												if(b.getName().contains(bookNameFilter) ||
														bookNameFilter.equalsIgnoreCase("All")) {
													return true;
												}
												return false;
											}).
										collect(Collectors.toList());
	}
	
	public Book book(long id) {
		return bookService.getBookDetail(id);
	}
	
	@Unsecured
	public List<Book> bookByAuthor(final String author) {
		return bookService.getBookByAuthor(author);
	}

}
