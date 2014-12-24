package com.dstevens.characters.traits.experience;

import com.dstevens.characters.traits.changes.ApplicableTraitChange;
import com.dstevens.characters.traits.changes.TraitChangeStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ChangeExperience")
public class ChangeExperience extends ApplicableTraitChange<Experience> {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private ChangeExperience() {
        super();
    }
    
    public ChangeExperience(TraitChangeStatus status, ExperienceMovement movement, int xp) {
    	super(movement.ordinal(), xp);
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
