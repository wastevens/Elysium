package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;


public enum MentalAttributeFocus implements ApplicableTrait {

    INTELLIGENCE,
    WITS,
    PERCEPTION;

	@Override
	public PlayerCharacter applyTo(PlayerCharacter character) {
		return character.withMentalAttributeFocus(this);
	}

	@Override
	public PlayerCharacter removeFrom(PlayerCharacter character) {
		return character.withoutMentalAttributeFocus(this);
	}
    
}
