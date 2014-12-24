package com.dstevens.characters.traits.powers.disciplines;

import com.dstevens.characters.traits.changes.ApplicableTraitChange;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Technique")
class SetTechnique extends ApplicableTraitChange<Technique> {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetTechnique() {
    	super();
    }
    
    public SetTechnique(int ordinal) {
    	super(ordinal);
    }

	@Override
	protected Technique trait() {
		return Technique.values()[ordinal];
	}

}
