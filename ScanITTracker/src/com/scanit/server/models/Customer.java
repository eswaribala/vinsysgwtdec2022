package com.scanit.server.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	private String userName;
	private String password;
	private String email;
	private Date dob;
	private long mobileNo;
	private Gender gender;
	

}
