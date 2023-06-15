package com.jester.booklist.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jester.booklist.models.Book;
import com.jester.booklist.repo.BookRepository;

@Controller
@RequestMapping(path="/books")
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@GetMapping
	public String BooksGet(Model model) {
		Iterable<Book> books = bookRepository.findAll();
		
		model.addAttribute("books", books);
		model.addAttribute("title", "Мои книги");
		
		return "books/books";
	}
	
	@GetMapping("/add")
	public String BookAddGet(Model model) {
		
		model.addAttribute("title", "Добавить книгу");
		return "books/books-add";
	}
	
	@PostMapping("/add")
	public String BookAddPost(@RequestParam String title,
			@RequestParam String author,
			@RequestParam String anotation,
			@RequestParam String opinion,
			@RequestParam(required=true, defaultValue = "off") String status,
			Model model) {
		
		Boolean read = false;
		if (status.equals("on")) {
			read = true;
		}
		
		Book book = new Book(title, author, anotation, opinion, read);
		bookRepository.save(book);
		return "redirect:/books";
	}
	
	@GetMapping("/{id}")
	public String BookOneGet(@PathVariable(value = "id") long id,
			Model model) {
		
		if(!bookRepository.existsById(id)) {
			return "redirect:/books";
		}
		
		Optional<Book> book = bookRepository.findById(id);
		ArrayList<Book> arrayList = new ArrayList<>();
		book.ifPresent(arrayList::add);
		model.addAttribute("book", arrayList);
		return "books/books-one";
	}
	
	@GetMapping("/{id}/edit")
	public String BookEditGet(@PathVariable(value = "id") long id,
			Model model) {
		
		if(!bookRepository.existsById(id)) {
			return "redirect:/books";
		}
		
		Optional<Book> book = bookRepository.findById(id);
		ArrayList<Book> arrayList = new ArrayList<>();
		book.ifPresent(arrayList::add);
		model.addAttribute("book", arrayList);
		return "books/books-edit";
	}
	
	@PostMapping("{id}/edit")
	public String BookEditPost(@PathVariable(value = "id") long id,
			@RequestParam String title,
			@RequestParam String author,
			@RequestParam String anotation,
			@RequestParam String opinion,
			@RequestParam(required=true, defaultValue = "off") String status,
			Model model) {
		
		Boolean read = false;
		if (status.equals("on")) {
			read = true;
		}
		
		Book book = bookRepository.findById(id).orElseThrow();
		book.setTitle(title);
		book.setAuthor(author);
		book.setAnotation(anotation);
		book.setOpinion(opinion);
		book.setStatus(read);
		
		bookRepository.save(book);
		
		return "redirect:/books";
	}
	
	@PostMapping("{id}/remove")
	public String BookRemove(@PathVariable(value = "id") long id,
			Model model) {
		
		Book book = bookRepository.findById(id).orElseThrow();
		
		bookRepository.delete(book);
		
		return "redirect:/books";
	}
}
