package com.statGambler.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.statGambler.model.Primitiva;

@Repository
public interface PrimitivaRepository extends CrudRepository<Primitiva, Long> {

}
