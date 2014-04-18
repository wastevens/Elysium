package com.dstevens.players;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TroupeDao extends CrudRepository<Troupe, String> {

    @Query("SELECT a FROM Troupe a WHERE a.name = ?1 AND a.deleteTimestamp is null")
    Iterable<Troupe> findUndeletedNamed(String name);
    
    @Query("SELECT a FROM Troupe a WHERE a.name = ?1")
    Iterable<Troupe> findNamed(String name);
}
