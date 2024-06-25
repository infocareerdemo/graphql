package com.example.infodetails.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.infodetails.model.UserDetails;
import com.example.infodetails.model.UserDetailsRequestDto;
import com.example.infodetails.repository.UserDetailsRepository;

@Service
public class UserDetailsService {

	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	public String saveUserDetails(UserDetailsRequestDto userDetailsRequestDto) {
		UserDetails userDetails = new UserDetails();
		userDetails.setName(userDetailsRequestDto.getName());
		userDetails.setEmailId(userDetailsRequestDto.getEmailId());
		userDetails.setDob(userDetailsRequestDto.getDob());
		userDetails.setMobile(userDetailsRequestDto.getMobile());
		userDetails.setAddress(userDetailsRequestDto.getAddress());
		userDetails.setUserId(userDetailsRequestDto.getUserId());
		userDetailsRepository.save(userDetails);
		
		return "saved";
	}
	
	public UserDetails getUserDetailsByUserId(Long id) {
		UserDetails userDetails = userDetailsRepository.findByUserId(id);
		if (userDetails != null) {
			return userDetails;
		} else {
			return null;
		}
	}
	
	public List<UserDetails> getAllUserDetails() {
		List<UserDetails> userDetails = userDetailsRepository.findAll();
		if (!CollectionUtils.isEmpty(userDetails)) {
			return userDetails;
		} else {
			return null;
		}
	}
}
