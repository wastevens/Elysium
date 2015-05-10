package com.dstevens.characters.traits.status;

import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.Trait;
import com.dstevens.characters.traits.TraitQualities;

public enum Status implements Trait {

	AWESOME(0),
	NOT_AWESOME(1);
	
	private final int id;

	private Status(int id) {
		this.id = id;
	}
	
	@Override
	public int id() {
		return id;
	}
	
	@Override
	public ApplicableTrait applyWith(TraitQualities qualities) {
		return new CharacterStatus(this, qualities.specialization);
	};
	
}
