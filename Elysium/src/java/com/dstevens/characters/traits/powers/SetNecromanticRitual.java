package com.dstevens.characters.traits.powers;

import com.dstevens.characters.traits.SetApplicableTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("NecromanticRitual")
public class SetNecromanticRitual extends SetApplicableTrait<NecromanticRitual> {

	@Column(name="trait_ordinal")
    private NecromanticRitual trait;

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetNecromanticRitual() {
        this(null, null);
    }
    
    public SetNecromanticRitual(TraitChangeStatus status, NecromanticRitual trait) {
    	super(status);
		this.trait = trait;
    }

	@Override
	protected NecromanticRitual trait() {
		return trait;
	}
	
}
