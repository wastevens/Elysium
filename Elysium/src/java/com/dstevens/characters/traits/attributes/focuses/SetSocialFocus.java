package com.dstevens.characters.traits.attributes.focuses;

import com.dstevens.characters.traits.SetApplicableTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SocialFocus")
class SetSocialFocus extends SetApplicableTrait<SocialAttributeFocus> {

    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetSocialFocus() {
        super();
    }
    
    public SetSocialFocus(TraitChangeStatus status, int ordinal) {
    	super(status, ordinal);
    }

	@Override
	protected SocialAttributeFocus trait() {
		return SocialAttributeFocus.values()[ordinal];
	}
}
