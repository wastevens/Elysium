package com.dstevens.characters.traits.powers.magic.necromancy;

import com.dstevens.characters.traits.changes.ApplicableTraitChange;
import com.dstevens.characters.traits.changes.TraitChangeStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Necromancy")
class SetNecromancy extends ApplicableTraitChange<CharacterNecromancy> {

	//Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private SetNecromancy() {
    	super();
    }
	
    public SetNecromancy(TraitChangeStatus status, int ordinal, int rating) {
    	super(status, ordinal, rating);
    }

	@Override
	protected CharacterNecromancy trait() {
		return new CharacterNecromancy(Necromancy.values()[ordinal], rating);
	}

}
