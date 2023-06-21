package com.jester.booklist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jester.booklist.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{

}
