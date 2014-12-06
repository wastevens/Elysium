package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.traits.SetApplicableTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SocialFocus")
class SetSocialFocus extends SetApplicableTrait<SocialAttributeFocus> {

	@Column(name="applicable_trait_ordinal")
    private SocialAttributeFocus trait;

    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetSocialFocus() {
        this(null, null);
    }
    
    public SetSocialFocus(TraitChangeStatus status, SocialAttributeFocus trait) {
    	super(status);
    	this.trait = trait;
    }

	@Override
	protected SocialAttributeFocus trait() {
		return trait;
	}
}
