package com.dstevens.characters.changes;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.powers.ElderPower;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ElderPower")
public class SetElderPower extends SetTrait {

	@Column(name="trait_ordinal")
    private ElderPower trait;
	

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetElderPower() {
        this(null, null);
    }
    
    public SetElderPower(TraitChangeStatus status, ElderPower trait) {
    	super(status);
		this.trait = trait;
    }
	
	@Override
	public PlayerCharacter apply(PlayerCharacter character) {
		return character.withElderPower(trait);
	}

	@Override
	public PlayerCharacter remove(PlayerCharacter character) {
		return character.withoutElderPower(trait);
	}

	@Override
	public String describe() {
		return trait.toString();
	}

}
