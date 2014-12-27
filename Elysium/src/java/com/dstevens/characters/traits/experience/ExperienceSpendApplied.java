package com.dstevens.characters.traits.experience;

import com.dstevens.characters.PlayerCharacter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SpendApplied")
public class ExperienceSpendApplied extends ExperienceChange {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private ExperienceSpendApplied() {
        super();
    }
	
	public ExperienceSpendApplied(int value) {
		super(value);
	}

	@Override
	public PlayerCharacter applyTo(PlayerCharacter playerCharacter) {
		return playerCharacter.applyXpSpend(getValue());
	}

}
