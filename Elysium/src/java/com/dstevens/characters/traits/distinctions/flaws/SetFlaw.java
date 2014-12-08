package com.dstevens.characters.traits.distinctions.flaws;

import com.dstevens.characters.traits.ApplicableTraitChange;
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
        this(null, 0, null);
    }
	
	protected SetFlaw(TraitChangeStatus status, int ordinal, String specialization) {
		super(status, ordinal, specialization);
	}

	@Override
	protected CharacterFlaw trait() {
		return new CharacterFlaw(Flaw.values()[ordinal], specialization);
	}
}
