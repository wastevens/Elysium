package com.dstevens.characters.traits;

import static com.dstevens.collections.Sets.set;

import java.util.Set;

import com.dstevens.characters.PlayerCharacter;

public abstract class SetApplicableTrait<T extends ApplicableTrait> extends SetTrait {

	protected SetApplicableTrait(TraitChangeStatus status) {
		super(status);
	}
	
	protected SetApplicableTrait(TraitChangeStatus status, int ordinal) {
		super(status, ordinal, 0, set(), null);
	}
	
	protected SetApplicableTrait(TraitChangeStatus status, int ordinal, int rating) {
		super(status, ordinal, rating, set(), null);
	}
	
	protected SetApplicableTrait(TraitChangeStatus status, int ordinal, String specialization) {
		super(status, ordinal, 0, set(), specialization);
	}
	
	protected SetApplicableTrait(TraitChangeStatus status, int ordinal, int rating, Set<String> focuses, String specialization) {
		super(status, ordinal, rating, focuses, specialization);
	}
	
	protected abstract T trait();
	
	public final PlayerCharacter apply(PlayerCharacter character) {
		return trait().copy().applyTo(character);
	}

	@Override
	public final PlayerCharacter remove(PlayerCharacter character) {
		return trait().copy().removeFrom(character);
	}

	@Override
	public final String describe() {
		return trait().toString();
	}

}
