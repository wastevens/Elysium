package com.dstevens.characters.traits.distinctions;

import com.dstevens.characters.clans.Clan;
import com.dstevens.characters.traits.changes.TraitChange;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Clan")
public class SetClan extends TraitChange<Clan> {

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetClan() {
    	super();
    }
    
    public SetClan(int ordinal) {
    	super(ordinal);
    }

	@Override
	protected Clan trait() {
		return Clan.values()[ordinal];
	}
	
}
