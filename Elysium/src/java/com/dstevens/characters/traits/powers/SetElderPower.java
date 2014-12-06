package com.dstevens.characters.traits.powers;

import com.dstevens.characters.traits.SetEnumeratedTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ElderPower")
public class SetElderPower extends SetEnumeratedTrait<ElderPower> {

	@Column(name="trait_ordinal")
    private final ElderPower trait;

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetElderPower() {
        this(null, null);
    }
    
    public SetElderPower(TraitChangeStatus status, ElderPower trait) {
    	super(status);
		this.trait = trait;
    }

	@Override
	protected ElderPower trait() {
		return trait;
	}

}
