package com.statGambler.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	public String username;
	public String psswrd;

	
	public User() { }

	public User(Long id, String name, String pass) {
		this.id = id;
		this.username = name;
		this.psswrd = pass;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPsswrd() {
		return psswrd;
	}

	public void setPsswrd(String psswrd) {
		this.psswrd = psswrd;
	}
	
	@Override
	public boolean equals(Object obj) {
		User u=(User)obj;
		return u.psswrd.equals(this.psswrd) && u.username.equals(this.username);
	}

	
}
