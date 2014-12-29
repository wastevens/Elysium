package com.dstevens.characters.activity;

public enum ActivityStatus {

	PRIMARY(10),
	SECONDARY(4),
	AWAITING_APPROVAL(0),
	BEING_CREATED(0),
	INACTIVE(0);
	
	private int maxXpPerMonth;

	private ActivityStatus(int maxXpPerMonth) {
		this.maxXpPerMonth = maxXpPerMonth;
	}
	
	public int maxXpPerMonth() {
		return maxXpPerMonth;
	}
	
}
