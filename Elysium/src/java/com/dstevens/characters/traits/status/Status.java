package com.dstevens.characters.traits.status;

import com.dstevens.characters.traits.changes.TraitChange;

public enum Status {

	AWESOME,
	NOT_AWESOME;
	
	public TraitChange set(String specialization) {
		return new SetStatus(this.ordinal(), specialization);
	};
	
}
