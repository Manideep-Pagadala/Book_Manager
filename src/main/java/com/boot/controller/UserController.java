package com.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.entity.User;
import com.boot.service.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {

	@Autowired
	private UserService userv;

	// show Registration form
	@GetMapping("/reg")
	public String showUserForm(Model model) {
		model.addAttribute("user", new User());
		return "regForm";
	}

	// to register new user
	@PostMapping("/saveUser")
	public String saveData(@Valid User user, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("err", "Vaidation Failed , Plese follow Form patterns");
			return "regForm";
		}
		userv.mailSender(user);
		Boolean status = userv.save(user);
		if (status) {
			model.addAttribute("message", "Registarion successful,Kindly check your mail and please login");
			return "home";
		} else {
			model.addAttribute("message", "Login Failed");
			return "home";
		}
	}

	// show login form
	@GetMapping("/login")
	public String showLoginForm(Model model) {
		model.addAttribute("user", new User());
		return "loginForm";
	}

	// validate user
	@PostMapping("/validateUser")
	public String validateUser(@RequestParam("userName") String userName, @RequestParam("password") String password,
			Model model) {
		User userStatus = userv.find(userName);

		if (userStatus != null && userStatus.getPassword().equals(password)) {

			return "redirect:/books";
		} else {
			model.addAttribute("message", "Login Failed");
			return "home";
		}
	}

}
