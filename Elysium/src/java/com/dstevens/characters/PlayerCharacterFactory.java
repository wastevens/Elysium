package com.dstevens.characters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.characters.status.ApprovalStatus;
import com.dstevens.players.Setting;
import com.dstevens.suppliers.IdSupplier;

@Service
public class PlayerCharacterFactory {

    private final IdSupplier idSupplier;

    @Autowired
    public PlayerCharacterFactory(IdSupplier idSupplier) {
        this.idSupplier = idSupplier;
    }
    
    public PlayerCharacter createPlayerCharacter(String name, Setting setting) {
        return new PlayerCharacter(idSupplier.get(), name, setting, ApprovalStatus.IN_CREATION);
    }
    
}
