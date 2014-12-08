package com.dstevens.characters.traits.powers.magic.thaumaturgy;

import com.dstevens.characters.traits.SetApplicableTrait;
import com.dstevens.characters.traits.changes.TraitChangeStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("InClanThaumaturgy")
class SetInClanThaumaturgy extends SetApplicableTrait<Thaumaturgy> {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetInClanThaumaturgy() {
        this(null, 0);
    }
    
    public SetInClanThaumaturgy(TraitChangeStatus status, int ordinal) {
    	super(status, ordinal);
    }

	@Override
	protected Thaumaturgy trait() {
		return Thaumaturgy.values()[ordinal];
	}

}
