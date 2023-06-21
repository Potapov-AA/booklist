package com.jester.booklist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jester.booklist.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
