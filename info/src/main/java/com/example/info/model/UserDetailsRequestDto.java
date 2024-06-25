package com.example.info.model;

import lombok.Data;

@Data
public class UserDetailsRequestDto {

	public UserDetailsRequestDto(String name2, String emailId2, String dob2, Long mobile2, String address2, Long id) {
		// TODO Auto-generated constructor stub
	}
	private String userName;
	private String password;
	private String name;
	private String emailId;
	private String dob;
	private String mobile;
	private String address;
	private Long userId;
}
