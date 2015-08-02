package com.dstevens.event;

public enum EventStatus {

	PENDING(0),
	ACTIVE(1),
	CONCLUDED(2),
	;
	
	private final int id;

	private EventStatus(int id) {
		this.id = id;
	}
	
	public static EventStatus from(int id) {
		for (EventStatus status : EventStatus.values()) {
			if(status.id == id) {
				return status;
			}
		}
		throw new IllegalArgumentException("Could not find an event status with id " + id);
	}
	
	public int getId() {
		return id;
	}
	
}
