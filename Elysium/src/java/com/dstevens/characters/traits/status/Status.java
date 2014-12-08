package com.dstevens.characters.traits.status;

import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.SetTraitFactory;
import com.dstevens.characters.traits.TraitChangeStatus;

public enum Status implements SetTraitFactory {

	AWESOME,
	NOT_AWESOME;
	
	public SetTrait set(TraitChangeStatus status, String specialization) {
		return new SetStatus(status, this.ordinal(), specialization);
	};
	
}
