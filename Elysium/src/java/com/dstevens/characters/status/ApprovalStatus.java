package com.dstevens.characters.status;

import com.dstevens.utilities.Identified;

public enum ApprovalStatus implements Identified<Integer> {

	IN_CREATION(0),
	APPROVAL_REQUESTED(1),
	APPROVED(2);
	
	private final int id;

	private ApprovalStatus(int id) {
		this.id = id;
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	
}
