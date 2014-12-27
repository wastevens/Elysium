package com.dstevens.characters.traits.experience;

import com.dstevens.characters.PlayerCharacter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SpendRequest")
public class ExperienceSpendRequest extends ExperienceChange {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private ExperienceSpendRequest() {
        super();
    }
	
	public ExperienceSpendRequest(int value) {
		super(value);
	}

	@Override
	public PlayerCharacter applyTo(PlayerCharacter playerCharacter) {
		return playerCharacter.requestXpSpend(getValue());
	}

}
