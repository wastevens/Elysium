package com.dstevens.characters.traits.powers.magic;

import com.dstevens.characters.traits.RatedTrait;
import com.dstevens.characters.traits.changes.TraitChange;
import com.dstevens.characters.traits.changes.TraitChangeStatus;

public interface Ritual<T> extends RatedTrait {

	TraitChange set(TraitChangeStatus status);
}
