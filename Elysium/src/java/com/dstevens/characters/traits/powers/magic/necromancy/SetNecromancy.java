package com.dstevens.characters.traits.powers.magic.necromancy;

import com.dstevens.characters.traits.SetApplicableTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Necromancy")
class SetNecromancy extends SetApplicableTrait<CharacterNecromancy> {

	//Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private SetNecromancy() {
        this(null, 0, 0);
    }
	
    public SetNecromancy(TraitChangeStatus status, int ordinal, int rating) {
    	super(status, ordinal, rating);
    }

	@Override
	protected CharacterNecromancy trait() {
		return new CharacterNecromancy(Necromancy.values()[ordinal], rating);
	}

}
