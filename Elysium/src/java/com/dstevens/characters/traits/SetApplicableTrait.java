package com.dstevens.characters.traits;

import static com.dstevens.collections.Sets.set;

import java.util.Set;

import com.dstevens.characters.PlayerCharacter;

public abstract class SetApplicableTrait<T extends ApplicableTrait> extends SetTrait {

	protected SetApplicableTrait() {
		super(null);
	}
	
	protected SetApplicableTrait(TraitChangeStatus status) {
		super(status);
	}
	
	protected SetApplicableTrait(TraitChangeStatus status, int ordinal) {
		super(status, ordinal, 0, null, set());
	}
	
	protected SetApplicableTrait(TraitChangeStatus status, int ordinal, int rating) {
		super(status, ordinal, rating, null, set());
	}
	
	protected SetApplicableTrait(TraitChangeStatus status, int ordinal, String specialization) {
		super(status, ordinal, 0, specialization, set());
	}
	
	protected SetApplicableTrait(TraitChangeStatus status, int ordinal, int rating, String specialization, Set<String> focuses) {
		super(status, ordinal, rating, specialization, focuses);
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
