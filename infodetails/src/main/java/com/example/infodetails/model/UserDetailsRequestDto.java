package com.example.infodetails.model;

import lombok.Data;

@Data
public class UserDetailsRequestDto {

	private String userName;
	private String password;
	private String name;
	private String emailId;
	private String dob;
	private String mobile;
	private String address;
	private Long userId;
}
