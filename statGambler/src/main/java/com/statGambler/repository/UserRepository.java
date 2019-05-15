package com.statGambler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.statGambler.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
