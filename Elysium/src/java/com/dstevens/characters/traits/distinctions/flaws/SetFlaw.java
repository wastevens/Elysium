package com.dstevens.characters.traits.distinctions.flaws;

import com.dstevens.characters.traits.changes.ApplicableTraitChange;
import com.dstevens.characters.traits.changes.TraitChangeStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Flaw")
class SetFlaw extends ApplicableTraitChange<CharacterFlaw> {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetFlaw() {
    	super();
    }
	
	protected SetFlaw(TraitChangeStatus status, int ordinal, String specialization) {
		super(status, ordinal, specialization);
	}

	@Override
	protected CharacterFlaw trait() {
		return new CharacterFlaw(Flaw.values()[ordinal], specialization);
	}
}