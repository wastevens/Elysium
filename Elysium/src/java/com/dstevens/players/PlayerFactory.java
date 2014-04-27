package com.dstevens.players;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.collections.Sets;
import com.dstevens.suppliers.IdSupplier;

@Service
public class PlayerFactory {

    private IdSupplier idSupplier;

    @Autowired
    public PlayerFactory(IdSupplier idSupplier) {
        this.idSupplier = idSupplier;
    }
    
    public Player createPlayer(String name, String email, Troupe troupe) {
        return new Player(idSupplier.get(), name, email, Sets.<PlayerCharacter>set());
    }
    
}
