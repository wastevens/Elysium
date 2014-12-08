package com.dstevens.characters.traits.powers.magic.necromancy;

import com.dstevens.characters.traits.changes.ApplicableTraitChange;
import com.dstevens.characters.traits.changes.TraitChangeStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("NecromanticRitual")
class SetNecromanticRitual extends ApplicableTraitChange<NecromanticRitual> {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetNecromanticRitual() {
        this(null, 0);
    }
    
    public SetNecromanticRitual(TraitChangeStatus status, int ordinal) {
    	super(status, ordinal);
    }

	@Override
	protected NecromanticRitual trait() {
		return NecromanticRitual.values()[ordinal];
	}
	
}
