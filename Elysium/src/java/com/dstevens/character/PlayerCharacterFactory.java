package com.dstevens.character;

import org.springframework.stereotype.Service;

import com.dstevens.player.Setting;

@Service
public class PlayerCharacterFactory {

    public PlayerCharacter createPlayerCharacter(String name, Setting setting) {
        return new PlayerCharacter(null, name, setting);
    }
    
}
