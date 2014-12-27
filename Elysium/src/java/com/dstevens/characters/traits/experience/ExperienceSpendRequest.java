package com.dstevens.characters.traits.experience;

import java.util.Date;

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
	
	public ExperienceSpendRequest(int value, Date changedOn) {
		super(value, changedOn);
	}

	@Override
	public PlayerCharacter applyTo(PlayerCharacter playerCharacter) {
		return playerCharacter.requestXpSpend(getValue());
	}

}
