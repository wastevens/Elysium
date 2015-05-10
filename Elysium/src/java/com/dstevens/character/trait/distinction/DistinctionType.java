package com.dstevens.character.trait.distinction;

import com.dstevens.utilities.Identified;

public enum DistinctionType implements Identified<Integer> {

	GENERAL(0),
	MORALITY(1),
	RARITY(2),
	CLAN(3),
	BLOODLINE(4),
	SETTING(5);
	
	private final int id;

	private DistinctionType(int id) {
		this.id = id;
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	
}
