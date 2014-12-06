package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MentalFocus")
public class SetMentalFocus extends SetTrait {

	@Column(name="trait_ordinal")
    private MentalAttributeFocus trait;

    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetMentalFocus() {
        this(null, null);
    }
    
    public SetMentalFocus(TraitChangeStatus status, MentalAttributeFocus trait) {
    	super(status);
    	this.trait = trait;
    }
    
    @Override
    public PlayerCharacter apply(PlayerCharacter character) {
    	return character.withMentalAttributeFocus(trait);
    }
    
    @Override
    public PlayerCharacter remove(PlayerCharacter character) {
    	return character.withoutMentalAttributeFocus(trait);
    }
    
    @Override
    public String describe() {
        String nextTrait = (hasAssociatedTrait() ? String.format("with %1$s", associatedTrait().describe()) : "");
        return String.format("(%1$s) Added mental focus %2$s %3$s", status(), trait, nextTrait);
    }
}
