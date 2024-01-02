package com.boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.boot.entity.Book;
import com.boot.repo.BookRepo;

@Service
public class BookService {

	@Autowired
	private BookRepo repo;

	// Retrieve all records
	public List<Book> allRecords() {
		return repo.findAll();
	}

	// save record
	public Boolean saveRecord(Book book) {
		Book savedBook = repo.save(book);
		return savedBook.getId() != null;
	}

	// delete By Id
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

	// findById
	public Book getById(Integer id) {
		Optional<Book> byId = repo.findById(id);
		if (byId.isPresent()) {
			return byId.get();
		} else {
			return null;
		}
	}

	// pagination
	public Page<Book> customPagination(int pageno, int pagesize) {
		
		return repo.findAll(PageRequest.of(pageno - 1, pagesize));

	}

	// with sorting
	public List<Book> booksWithSort(String feild) {
		return repo.findAll(Sort.by(Sort.Direction.ASC, feild));
	}

	// search by name case insensitive
	public List<Book> searchByName(String name) {
		return repo.findByNameIgnoreCaseContaining(name);
	}

}
