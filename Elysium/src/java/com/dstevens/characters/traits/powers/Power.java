package com.dstevens.characters.traits.powers;

import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.changes.TraitChange;



public interface Power<T> extends ApplicableTrait {
	
	TraitChange set();
	TraitChange set(int rating);

	
}
