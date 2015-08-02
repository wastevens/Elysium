package com.dstevens.character.status;

import com.dstevens.utilities.Identified;
import com.dstevens.utilities.IdentityUtilities;

public enum ApprovalStatus implements Identified<Integer> {

	IN_CREATION(0),
	APPROVAL_REQUESTED(1),
	APPROVED(2);
	
	private final int id;

	private ApprovalStatus(int id) {
		this.id = id;
	}
	
	public static ApprovalStatus from(int id) {
		return IdentityUtilities.withId(id, ApprovalStatus.values());
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	
}
