package com.dstevens.characters.traits;

import com.dstevens.characters.PlayerCharacter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Gain")
public class GainExperience extends Experience {

	//Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private GainExperience() {
        this(0);
    }
	
	public GainExperience(int xp) {
		super(xp);
	}
	
	@Override
	public PlayerCharacter applyTo(PlayerCharacter character) {
		return character.setXp(character.getXp() + xp());
	}

	@Override
	public PlayerCharacter removeFrom(PlayerCharacter character) {
		return character.setXp(character.getXp() - xp());
	}
}
