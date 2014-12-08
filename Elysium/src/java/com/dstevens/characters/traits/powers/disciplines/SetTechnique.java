package com.dstevens.characters.traits.powers.disciplines;

import com.dstevens.characters.traits.ApplicableTraitChange;
import com.dstevens.characters.traits.changes.TraitChangeStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Technique")
class SetTechnique extends ApplicableTraitChange<Technique> {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetTechnique() {
        this(null, 0);
    }
    
    public SetTechnique(TraitChangeStatus status, int ordinal) {
    	super(status, ordinal);
    }

	@Override
	protected Technique trait() {
		return Technique.values()[ordinal];
	}

}
