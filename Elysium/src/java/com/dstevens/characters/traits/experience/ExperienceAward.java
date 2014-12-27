package com.dstevens.characters.traits.experience;

import com.dstevens.characters.PlayerCharacter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Award")
public class ExperienceAward extends ExperienceChange {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private ExperienceAward() {
        super();
    }
	
	public ExperienceAward(int value) {
		super(value);
	}

	@Override
	public PlayerCharacter applyTo(PlayerCharacter playerCharacter) {
		return playerCharacter.awardXp(getValue());
	}

}
