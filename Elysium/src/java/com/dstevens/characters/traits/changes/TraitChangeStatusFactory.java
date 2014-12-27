package com.dstevens.characters.traits.changes;

import java.time.Clock;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.suppliers.ClockSupplier;

@Service
public class TraitChangeStatusFactory {

	private final Clock clock;

	@Autowired
	public TraitChangeStatusFactory(ClockSupplier clockSupplier) {
		this.clock = clockSupplier.get();
	}
	
	public TraitChangeStatus requeted() {
		return requestedOn(now());
	}
	
	public TraitChangeStatus requestedOn(Date pendingOn) {
		return new TraitChangeStatus(Status.REQUESTED, pendingOn);
	}
	
	public TraitChangeStatus applied() {
		return appliedOn(now());
	}
	public TraitChangeStatus appliedOn(Date appliedOn) {
		return new TraitChangeStatus(Status.APPLIED, appliedOn);
	}

	private Date now() {
		return Date.from(clock.instant());
	}

}
