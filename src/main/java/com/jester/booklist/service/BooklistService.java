package com.jester.booklist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.jester.booklist.model.Author;
import com.jester.booklist.model.Book;
import com.jester.booklist.repository.AuthorRepository;
import com.jester.booklist.repository.BookRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BooklistService {

	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;

	public List<Book> readBooks() {

		return bookRepository.findAll();
	}

	public Book readBook(Long bookId) {

		Optional<Book> book = bookRepository.findById(bookId);

		if (book.isPresent()) {
			return book.get();
		}
		throw new EntityNotFoundException("Cant find any book under given ID");
	}

	public Book createBook(BookCreationRequest request) {

		Optional<Author> author = authorRepository.findById(request.getAuthorId());
		if (!author.isPresent()) {
			throw new EntityNotFoundException("Author Not Found");
		}

		Book book = new Book();
		BeanUtils.copyProperties(request, book);
		book.setAuthor(author.get());

		return bookRepository.save(book);
	}

	public Book updateBook(BookCreationRequest request, Long bookId) {

		Optional<Author> author = authorRepository.findById(request.getAuthorId());
		if (!author.isPresent()) {
			throw new EntityNotFoundException("Author Not Found");
		}

		Optional<Book> bookOptional = bookRepository.findById(bookId);
		Book book = bookOptional.get();

		book.setTitle(request.getTitle());
		book.setAnotation(request.getAnotation());
		book.setOpinion(request.getOpinion());
		book.setStatus(request.getStatus());
		book.setAuthor(author.get());
		
		return bookRepository.save(book);
	}

	public void deleteBook(Long bookId) {

		bookRepository.deleteById(bookId);
	}

	public List<Author> readAuthors() {

		return authorRepository.findAll();
	}

	public Author readAuthor(Long authorId) {

		Optional<Author> author = authorRepository.findById(authorId);
		if (author.isPresent()) {
			return author.get();
		}
		throw new EntityNotFoundException("Cant find any author under given ID");
	}

	public Author createAuthor(AuthorCreationRequest request) {

		Author author = new Author();
		BeanUtils.copyProperties(request, author);

		return authorRepository.save(author);
	}
	
	public Author updateAuthor(AuthorCreationRequest request, Long authorId) {
		
		Optional<Author> authorOptional = authorRepository.findById(authorId);
		Author author = authorOptional.get();
		
		author.setFirstName(request.getFirstName());
		author.setLastName(request.getLastName());
		
		return authorRepository.save(author);
	}
	
	public void deleteAuthor(Long authorId) {
		
		authorRepository.deleteById(authorId);
	}
}
