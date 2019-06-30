package com.statGambler.services;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import com.statGambler.model.Role;
import com.statGambler.model.User;

public class MyUserPrincipal implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	public boolean isAdmin;
	public boolean isUser;

	public MyUserPrincipal(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		isUser=false;isAdmin=false;
		System.out.println(user.toString());
		Iterable<Role> users = user.getRoles();
		List<Role> lUsers = new LinkedList<Role>();
		for (Role r : users) {
			if (r.name == "USER") {
				isUser=true;
			}
			if (r.name == "ADMIN") {
				isAdmin=true;
			}
			lUsers.add(r);
		}
		return lUsers;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isUser() {
		return isUser;
	}

	public void setUser(boolean isUser) {
		this.isUser = isUser;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}