package com.dstevens.character;

import com.dstevens.utilities.Identified;

public enum Setting implements Identified<Integer> {

    CAMARILLA(0),
    ANARCH(1),
    SABBAT(2),
    INDEPENDENT_ALLIANCE(3);

    private final int id;

	private Setting(int id) {
		this.id = id;
	}
    
	@Override
	public Integer getId() {
		return id;
	}
}
