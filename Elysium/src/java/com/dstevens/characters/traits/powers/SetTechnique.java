package com.dstevens.characters.traits.powers;

import com.dstevens.characters.traits.SetApplicableTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Technique")
class SetTechnique extends SetApplicableTrait<Technique> {

	@Column(name="trait_ordinal")
    private Technique trait;
	
	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetTechnique() {
        this(null, null);
    }
    
    public SetTechnique(TraitChangeStatus status, Technique trait) {
    	super(status);
		this.trait = trait;
    }

	@Override
	protected Technique trait() {
		return trait;
	}

}
