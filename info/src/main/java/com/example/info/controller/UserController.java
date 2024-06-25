package com.example.info.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.yaml.snakeyaml.util.ArrayUtils;

import com.example.info.model.User;
import com.example.info.model.UserDetailsRequestDto;
import com.example.info.model.UserDetailsResponse;
import com.example.info.model.userDetails;
import com.example.info.service.UserService;

//@RestController
//@RequestMapping("/user")
@Controller
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	RestTemplate restTemplate;

	@PostMapping("/save")
	public ResponseEntity<Object> saveUserAndUserDetails(@RequestBody UserDetailsRequestDto userDetailsRequestDto) {
		return new ResponseEntity<Object>(userService.saveUserAndUserDetails(userDetailsRequestDto), HttpStatus.OK);
	}

//	@GetMapping("/id")
	@QueryMapping("getUserById")
	public UserDetailsResponse getUserById(@Argument Long id) {
		UserDetailsResponse userDetailsResponse = userService.getUserById(id);
		if (userDetailsResponse.getUser() != null) {
			String uri = "http://localhost:8093/userdetails/id?id=" + id;
			userDetails response = restTemplate.getForObject(uri, userDetails.class, id);
			if (response != null) {
				userDetailsResponse.setUserDetails(response);
			}
		}
		return userDetailsResponse;
	}

//	@GetMapping("/all")
	@QueryMapping("getAllUser")
	public List<User> getAllUser() {
		return userService.getAllUser();
	}

//	@GetMapping("/alldetails")
	@QueryMapping("getAllUsersAndUserDetails")
	public List<UserDetailsResponse> getAllUsersAndUserDetails() {
		List<UserDetailsResponse> userDetailsResponses = userService.getAllUsersAndUserDetails();
		if (!CollectionUtils.isEmpty(userDetailsResponses)) {
			String uri = "http://localhost:8093/userdetails/all";
			userDetails[] response = restTemplate.getForObject(uri, userDetails[].class);
			if (response != null) {
				System.out.println(response.length);
				for (int i = 0; i < response.length; i++) {
					userDetails userDetails = response[i];
					for (UserDetailsResponse userDetailsResponse : userDetailsResponses) {
						if (userDetailsResponse.getUser().getId().equals(userDetails.getUserId())) {
							userDetailsResponse.setUserDetails(userDetails);
						}
					}
				}
			}
		}
		return userDetailsResponses;
	}
}
