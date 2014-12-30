package com.dstevens.characters.status;

import static com.dstevens.time.DateTimeUtilities.asLocalDateInUTC;

import java.time.LocalDate;
import java.util.Date;

import com.dstevens.utilities.ObjectExtensions;

import javax.persistence.Embeddable;

@Embeddable
public class ApprovalStatusChange implements Comparable<ApprovalStatusChange>{

	private PlayerStatus status;
	private Date changedOn;

	//Hibernate only
    @SuppressWarnings("unused")
	@Deprecated
    private ApprovalStatusChange() {
    	this(null, null);
    }
	
	public ApprovalStatusChange(PlayerStatus status, Date changedOn) {
		this.status = status;
		this.changedOn = changedOn;
	}
	
	public PlayerStatus status() {
		return status;
	}
	
	public LocalDate changedOn() {
		return asLocalDateInUTC(changedOn);
	}

	@Override
	public boolean equals(Object that) {
		return ObjectExtensions.equals(this, that);
	}
	
	@Override
	public int hashCode() {
		return ObjectExtensions.hashCodeFor(this);
	}
	
	@Override
	public String toString() {
		return ObjectExtensions.toStringFor(this);
	}
	
	@Override
	public int compareTo(ApprovalStatusChange that) {
		return this.changedOn.compareTo(that.changedOn);
	}
	
}
