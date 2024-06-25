package com.example.info.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.example.info.model.User;
import com.example.info.model.UserDetailsRequestDto;
import com.example.info.model.UserDetailsResponse;
import com.example.info.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RestTemplate restTemplate;

	public String saveUserAndUserDetails(UserDetailsRequestDto userDetailsRequestDto) {
		User user = new User();
		user.setUserName(userDetailsRequestDto.getUserName());
		user.setPassword(userDetailsRequestDto.getPassword());
		User usr = userRepository.save(user);
		ResponseEntity<String> response = null;
		if (usr.getId() != null) {
			userDetailsRequestDto.setUserId(usr.getId());
			String uri = "http://localhost:8093/userdetails/save";
//			UserDetailsRequestDto userDetailsRequest = new UserDetailsRequestDto(userDetailsRequestDto.getName(), userDetailsRequestDto.getEmailId(), userDetailsRequestDto.getDob(), userDetailsRequestDto.getMobile(),
//					userDetailsRequestDto.getAddress(), usr.getId());

			response = restTemplate.postForEntity(uri, userDetailsRequestDto, String.class);
		}

		return (String) response.getBody();
	}

	public UserDetailsResponse getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
			userDetailsResponse.setUser(user.get());
			return userDetailsResponse;
		} else {
			return null;
		}
	}

	public List<User> getAllUser() {
		List<User> users = userRepository.findAll();
		if (!CollectionUtils.isEmpty(users)) {
			return users;
		} else {
			return new ArrayList<>();
		}
	}

	public List<UserDetailsResponse> getAllUsersAndUserDetails() {
		List<UserDetailsResponse> userDetailsResponses = new ArrayList<>();
		List<User> users = userRepository.findAll();
		if (!CollectionUtils.isEmpty(users)) {
			for (User user : users) {
				UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
				userDetailsResponse.setUser(user);
				userDetailsResponses.add(userDetailsResponse);
			}
			return userDetailsResponses;
		} else {
			return null;
		}
	}
}
