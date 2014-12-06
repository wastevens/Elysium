package com.dstevens.characters.traits;

import com.dstevens.characters.PlayerCharacter;

public abstract class SetEnumeratedTrait<T extends EnumeratedTrait<?>> extends SetTrait {

	private final T trait;

	protected SetEnumeratedTrait(TraitChangeStatus status, T trait) {
		super(status);
		this.trait = trait;
	}

	public final PlayerCharacter apply(PlayerCharacter character) {
		return trait.applyTo(character);
	}

	@Override
	public final PlayerCharacter remove(PlayerCharacter character) {
		return trait.removeFrom(character);
	}

	@Override
	public final String describe() {
		return trait.toString();
	}

}
