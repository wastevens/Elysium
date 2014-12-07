package com.dstevens.characters.traits.distinctions;

import com.dstevens.characters.traits.SetApplicableTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Flaw")
class SetFlaw extends SetApplicableTrait<CharacterFlaw> {

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
