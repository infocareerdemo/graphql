package com.example.infodetails.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "user_details")
@Data
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "email_id")
	private String emailId;
	@Column(name = "dob")
	private String dob;
	@Column(name = "mobile")
	private String mobile;
	@Column(name = "address")
	private String address;
	@Column(name = "user_id")
	private Long userId;
}
