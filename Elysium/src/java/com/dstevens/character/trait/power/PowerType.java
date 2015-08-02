package com.dstevens.character.trait.power;

import com.dstevens.utilities.Identified;
import com.dstevens.utilities.IdentityUtilities;

public enum PowerType implements Identified<Integer> {

	DISCIPLINE(0),
	NECROMANCY(1),
	THAUMATURGY(2);
	
	private final int id;

	private PowerType(int id) {
		this.id = id;
	}
	
	public static PowerType from(int id) {
		return IdentityUtilities.withId(id, PowerType.values());
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	
}
