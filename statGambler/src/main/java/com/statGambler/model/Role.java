package com.statGambler.model;

import javax.persistence.*;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Role implements GrantedAuthority{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

	@Override
	public String getAuthority() {
		return getName();
	}
	@Override
	public String toString() {
		return getName();
	}
}
