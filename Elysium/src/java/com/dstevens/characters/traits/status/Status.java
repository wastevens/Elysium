package com.dstevens.characters.traits.status;

import com.dstevens.characters.traits.TraitChange;
import com.dstevens.characters.traits.changes.TraitChangeStatus;

public enum Status {

	AWESOME,
	NOT_AWESOME;
	
	public TraitChange set(TraitChangeStatus status, String specialization) {
		return new SetStatus(status, this.ordinal(), specialization);
	};
	
}
