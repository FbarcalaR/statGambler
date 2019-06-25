package com.statGambler.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.statGambler.model.User;
import com.statGambler.repository.UserRepository;
import com.statGambler.services.MyUserDetailsService;


@Controller
public class LoginController {
	
	@Autowired
	MyUserDetailsService userDetailsService;	
	@Autowired
	PasswordEncoder encoder;
	
	
	@GetMapping("/index")
	public String getIndex(User u) {
        return "index";
    }
	
	@GetMapping("/")
	public String getRoot(User u) {
		
			
        return "index";
    }
	
	@PostMapping("/index")
	public String getIndexPost() {
        return "index";
    }
	
	@GetMapping("/newuser")
	public String getNewUser(User u) {
        return "new-user";
    }
	
	@GetMapping("/login")
	public String getLoginForm(User u) {
        return "login";
    }
	
	@GetMapping("/logout")
	public String logout(User u) {
        return "login";
    }
	
	@GetMapping("/layout")
	public String getLayout() {
        return "_Layout";
    }
	
    @PostMapping("/newuser")
    public String newUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "new-user";
        }
        
        userDetailsService.save(user);
        return "login";
    }
    
    @PostMapping("/login")
    public String logIn(@Valid User user, BindingResult result, Model model) {
    	if (result.hasErrors()) {
            return "login";
        }
    	
    	return "index";
    }
}