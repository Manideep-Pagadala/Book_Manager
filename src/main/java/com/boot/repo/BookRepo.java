package com.boot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.entity.Book;

public interface BookRepo extends JpaRepository<Book, Integer> {

	List<Book> findByNameIgnoreCaseContaining(String name);
}
