package com.dstevens.characters.traits.powers;

import com.dstevens.characters.traits.SetApplicableTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("InClanNecromancy")
class SetInClanNecromancy extends SetApplicableTrait<Necromancy> {

	@Column(name="applicable_trait_ordinal")
    private Necromancy trait;
	
	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetInClanNecromancy() {
        this(null, null);
    }
    
    public SetInClanNecromancy(TraitChangeStatus status, Necromancy trait) {
    	super(status);
		this.trait = trait;
    }

	@Override
	protected Necromancy trait() {
		return trait;
	}

}
