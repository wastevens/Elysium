package com.dstevens.characters.traits;

import com.dstevens.characters.PlayerCharacter;

public interface ApplicableTrait {

	PlayerCharacter applyTo(PlayerCharacter character);
    PlayerCharacter removeFrom(PlayerCharacter character);
    
    ApplicableTrait copy();
	
}
