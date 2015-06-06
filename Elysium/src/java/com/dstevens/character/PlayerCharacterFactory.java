package com.dstevens.character;

import org.springframework.stereotype.Service;

@Service
public class PlayerCharacterFactory {

    public PlayerCharacter createPlayerCharacter(String name, Setting setting) {
        return new PlayerCharacter(name, setting);
    }
    
}
