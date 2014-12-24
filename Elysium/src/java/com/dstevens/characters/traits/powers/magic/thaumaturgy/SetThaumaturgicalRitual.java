package com.dstevens.characters.traits.powers.magic.thaumaturgy;

import com.dstevens.characters.traits.changes.TraitChange;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ThaumaturgicalRitual")
class SetThaumaturgicalRitual extends TraitChange<ThaumaturgicalRitual> {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetThaumaturgicalRitual() {
    	super();
    }
    
    public SetThaumaturgicalRitual(int ordinal) {
    	super(ordinal);
    }

    @Override
    protected ThaumaturgicalRitual trait() {
    	return ThaumaturgicalRitual.values()[ordinal];
    }
	
}
