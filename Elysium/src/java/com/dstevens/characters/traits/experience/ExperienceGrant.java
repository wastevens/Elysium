package com.dstevens.characters.traits.experience;

import com.dstevens.characters.PlayerCharacter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Grant")
public class ExperienceGrant extends ExperienceChange {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private ExperienceGrant() {
        super();
    }
	
	public ExperienceGrant(int value) {
		super(value);
	}

	@Override
	public PlayerCharacter applyTo(PlayerCharacter playerCharacter) {
		return playerCharacter.grantXp(getValue());
	}

}
