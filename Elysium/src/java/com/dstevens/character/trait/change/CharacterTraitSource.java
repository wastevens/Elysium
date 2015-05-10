package com.dstevens.character.trait.change;

import com.dstevens.character.trait.ApplicableTrait;
import com.dstevens.character.trait.TraitQualities;

public interface  CharacterTraitSource<CharacterTrait extends ApplicableTrait> {

	CharacterTrait with(TraitQualities qualities);
	
}
