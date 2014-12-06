package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeBuilder;
import com.dstevens.characters.traits.TraitChangeStatus;

public class SetSocialFocusBuilder implements TraitChangeBuilder {

    private SocialAttributeFocus trait;

	public SetSocialFocusBuilder(SocialAttributeFocus trait) {
		this.trait = trait;
    }
    
    @Override
    public SetTrait buy() {
		throw new IllegalStateException("You can't buy an attribute focus");
    }

    @Override
    public SetTrait add() {
        return setAttribute();
    }

    private SetTrait setAttribute() {
    	return new SetSocialFocus(TraitChangeStatus.PENDING, trait);
    }

	@Override
	public SetTrait sell() {
		throw new IllegalStateException("not yet implemented");
	}

	@Override
	public SetTrait remove() {
		throw new IllegalStateException("not yet implemented");
	}
    
}
