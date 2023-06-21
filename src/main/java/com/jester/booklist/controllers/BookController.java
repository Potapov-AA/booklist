package com.jester.booklist.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jester.booklist.model.Book;
import com.jester.booklist.service.BookCreationRequest;
import com.jester.booklist.service.BooklistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/books")
@RequiredArgsConstructor
public class BookController {

	private final BooklistService booklistService;

	@GetMapping()
	public ResponseEntity<List<Book>> readBooks() {

		final List<Book> books = booklistService.readBooks();

		return books != null && !books.isEmpty() 
				? new ResponseEntity<List<Book>>(books, HttpStatus.OK)
				: new ResponseEntity<List<Book>>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{bookId}")
	public ResponseEntity<Book> readBook(@PathVariable(name = "bookId") Long bookId) {

		final Book book = booklistService.readBook(bookId);

		return book != null 
				? new ResponseEntity<Book>(book, HttpStatus.OK)
				: new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
	}

	@PostMapping()
	public ResponseEntity<Book> createBook(@RequestBody BookCreationRequest request) {
		
		final Book book = booklistService.createBook(request);
		
		return book != null 
				? new ResponseEntity<Book>(book, HttpStatus.CREATED)
				: new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
	}
	
	@PatchMapping("/{bookId}")
	public ResponseEntity<Book> updateBook(@PathVariable(name = "bookId") Long bookId, @RequestBody BookCreationRequest request) {
		
		final Book book = booklistService.updateBook(request, bookId);
		
		return book != null 
				? new ResponseEntity<Book>(book, HttpStatus.OK)
				: new ResponseEntity<Book>(HttpStatus.NOT_MODIFIED);
						
	}
	
	@DeleteMapping("/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable(name = "bookId") Long bookId) {
		
		booklistService.deleteBook(bookId);
		
		return ResponseEntity.ok().build();
	}
}
