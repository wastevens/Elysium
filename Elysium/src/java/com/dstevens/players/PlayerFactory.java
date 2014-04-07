package com.dstevens.players;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.collections.Sets;
import com.dstevens.persistence.IdGenerator;

@Service
public class PlayerFactory {

    private IdGenerator idGenerator;

    @Autowired
    public PlayerFactory(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }
    
    public Player createPlayer(String name, String email) {
        return new Player(idGenerator.createId(), name, email, Sets.<Troupe>set(), Sets.<PlayerCharacter>set());
    }
    
}
