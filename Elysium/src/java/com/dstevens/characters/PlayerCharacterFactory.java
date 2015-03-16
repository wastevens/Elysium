package com.dstevens.characters;

import org.springframework.stereotype.Service;

import com.dstevens.players.Setting;

@Service
public class PlayerCharacterFactory {

    public PlayerCharacter createPlayerCharacter(String name, Setting setting) {
        return new PlayerCharacter(null, name, setting);
    }
    
}
