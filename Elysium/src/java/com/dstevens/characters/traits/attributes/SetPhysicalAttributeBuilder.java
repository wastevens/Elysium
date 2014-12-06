package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.traits.ChangeXp;
import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeBuilder;
import com.dstevens.characters.traits.TraitChangeStatus;

public class SetPhysicalAttributeBuilder implements TraitChangeBuilder {

    private final int rating;

    public SetPhysicalAttributeBuilder(int rating) {
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
    	return new SetPhysicalAttribute(TraitChangeStatus.PENDING, rating);
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
