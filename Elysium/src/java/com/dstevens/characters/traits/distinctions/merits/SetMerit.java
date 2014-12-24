package com.dstevens.characters.traits.distinctions.merits;

import com.dstevens.characters.traits.changes.TraitChange;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Merit")
class SetMerit extends TraitChange<CharacterMerit> {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetMerit() {
    	super();
    }
	
	protected SetMerit(int ordinal, String specialization) {
		super(ordinal, specialization);
	}

	@Override
	protected CharacterMerit trait() {
		return new CharacterMerit(Merit.values()[ordinal], specialization);
	}

}
