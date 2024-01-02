package com.boot.exceptionHandler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookExceptionHandler {
	@ExceptionHandler(value = Exception.class)
	public String handleException(Exception e, Model model) {
		model.addAttribute("errMsg", e.getMessage());

		return "errorPage";
	}
}
