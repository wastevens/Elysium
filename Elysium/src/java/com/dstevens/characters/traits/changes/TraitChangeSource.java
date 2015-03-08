package com.dstevens.characters.traits.changes;

import java.util.Set;

import com.dstevens.characters.traits.ApplicableTrait;

public interface  TraitChangeSource<CharacterTrait extends ApplicableTrait> {

	TraitChange<CharacterTrait> set(int rating, String specialization, Set<String> focuses);
	
}
