package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.traits.changes.GainXp;
import com.dstevens.characters.traits.changes.SetTrait;
import com.dstevens.characters.traits.changes.SpendXp;
import com.dstevens.characters.traits.changes.TraitChangeStatus;
import com.dstevens.game.TraitChangeBuilder;

public class SetAttributeBuilder implements TraitChangeBuilder {

	private AttributeFactory factory;
	private int rating;

	public SetAttributeBuilder(AttributeFactory factory) {
		this.factory = factory;
	}
	
	public SetAttributeBuilder withRating(int rating) {
		this.rating = rating;
		return this;
	}

	@Override
	public SetTrait buy() {
		return new SpendXp(TraitChangeStatus.PENDING, 3).and(setAttribute());
	}

	@Override
	public SetTrait sell() {
		return new GainXp(TraitChangeStatus.PENDING, 3).and(setAttribute());
	}
	
	@Override
	public SetTrait add() {
		return setAttribute();
	}

	@Override
	public SetTrait remove() {
		return setAttribute();
	}

	private SetTrait setAttribute() {
		return new SetAttribute(TraitChangeStatus.PENDING, rating, factory);
	}

}
