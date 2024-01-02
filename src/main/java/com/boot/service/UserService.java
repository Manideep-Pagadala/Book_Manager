package com.boot.service;

import java.util.Optional;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.boot.entity.User;
import com.boot.repo.UserRepo;

@Service
public class UserService {

	private UserRepo repo;
	private JavaMailSender sender;

	public UserService(UserRepo repo, JavaMailSender sender) {
		this.repo = repo;
		this.sender = sender;
	}

	public Boolean save(User user) {
		User save = repo.save(user);
		return save.getUserName() != null;
	}

	public User find(String userName) {
		Optional<User> byId = repo.findById(userName);
		if (byId.isPresent())
			return byId.get();
		else
			return null;
	}
	
	// to send email
	public void mailSender(User user) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(user.getEmail());
		sm.setSubject("Registration");
		sm.setText("");
		sender.send(sm);

	}

}
