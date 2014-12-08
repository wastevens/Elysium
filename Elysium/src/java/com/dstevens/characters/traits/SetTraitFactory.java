package com.dstevens.characters.traits;

import java.util.Set;

public interface SetTraitFactory {
	
	public default SetTrait set(TraitChangeStatus status) {
		throw new IllegalStateException(this.getClass().getSimpleName() + "#set(status) not implemented");
	}
	
	public default SetTrait set(TraitChangeStatus status, int rating) {
		throw new IllegalStateException(this.getClass().getSimpleName() + "#set(status, rating) not implemented");
	}
	
	public default SetTrait set(TraitChangeStatus status, String specialization) {
		throw new IllegalStateException(this.getClass().getSimpleName() + "#set(status, specialization) not implemented");
	}

	public default SetTrait set(TraitChangeStatus status, int rating, String specialization, Set<String> focuses) {
    	throw new IllegalStateException(this.getClass().getSimpleName() + "#set(status, rating, specialization, focuses) not implemented");
    }
	
}
