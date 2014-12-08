package com.dstevens.characters.traits.powers.magic.thaumaturgy;

import com.dstevens.characters.traits.SetApplicableTrait;
import com.dstevens.characters.traits.changes.TraitChangeStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ThaumaturgicalRitual")
class SetThaumaturgicalRitual extends SetApplicableTrait<ThaumaturgicalRitual> {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetThaumaturgicalRitual() {
        this(null, 0);
    }
    
    public SetThaumaturgicalRitual(TraitChangeStatus status, int ordinal) {
    	super(status, ordinal);
    }

    @Override
    protected ThaumaturgicalRitual trait() {
    	return ThaumaturgicalRitual.values()[ordinal];
    }
	
}
