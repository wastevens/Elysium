package com.dstevens.characters.changes;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.powers.magics.Thaumaturgy;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("InClanThaumaturgy")
public class SetInClanThaumaturgy extends SetTrait {

	@Column(name="trait_ordinal")
    private Thaumaturgy trait;
	

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetInClanThaumaturgy() {
        this(null, null);
    }
    
    public SetInClanThaumaturgy(TraitChangeStatus status, Thaumaturgy trait) {
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
