package com.dstevens.characters.traits.status;

import com.dstevens.characters.traits.SetApplicableTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Status")
public class SetStatus extends SetApplicableTrait<CharacterStatus> {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetStatus() {
        this(null, 0, null);
    }
    
    public SetStatus(TraitChangeStatus status, int ordinal, String specialization) {
    	super(status, ordinal, specialization);
    }
	
	@Override
	protected CharacterStatus trait() {
		return new CharacterStatus(Status.values()[ordinal], specialization);
	}

}
