package com.dstevens.characters.traits.attributes.focuses;

import com.dstevens.characters.traits.changes.TraitChange;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SocialFocus")
class SetSocialFocus extends TraitChange<SocialAttributeFocus> {

    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetSocialFocus() {
        super();
    }
    
    public SetSocialFocus(int ordinal) {
    	super(ordinal);
    }

	@Override
	protected SocialAttributeFocus trait() {
		return SocialAttributeFocus.values()[ordinal];
	}
}
