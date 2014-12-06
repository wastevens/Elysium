package com.dstevens.characters.traits.powers;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.changes.SetTrait;
import com.dstevens.characters.traits.changes.TraitChangeStatus;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("Thaumaturgy")
public class SetThaumaturgy extends SetTrait {

	@OneToOne(cascade={CascadeType.ALL}, optional=true)
	@JoinColumn(name="trait_id", referencedColumnName="id", foreignKey=@ForeignKey(name="none"))
    private CharacterThaumaturgy trait;

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetThaumaturgy() {
        this(null, null);
    }
    
    public SetThaumaturgy(TraitChangeStatus status, CharacterThaumaturgy trait) {
    	super(status);
		this.trait = trait;
    }

    public PlayerCharacter apply(PlayerCharacter character) {
        return trait.applyTo(character);
    }
    
    @Override
	public PlayerCharacter remove(PlayerCharacter character) {
    	return trait.removeFrom(character);
	}
    
    @Override
    public String describe() {
        String nextTrait = (hasAssociatedTrait() ? String.format (" and %1$s", associatedTrait().describe()) : "");
        
        return String.format("(%1$s) Set %2$s to %3$s%4$s", status(), trait, rating(), nextTrait);
    }

	private int rating() {
		return trait.rating();
	}
}
