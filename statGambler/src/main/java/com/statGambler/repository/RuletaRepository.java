package com.statGambler.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.statGambler.model.Ruleta;

@Repository
public interface RuletaRepository extends CrudRepository<Ruleta, Long> {

}
