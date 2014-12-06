package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeBuilder;
import com.dstevens.characters.traits.TraitChangeStatus;

public class SetAttributeFocusBuilder implements TraitChangeBuilder {

	private AttributeFocus focus;

	public SetAttributeFocusBuilder(AttributeFocus focus) {
		this.focus = focus;
	}

	@Override
	public SetTrait buy() {
		throw new IllegalStateException("You cannot buy a new attribute focus, only add them as part of buying or adding something else.");
	}

	@Override
	public SetTrait sell() {
		throw new IllegalStateException("You cannot sell an attribute focus, only remove them as part of buying or adding something else.");
	}
	
	@Override
	public SetTrait add() {
		if(focus instanceof PhysicalAttributeFocus) {
			return new SetAttributeFocus(TraitChangeStatus.PENDING, focus, AttributeFactory.PHYSICAL);
		}
		if(focus instanceof SocialAttributeFocus) {
			return new SetAttributeFocus(TraitChangeStatus.PENDING, focus, AttributeFactory.SOCIAL);
		}
		if(focus instanceof MentalAttributeFocus) {
			return new SetAttributeFocus(TraitChangeStatus.PENDING, focus, AttributeFactory.MENTAL);
		}
		throw new IllegalArgumentException("Could not create SetAttributeFocus for " + focus);
	}

	@Override
	public SetTrait remove() {
		throw new IllegalStateException("not yet implemented");
	}

}
