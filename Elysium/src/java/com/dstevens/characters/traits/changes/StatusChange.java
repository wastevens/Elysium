package com.dstevens.characters.traits.changes;

import java.util.Date;

class StatusChange implements Comparable<StatusChange> {

	Status status;
	Date changed;
	
	public StatusChange() {
		this(null, null);
	}
	
	StatusChange(Status status, Date changed) {
		this.status = status;
		this.changed = changed;
	}

	@Override
	public int compareTo(StatusChange that) {
		return this.changed.compareTo(that.changed);
	}
}
