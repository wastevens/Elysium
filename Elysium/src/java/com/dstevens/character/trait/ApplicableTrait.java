package com.dstevens.character.trait;

import com.dstevens.character.PlayerCharacter;

public interface ApplicableTrait {

	PlayerCharacter applyTo(PlayerCharacter character);
    PlayerCharacter removeFrom(PlayerCharacter character);
	
}
