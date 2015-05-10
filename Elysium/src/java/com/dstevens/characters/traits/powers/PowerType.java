package com.dstevens.characters.traits.powers;

import com.dstevens.utilities.Identified;

public enum PowerType implements Identified<Integer> {

	DISCIPLINE(0),
	NECROMANCY(1),
	THAUMATURGY(2);
	
	private final int id;

	private PowerType(int id) {
		this.id = id;
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	
}
