package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.EnumeratedTrait;


public enum MentalAttributeFocus implements EnumeratedTrait<MentalAttributeFocus>, ApplicableTrait {

    INTELLIGENCE,
    WITS,
    PERCEPTION;

	@Override
	public MentalAttributeFocus trait() {
		return this;
	}

	@Override
	public PlayerCharacter applyTo(PlayerCharacter character) {
		return character.withMentalAttributeFocus(this);
	}

	@Override
	public PlayerCharacter removeFrom(PlayerCharacter character) {
		return character.withoutMentalAttributeFocus(this);
	}
    
}
