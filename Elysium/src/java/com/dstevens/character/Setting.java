package com.dstevens.character;

import com.dstevens.utilities.Identified;
import com.dstevens.utilities.IdentityUtilities;

public enum Setting implements Identified<Integer> {

    CAMARILLA(0),
    ANARCH(1),
    SABBAT(2),
    INDEPENDENT_ALLIANCE(3);

    private final int id;

	private Setting(int id) {
		this.id = id;
	}
    
	public static Setting from(int id) {
		return IdentityUtilities.withId(id, Setting.values());
	}
	
	@Override
	public Integer getId() {
		return id;
	}
}
