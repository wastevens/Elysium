package com.dstevens.characters.traits.experience;

import com.dstevens.characters.traits.changes.TraitChange;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ChangeExperience")
public class ChangeExperience extends TraitChange<Experience> {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private ChangeExperience() {
        super();
    }
    
    public ChangeExperience(ExperienceMovement movement, int xp) {
    	super(movement.ordinal(), xp);
    }

	@Override
	protected Experience trait() {
		return ExperienceMovement.values()[ordinal].changeExperience(rating);
	}
	
	public static ChangeExperience spend(int xp) {
		return new ChangeExperience(ExperienceMovement.SPEND, xp);
	}
	
	public static ChangeExperience gain(int xp) {
		return new ChangeExperience(ExperienceMovement.GAIN, xp);
	}
	
	public static ChangeExperience earn(int xp) {
		return new ChangeExperience(ExperienceMovement.EARN, xp);
	}
}
