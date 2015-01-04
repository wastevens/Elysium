package com.dstevens.characters.traits.distinctions;

import com.dstevens.characters.clans.Bloodline;
import com.dstevens.characters.traits.changes.TraitChange;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Bloodline")
public class SetBloodline extends TraitChange<Bloodline> {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetBloodline() {
    	super();
    }
    
    public SetBloodline(int ordinal) {
    	super(ordinal);
    }

	@Override
	protected Bloodline trait() {
		return Bloodline.values()[ordinal];
	}
	
}
