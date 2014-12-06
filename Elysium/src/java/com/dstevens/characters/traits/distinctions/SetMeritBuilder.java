package com.dstevens.characters.traits.distinctions;

import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.SpendXp;
import com.dstevens.characters.traits.TraitChangeBuilder;
import com.dstevens.characters.traits.TraitChangeStatus;

public class SetMeritBuilder implements TraitChangeBuilder {

	private Merits merit;
	private String specialization;
	private SetTrait associatedChange;

	public SetMeritBuilder(Merits merit) {
		this(merit, null, null);
	}
	
	public SetMeritBuilder(Merits merit, String specialization) {
		this(merit, specialization, null);
	}
	
	public SetMeritBuilder(Merits merit, String specialization, SetTrait associatedchange) {
		this.merit = merit;
		this.specialization = specialization;
		this.associatedChange = associatedchange;
	}
	
	@Override
	public SetTrait buy() {
		return new SpendXp(TraitChangeStatus.PENDING, merit.getPoints()).and(merit());
	}

	@Override
	public SetTrait add() {
		return merit();
	}
	
	private SetTrait merit() {
		return new SetMerit(TraitChangeStatus.PENDING, new CharacterMerit(merit, specialization)).and(associatedChange);
	}

	@Override
	public SetTrait sell() {
		throw new IllegalStateException("nyi");
	}

	@Override
	public SetTrait remove() {
		throw new IllegalStateException("nyi");
	}

}
