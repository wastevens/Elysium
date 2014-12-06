package com.dstevens.characters.traits;

import com.dstevens.characters.PlayerCharacter;

public abstract class SetApplicableTrait<T extends ApplicableTrait> extends SetTrait {

	protected SetApplicableTrait(TraitChangeStatus status) {
		super(status);
	}
	
	protected abstract T trait();
	
	public final PlayerCharacter apply(PlayerCharacter character) {
		return trait().applyTo(character);
	}

	@Override
	public final PlayerCharacter remove(PlayerCharacter character) {
		return trait().removeFrom(character);
	}

	@Override
	public final String describe() {
		return trait().toString();
	}

}
