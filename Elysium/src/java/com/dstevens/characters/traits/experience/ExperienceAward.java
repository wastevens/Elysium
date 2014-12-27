package com.dstevens.characters.traits.experience;

import java.util.Date;

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
	
	public ExperienceAward(int value, Date changedOn) {
		super(value, changedOn);
	}

	@Override
	public PlayerCharacter applyTo(PlayerCharacter playerCharacter) {
		return playerCharacter.awardXp(getValue());
	}

}
