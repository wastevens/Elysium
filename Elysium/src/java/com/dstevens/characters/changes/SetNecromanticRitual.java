package com.dstevens.characters.changes;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.powers.magics.NecromanticRitual;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("NecromanticRitual")
public class SetNecromanticRitual extends SetTrait {

	@Column(name="trait_ordinal")
    private NecromanticRitual trait;

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetNecromanticRitual() {
        this(null, null);
    }
    
    public SetNecromanticRitual(TraitChangeStatus status, NecromanticRitual trait) {
    	super(status);
		this.trait = trait;
    }
	
	@Override
	public PlayerCharacter apply(PlayerCharacter character) {
		return character.withNecromanticRitual(trait);
	}

	@Override
	public PlayerCharacter remove(PlayerCharacter character) {
		return character.withoutNecromanticRitual(trait);
	}

	@Override
	public String describe() {
		return trait.toString();
	}
	
}
