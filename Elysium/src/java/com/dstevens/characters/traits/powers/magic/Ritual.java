package com.dstevens.characters.traits.powers.magic;

import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.RatedTrait;
import com.dstevens.characters.traits.changes.TraitChange;

public interface Ritual<T> extends RatedTrait {

	TraitChange<? extends ApplicableTrait> set();
}
