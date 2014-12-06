package com.dstevens.characters.changes;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.powers.CharacterTechnique;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("Technique")
public class SetTechnique extends SetTrait {

	@OneToOne(cascade={CascadeType.ALL}, optional=true)
	@JoinColumn(name="trait_id", referencedColumnName="id", foreignKey=@ForeignKey(name="none"))
    private CharacterTechnique trait;
	

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetTechnique() {
        this(null, null);
    }
    
    public SetTechnique(TraitChangeStatus status, CharacterTechnique trait) {
    	super(status);
		this.trait = trait;
    }
	
	@Override
	public PlayerCharacter apply(PlayerCharacter character) {
		return trait.applyTo(character);
	}

	@Override
	public PlayerCharacter remove(PlayerCharacter character) {
		return trait.removeFrom(character);
	}

	@Override
	public String describe() {
		return trait.toString();
	}

}
