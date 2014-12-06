package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.traits.SetEnumeratedTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MentalFocus")
public class SetMentalFocus extends SetEnumeratedTrait<MentalAttributeFocus> {

	@Column(name="trait_ordinal")
    private MentalAttributeFocus trait;

    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetMentalFocus() {
        this(null, null);
    }
    
    public SetMentalFocus(TraitChangeStatus status, MentalAttributeFocus trait) {
    	super(status);
    	this.trait = trait;
    }
    
    @Override
    protected MentalAttributeFocus trait() {
    	return trait;
    }
}
