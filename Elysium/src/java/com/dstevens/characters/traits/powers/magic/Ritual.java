package com.dstevens.characters.traits.powers.magic;

import com.dstevens.characters.traits.RatedTrait;
import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

public interface Ritual<T> extends RatedTrait {

	SetTrait set(TraitChangeStatus status);
}
