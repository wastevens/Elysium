package com.dstevens.characters.traits.changes;

import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class TraitChangeStatus {

	private Status status;
	
	@SuppressWarnings("unused")
	private Date changedOn;
	
	//Hibernate only
	@Deprecated
	@SuppressWarnings("unused")
	private TraitChangeStatus() {
		this(null,null);
	}
	
	public TraitChangeStatus(Status status, Date changedOn) {
		this.status = status;
		this.changedOn = changedOn;
	}
	
	public boolean pending() {
		return status.equals(Status.PENDING);
	}
	
	public boolean applied() {
		return status.equals(Status.APPLIED);
	}
}
