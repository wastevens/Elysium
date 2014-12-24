package com.dstevens.characters.traits.changes;

import static com.dstevens.collections.Sets.set;

import java.util.Set;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;

public abstract class ApplicableTraitChange<T extends ApplicableTrait> extends TraitChange {

	//Hibernate only
	@SuppressWarnings("deprecation")
	protected ApplicableTraitChange() {
		super();
	}
	
	protected ApplicableTraitChange(int ordinal) {
		super(ordinal, 0, null, set());
	}
	
	protected ApplicableTraitChange(int ordinal, int rating) {
		super(ordinal, rating, null, set());
	}
	
	protected ApplicableTraitChange(int ordinal, String specialization) {
		super(ordinal, 0, specialization, set());
	}
	
	protected ApplicableTraitChange(int ordinal, int rating, String specialization, Set<String> focuses) {
		super(ordinal, rating, specialization, focuses);
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
