package com.example.infodetails.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.infodetails.model.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long>{

	UserDetails findByUserId(Long id);

}
