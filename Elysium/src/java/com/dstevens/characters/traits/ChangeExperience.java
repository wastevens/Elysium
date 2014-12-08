package com.dstevens.characters.traits;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ChangeExperience")
class ChangeExperience extends SetApplicableTrait<Experience> {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private ChangeExperience() {
        super();
    }
    
    public ChangeExperience(TraitChangeStatus status, ExperienceMovement movement, int xp) {
    	super(status, movement.ordinal(), xp);
    }

	@Override
	protected Experience trait() {
		return ExperienceMovement.values()[ordinal].changeExperience(rating);
	}
	
	public static ChangeExperience spend(int xp) {
		return new ChangeExperience(TraitChangeStatus.PENDING, ExperienceMovement.SPEND, xp);
	}
	
	public static ChangeExperience gain(int xp) {
		return new ChangeExperience(TraitChangeStatus.PENDING, ExperienceMovement.GAIN, xp);
	}
}
