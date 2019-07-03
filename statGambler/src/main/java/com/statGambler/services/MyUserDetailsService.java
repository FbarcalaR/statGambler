package com.statGambler.services;

import java.security.Principal;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.statGambler.model.EstadisticasPersonales;
import com.statGambler.model.Role;
import com.statGambler.model.User;
import com.statGambler.repository.EstadisticasPersonalesRepository;
import com.statGambler.repository.RoleRepository;
import com.statGambler.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
 
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EstadisticasPersonalesRepository estadisticasPersonalesRepository;
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
        System.out.println(user);
        return new MyUserPrincipal(user);
    }
    
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        setRoles(user);
        setRuleta(user);
        setEstadisticasPersonales(user);
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
    
    private void setRuleta(User user){
    	Iterable<User> todos=userRepository.findAll();
    	long ultimoID=-1;
    	
    	for(User u:todos) {
    		if(ultimoID<u.getRuletaId()) {
    			ultimoID=u.getRuletaId();
    		}
    	}
    	
    	ultimoID++;
    	user.setRuletaId(ultimoID);
//    	Ruleta r=new Ruleta();
//    	r.setUserId(user.getId());
//    	ruletaRepository.save(r);
    	
    }
    
    private void setEstadisticasPersonales(User user){
    	Iterable<User> todos=userRepository.findAll();
    	long ultimoID=-1;
    	
    	for(User u:todos) {
    		if(ultimoID<u.getEstadisticasPersonalesId()) {
    			ultimoID=u.getEstadisticasPersonalesId();
    		}
    	}
    	
    	EstadisticasPersonales ep=new EstadisticasPersonales();
    	
    	ultimoID++;
    	ep.setUserId(user.getId());
    	user.setEstadisticasPersonalesId(ultimoID);
    	estadisticasPersonalesRepository.save(ep);
    	
    }
    
    public User getUserPrincipal() {
    	Principal principal=request.getUserPrincipal();
		String username=principal.getName();
		User user=userRepository.findByUsername(username);
		return user;
    }
}