package com.dstevens.players;

import java.util.List;

import com.dstevens.characters.PlayerCharacter;

public class Player {

    private final long id;
    private final String name;
    private final String email;
    private final List<PlayerCharacter> characters;
    
    private Player(long id, String name, String email, List<PlayerCharacter> characters) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.characters = characters;
    }
}
