package com.statGambler.services;

import java.security.Principal;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.statGambler.model.Role;
import com.statGambler.model.User;
import com.statGambler.repository.RoleRepository;
import com.statGambler.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
 
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
	private HttpServletRequest request;
 
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
    }
    
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        setRoles(user);
        userRepository.save(user);
    }
    
    private void setRoles(User user) {
    	LinkedList<Long> ids=new LinkedList<Long>();
    	ids.add(new Long (1));
    	
    	Iterable<Role> roles= roleRepository.findAllById(ids);
    	
        Set<Role> rolSet=new HashSet<Role>();
        for(Role r: roles) {
        	rolSet.add(r);
        }
        user.setRoles(rolSet);
    }
    
    public User getUserPrincipal() {
    	Principal principal=request.getUserPrincipal();
		String username=principal.getName();
		User user=userRepository.findByUsername(username);
		return user;
    }
}