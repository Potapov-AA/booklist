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

import com.jester.booklist.model.Author;
import com.jester.booklist.service.AuthorCreationRequest;
import com.jester.booklist.service.BooklistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/author")
@RequiredArgsConstructor
public class AuthorController {
	
	private final BooklistService booklistService;

	@GetMapping()
	public ResponseEntity<List<Author>> readAuthors() {

		final List<Author> authors = booklistService.readAuthors();

		return authors != null && !authors.isEmpty() 
				? new ResponseEntity<List<Author>>(authors, HttpStatus.OK)
				: new ResponseEntity<List<Author>>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{authorId}")
	public ResponseEntity<Author> readAuthor(@PathVariable(name = "authorId") Long authorId) {

		final Author author = booklistService.readAuthor(authorId);

		return author != null 
				? new ResponseEntity<Author>(author, HttpStatus.OK)
				: new ResponseEntity<Author>(HttpStatus.NOT_FOUND);
	}

	@PostMapping()
	public ResponseEntity<Author> createAuthor(@RequestBody AuthorCreationRequest request) {
		
		final Author author = booklistService.createAuthor(request);
		
		return author != null 
				? new ResponseEntity<Author>(author, HttpStatus.CREATED)
				: new ResponseEntity<Author>(HttpStatus.NOT_FOUND);
	}
	
	@PatchMapping("/{authorId}")
	public ResponseEntity<Author> updateAuthor(@PathVariable(name = "authorId") Long authorId, 
			@RequestBody AuthorCreationRequest request) {
		
		final Author author = booklistService.updateAuthor(request, authorId);
		
		return author != null 
				? new ResponseEntity<Author>(author, HttpStatus.OK)
				: new ResponseEntity<Author>(HttpStatus.NOT_MODIFIED);
						
	}
	
	@DeleteMapping("/{authorId}")
	public ResponseEntity<Void> deleteAuthor(@PathVariable(name = "authorId") Long authorId) {
		
		booklistService.deleteAuthor(authorId);
		
		return ResponseEntity.ok().build();
	}
}
