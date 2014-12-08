package com.dstevens.characters.traits.attributes.focuses;

import com.dstevens.characters.traits.ApplicableTraitChange;
import com.dstevens.characters.traits.changes.TraitChangeStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SocialFocus")
class SetSocialFocus extends ApplicableTraitChange<SocialAttributeFocus> {

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
