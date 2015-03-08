package com.dstevens.characters.traits.attributes.focuses;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.Trait;
import com.dstevens.characters.traits.TraitQualities;


public enum MentalAttributeFocus implements AttributeFocus, Trait, ApplicableTrait {

    INTELLIGENCE,
    WITS,
    PERCEPTION;


	@Override
	public ApplicableTrait applyWith(TraitQualities qualities) {
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
