package com.example.info.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.info.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
