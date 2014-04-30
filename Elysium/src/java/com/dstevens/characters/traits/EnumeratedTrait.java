package com.dstevens.characters.traits;

import com.dstevens.characters.PlayerCharacter;



public interface EnumeratedTrait<T extends Enum<?>> {

    int ordinal();
    T trait();
    PlayerCharacter applyTo(PlayerCharacter character);
    
}
