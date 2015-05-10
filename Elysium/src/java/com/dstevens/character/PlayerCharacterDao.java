package com.dstevens.character;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PlayerCharacterDao extends CrudRepository<PlayerCharacter, Integer> {
    
    @Query("SELECT a FROM PlayerCharacter a WHERE a.name = ?1")
    Iterable<PlayerCharacter> findNamed(String name);
    
}
