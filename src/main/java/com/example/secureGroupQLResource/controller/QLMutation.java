package com.example.secureGroupQLResource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.example.secureGroupQLResource.model.Book;
import com.example.secureGroupQLResource.service.BookService;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
public class QLMutation implements GraphQLMutationResolver {
	@Autowired
	private BookService bookService;

//	@PreAuthorize("hasRole('BOOK_SERVICE_ADMIN')")
	public Book addBook(final String name, final String author) {
		return bookService.addBook(name, author);
	}
}
