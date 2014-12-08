package com.dstevens.characters.traits.distinctions.merits;

import com.dstevens.characters.traits.changes.ApplicableTraitChange;
import com.dstevens.characters.traits.changes.TraitChangeStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Merit")
class SetMerit extends ApplicableTraitChange<CharacterMerit> {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetMerit() {
        this(null, 0, null);
    }
	
	protected SetMerit(TraitChangeStatus status, int ordinal, String specialization) {
		super(status, ordinal, specialization);
	}

	@Override
	protected CharacterMerit trait() {
		return new CharacterMerit(Merit.values()[ordinal], specialization);
	}

}
