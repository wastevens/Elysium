package com.dstevens.characters.traits.changes;

import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.TraitQualities;

public interface  CharacterTraitSource<CharacterTrait extends ApplicableTrait> {

	CharacterTrait with(TraitQualities qualities);
	
}
