package com.dstevens.characters.traits.experience;

import com.dstevens.characters.PlayerCharacter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Spend")
class SpendExperience extends Experience {

	//Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private SpendExperience() {
        this(0);
    }
	
	public SpendExperience(int xp) {
		super(xp);
	}
	
	@Override
	public PlayerCharacter applyTo(PlayerCharacter character) {
		return character.setXp(character.getXp() - xp());
	}

	@Override
	public PlayerCharacter removeFrom(PlayerCharacter character) {
		return character.setXp(character.getXp() + xp());
	}
}
