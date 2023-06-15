package com.jester.booklist.repo;

import org.springframework.data.repository.CrudRepository;

import com.jester.booklist.models.Book;

public interface BookRepository extends CrudRepository<Book, Long>{

}
