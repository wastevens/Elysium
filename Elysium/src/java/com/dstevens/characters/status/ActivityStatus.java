package com.dstevens.characters.status;

public enum ActivityStatus {

	PRIMARY(10),
	SECONDARY(4),
	INACTIVE(0);
	
	private int maxXpPerMonth;

	private ActivityStatus(int maxXpPerMonth) {
		this.maxXpPerMonth = maxXpPerMonth;
	}
	
	public int maxXpPerMonth() {
		return maxXpPerMonth;
	}
	
}
