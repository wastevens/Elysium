package com.dstevens.characters.status;

import com.dstevens.utilities.Identified;

public enum PlayerStatus implements Identified<Integer> {

	PRIMARY(0, 10, 4),
	SECONDARY(1, 4, 0),
	INACTIVE(2, 0, 0);
	
	private final int id;
	private final int maxXpPerMonth;
	private final int groundXpPerMonth;

	private PlayerStatus(int id, int maxXpPerMonth, int groundXpPerMonth) {
		this.id = id;
		this.maxXpPerMonth = maxXpPerMonth;
		this.groundXpPerMonth = groundXpPerMonth;
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	
	public int maxXpPerMonth() {
		return maxXpPerMonth;
	}
	
	public int groundXpPerMonth() {
		return groundXpPerMonth;
	}
	
}
