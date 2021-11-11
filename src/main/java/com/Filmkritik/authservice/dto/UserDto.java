package com.Filmkritik.authservice.dto;

import javax.persistence.Column;

public class UserDto {
	private String username;
	private String password;
	private String phoneno;
	private String address;
	private String firstName;
	private String lastName;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileNumber() {
		return phoneno;
	}

	public void setMobileNumber(String mobNo) {
		this.phoneno = mobNo;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String addr) {
		this.address = addr;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String fName) {
		this.firstName = fName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lName) {
		this.lastName = lName;
	}
}
