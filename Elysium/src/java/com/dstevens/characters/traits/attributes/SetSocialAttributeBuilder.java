package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.traits.ChangeXp;
import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeBuilder;
import com.dstevens.characters.traits.TraitChangeStatus;

public class SetSocialAttributeBuilder implements TraitChangeBuilder {

    private final int rating;

    public SetSocialAttributeBuilder(int rating) {
		this.rating = rating;
    }
    
    @Override
    public SetTrait buy() {
		return ChangeXp.spend(3).and(setAttribute());
    }

    @Override
    public SetTrait add() {
        return setAttribute();
    }

    private SetTrait setAttribute() {
    	return new SetSocialAttribute(TraitChangeStatus.PENDING, rating);
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
