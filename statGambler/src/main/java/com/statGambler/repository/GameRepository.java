package com.statGambler.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.statGambler.model.Game;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {

}
