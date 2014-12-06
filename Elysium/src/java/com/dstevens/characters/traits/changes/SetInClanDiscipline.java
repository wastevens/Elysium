package com.dstevens.characters.traits.changes;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.powers.Discipline;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("InClanDiscipline")
public class SetInClanDiscipline extends SetTrait {

	@Column(name="trait_ordinal")
    private Discipline trait;
	

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetInClanDiscipline() {
        this(null, null);
    }
    
    public SetInClanDiscipline(TraitChangeStatus status, Discipline trait) {
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
