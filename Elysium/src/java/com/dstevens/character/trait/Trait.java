package com.dstevens.character.trait;

import com.dstevens.utilities.Identified;

public interface Trait extends Identified<Integer> {

	ApplicableTrait applyWith(TraitQualities qualities);
	
}
