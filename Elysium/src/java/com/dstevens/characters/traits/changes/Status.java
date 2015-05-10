package com.dstevens.characters.traits.changes;

import com.dstevens.utilities.Identified;

public enum Status implements Identified<Integer> {

	REQUESTED(0),
	APPLIED(1),
	DENIED(2);
	
	private final int id;

	private Status(int id) {
		this.id = id;
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	
}
