package com.dstevens.characters.traits.experience;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Earn")
public class EarnExperience extends GainExperience {

	//Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private EarnExperience() {
        this(0);
    }
	
	public EarnExperience(int xp) {
		super(xp);
	}
}
