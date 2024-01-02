package com.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.entity.Book;
import com.boot.service.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService ser;

	@GetMapping("/index")
	public String mainPage() {
		
		return "home";
	}

	@GetMapping("/books")
	public String getAllrecords(Model model) {

		return getProductsWithPagination(1, model);
	}

	@GetMapping("/showForm")
	public String show(Model model) {
		model.addAttribute("book", new Book());
		return "form";
	}

	@PostMapping("/save")
	public String saveData(Book book, Model model) {
		Boolean status = ser.saveRecord(book);
		if (status)
			model.addAttribute("msg", "Record saved successfully");
		else
			model.addAttribute("msg", "Unable to save the record");
		model.addAttribute("book", new Book());
		return "form";
	}

	@GetMapping("/del")
	public String delete(@RequestParam("id") Integer id, Model model) {
		ser.deleteById(id);
		model.addAttribute("books", ser.allRecords());
		return "books";
	}

	@GetMapping("/edit")
	public String edit(Model model, @RequestParam("id") Integer id) {
		model.addAttribute("book", ser.getById(id));
		return "form";
	}

	@GetMapping("/search")
	public String searchByUsingName(@RequestParam("name") String name, Model model) {

		model.addAttribute("books", ser.searchByName(name));
		return "books";
	}

	@GetMapping("/page/{pageNo}")
	public String getProductsWithPagination(@PathVariable Integer pageNo, Model model) {
		Integer pazeSize = 6;
		Page<Book> pagedBooks = ser.customPagination(pageNo, pazeSize);
		List<Book> listBooks = pagedBooks.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", pagedBooks.getTotalPages());
		model.addAttribute("totalItems", pagedBooks.getTotalElements());
		model.addAttribute("books", listBooks);
		return "books";
	}

}
