package com.dstevens.characters.status;

public enum ActivityStatus {

	PRIMARY(10, 4),
	SECONDARY(4, 0),
	INACTIVE(0, 0);
	
	private int maxXpPerMonth;
	private int groundXpPerMonth;

	private ActivityStatus(int maxXpPerMonth, int groundXpPerMonth) {
		this.maxXpPerMonth = maxXpPerMonth;
		this.groundXpPerMonth = groundXpPerMonth;
	}
	
	public int maxXpPerMonth() {
		return maxXpPerMonth;
	}
	
	public int groundXpPerMonth() {
		return groundXpPerMonth;
	}
	
}
