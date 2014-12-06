package com.dstevens.characters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.suppliers.IdSupplier;

@Service
class PlayerCharacterFactory {

    private IdSupplier idSupplier;

    @Autowired
    public PlayerCharacterFactory(IdSupplier idSupplier) {
        this.idSupplier = idSupplier;
    }
    
    public PlayerCharacter createPlayerCharacter(String name) {
        return new PlayerCharacter(idSupplier.get(), name);
    }
    
}
