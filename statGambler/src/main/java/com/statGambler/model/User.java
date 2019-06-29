package com.statGambler.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long estadisticasPersonalesId;
	private String username;
	private String password;
	private String name;
	
	@Transient
    private String passwordConfirm;

	@JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
	
	public User() { }

	public User(Long id, String name, String pass) {
		this.id = id;
		this.username = name;
		this.password = pass;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEstadisticasPersonalesId() {
		return estadisticasPersonalesId;
	}

	public void setEstadisticasPersonalesId(Long estadisticasPersonalesId) {
		this.estadisticasPersonalesId = estadisticasPersonalesId;
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

	@Override
	public boolean equals(Object obj) {
		User u=(User)obj;
		return u.password.equals(this.password) && u.username.equals(this.username);
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		String resul="username: " + username + "pass: "+password + "roles:";
		for(Role r : roles) {
			resul+=" "+r.getName();
		}
		
		return resul;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
