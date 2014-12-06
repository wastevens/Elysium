package com.dstevens.characters.traits.powers;

import com.dstevens.characters.traits.SetApplicableTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("InClanThaumaturgy")
class SetInClanThaumaturgy extends SetApplicableTrait<Thaumaturgy> {

	@Column(name="applicable_trait_ordinal")
    private Thaumaturgy trait;

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetInClanThaumaturgy() {
        this(null, null);
    }
    
    public SetInClanThaumaturgy(TraitChangeStatus status, Thaumaturgy trait) {
    	super(status);
		this.trait = trait;
    }

	@Override
	protected Thaumaturgy trait() {
		return trait;
	}

}
