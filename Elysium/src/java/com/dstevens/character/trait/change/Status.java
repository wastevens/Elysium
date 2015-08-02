package com.dstevens.character.trait.change;

import com.dstevens.utilities.Identified;
import com.dstevens.utilities.IdentityUtilities;

public enum Status implements Identified<Integer> {

	REQUESTED(0),
	APPLIED(1),
	DENIED(2);
	
	private final int id;

	private Status(int id) {
		this.id = id;
	}
    
	public static Status from(int id) {
		return IdentityUtilities.withId(id, Status.values());
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	
}
