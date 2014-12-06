package com.dstevens.characters.traits.powers;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.changes.SetTrait;
import com.dstevens.characters.traits.changes.TraitChangeStatus;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("InClanNecromancy")
public class SetInClanNecromancy extends SetTrait {

	@Column(name="trait_ordinal")
    private Necromancy trait;
	

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetInClanNecromancy() {
        this(null, null);
    }
    
    public SetInClanNecromancy(TraitChangeStatus status, Necromancy trait) {
    	super(status);
		this.trait = trait;
    }
	
	@Override
	public PlayerCharacter apply(PlayerCharacter character) {
		return character.withInClanDiscipline(trait);
	}

	@Override
	public PlayerCharacter remove(PlayerCharacter character) {
		return character.withoutInClanDiscipline(trait);
	}

	@Override
	public String describe() {
		return trait.toString();
	}

}
