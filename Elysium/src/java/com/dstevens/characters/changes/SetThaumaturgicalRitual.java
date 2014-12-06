package com.dstevens.characters.changes;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.powers.magics.ThaumaturgicalRitual;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ThaumaturgicalRitual")
public class SetThaumaturgicalRitual extends SetTrait {

	@Column(name="trait_ordinal")
    private ThaumaturgicalRitual trait;

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetThaumaturgicalRitual() {
        this(null, null);
    }
    
    public SetThaumaturgicalRitual(TraitChangeStatus status, ThaumaturgicalRitual trait) {
    	super(status);
		this.trait = trait;
    }
	
	@Override
	public PlayerCharacter apply(PlayerCharacter character) {
		return character.withThaumaturgicalRitual(trait);
	}

	@Override
	public PlayerCharacter remove(PlayerCharacter character) {
		return character.withoutThaumaturgicalRitual(trait);
	}

	@Override
	public String describe() {
		return trait.toString();
	}
	
}
