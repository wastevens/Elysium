package com.dstevens.characters.traits;

import com.dstevens.utilities.Identified;

public enum DetailLevel implements Identified<Integer> {

    NONE(0),
    ALLOWS_FOCUS(1),
    REQUIRES_FOCUS(2),
    REQUIRES_SPECIALIZATION(3),
    REQUIRES_SPECIALIZATION_AND_FOCUS(4);
    
    private final int id;

	private DetailLevel(int id) {
		this.id = id;
	}
    
	@Override
	public Integer getId() {
		return id;
	}
    
}
