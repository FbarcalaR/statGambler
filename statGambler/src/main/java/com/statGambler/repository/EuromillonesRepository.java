package com.statGambler.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.statGambler.model.Euromillones;

@Repository
public interface EuromillonesRepository extends CrudRepository<Euromillones, Long> {

}
