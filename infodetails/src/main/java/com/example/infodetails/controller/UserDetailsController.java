package com.example.infodetails.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.infodetails.model.UserDetails;
import com.example.infodetails.model.UserDetailsRequestDto;
import com.example.infodetails.service.UserDetailsService;

@RestController
@RequestMapping("/userdetails")
public class UserDetailsController {

	@Autowired
	UserDetailsService userDetailsService;
	
	@PostMapping("/save")
	public String saveUserAndUserDetails(@RequestBody UserDetailsRequestDto userDetailsRequestDto) {
		return userDetailsService.saveUserDetails(userDetailsRequestDto);
	}
	
	@GetMapping("/id")
	public UserDetails getUserDetailsById(Long id) {
		return userDetailsService.getUserDetailsByUserId(id);
	}
	
	@GetMapping("/all")
	public List<UserDetails> getAllUserDetails() {
		return userDetailsService.getAllUserDetails();
	}
}
