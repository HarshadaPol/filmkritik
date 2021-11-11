package com.Filmkritik.authservice.entities;

import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "MST_User")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String username;
	@Column
	@JsonIgnore
	private String password;

	@Column
	private String phoneno;
	
	@Column
	private String address;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@OneToOne(mappedBy = "userEntity")
	private RefreshTokenEntity refreshTokenEntity;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "MAP_User_Role", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "ROLE_ID") })
	private Set<RolesEntity> roles;

	public Set<RolesEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RolesEntity> roles) {
		this.roles = roles;
	}

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	
	public String getFullName() {
		return getFirstName()+getLastName();
	}

	public void setFullName(String fName, String lName) {
		setFirstName(fName);
		setLastName(lName);
	}
}
