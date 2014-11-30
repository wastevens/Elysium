package com.dstevens.game.traitchangebuilders;

import com.dstevens.characters.changes.AttributeFactory;
import com.dstevens.characters.changes.DecreaseAttribute;
import com.dstevens.characters.changes.GainXp;
import com.dstevens.characters.changes.IncreaseAttribute;
import com.dstevens.characters.changes.SetTrait;
import com.dstevens.characters.changes.SpendXp;
import com.dstevens.characters.changes.TraitChangeStatus;
import com.dstevens.game.TraitChangeBuilder;

public class AttributeChangeBuilder implements TraitChangeBuilder {

	private final AttributeFactory attributeFactory;

	public AttributeChangeBuilder(AttributeFactory attributeFactory) {
		this.attributeFactory = attributeFactory;
	}
	
	@Override
	public SetTrait buy() {
		return new SpendXp(TraitChangeStatus.PENDING, 3, increaseTrait());
	}

	@Override
	public SetTrait sell() {
		return new GainXp(TraitChangeStatus.PENDING, 3, decreaseTrait());
	}
	
	@Override
	public SetTrait add() {
		return increaseTrait();
	}

	@Override
	public SetTrait remove() {
		return decreaseTrait();
	}
	
	private IncreaseAttribute increaseTrait() {
        return new IncreaseAttribute(TraitChangeStatus.PENDING, attributeFactory);
    }
	
	private DecreaseAttribute decreaseTrait() {
		return new DecreaseAttribute(TraitChangeStatus.PENDING, attributeFactory);
	}
}
