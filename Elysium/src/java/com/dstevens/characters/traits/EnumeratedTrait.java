package com.dstevens.characters.traits;

import com.dstevens.characters.PlayerCharacter;



public interface EnumeratedTrait<T> {

    int ordinal();
    T trait();
    PlayerCharacter applyTo(PlayerCharacter character);
    PlayerCharacter removeFrom(PlayerCharacter character);
    
}
