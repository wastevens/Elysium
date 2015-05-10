package com.dstevens.characters.traits;

import com.dstevens.utilities.Identified;

public interface Trait extends Identified<Integer> {

	ApplicableTrait applyWith(TraitQualities qualities);
	
}
