package com.dstevens.characters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.players.*;
import com.dstevens.suppliers.IdSupplier;

@Service
public class PlayerCharacterFactory {

    private IdSupplier idSupplier;

    @Autowired
    public PlayerCharacterFactory(IdSupplier idSupplier) {
        this.idSupplier = idSupplier;
    }
    
    public PlayerCharacter createPlayerCharacter(Troupe troupe, Player player, String name) {
        PlayerCharacter playerCharacter = new PlayerCharacter(idSupplier.get(), troupe, player, name);
        player.addCharacter(playerCharacter);
        troupe.withCharacter(playerCharacter);
        return playerCharacter;
    }
    
}
