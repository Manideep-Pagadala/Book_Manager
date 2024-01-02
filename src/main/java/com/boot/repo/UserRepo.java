package com.boot.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.entity.User;

public interface UserRepo extends JpaRepository<User, String> {

}
