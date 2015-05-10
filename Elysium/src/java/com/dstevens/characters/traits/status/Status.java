package com.dstevens.characters.traits.status;

import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.Trait;
import com.dstevens.characters.traits.TraitQualities;

public enum Status implements Trait {

	AWESOME,
	NOT_AWESOME;
	
	@Override
	public int id() {
		return 0;
	}
	
	@Override
	public ApplicableTrait applyWith(TraitQualities qualities) {
		return new CharacterStatus(this, qualities.specialization);
	};
	
}
