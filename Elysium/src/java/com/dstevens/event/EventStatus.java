package com.dstevens.event;

import com.dstevens.utilities.Identified;
import com.dstevens.utilities.IdentityUtilities;

public enum EventStatus implements Identified<Integer> {

	PENDING(0),
	ACTIVE(1),
	CONCLUDED(2),
	;
	
	private final int id;

	private EventStatus(int id) {
		this.id = id;
	}
	
	public static EventStatus from(int id) {
		return IdentityUtilities.withId(id, EventStatus.values());
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	
}
