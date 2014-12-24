package com.dstevens.characters.traits.powers.magic.thaumaturgy;

import com.dstevens.characters.traits.changes.TraitChange;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("InClanThaumaturgy")
class SetInClanThaumaturgy extends TraitChange<Thaumaturgy> {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetInClanThaumaturgy() {
    	super();
    }
    
    public SetInClanThaumaturgy(int ordinal) {
    	super(ordinal);
    }

	@Override
	protected Thaumaturgy trait() {
		return Thaumaturgy.values()[ordinal];
	}

}
