package com.dstevens.characters.traits.powers.magic.necromancy;

import com.dstevens.characters.traits.changes.TraitChange;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("InClanNecromancy")
class SetInClanNecromancy extends TraitChange<Necromancy> {
	
	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetInClanNecromancy() {
    	super();
    }
    
    public SetInClanNecromancy(int ordinal) {
    	super(ordinal);
    }

	@Override
	protected Necromancy trait() {
		return Necromancy.values()[ordinal];
	}

}
