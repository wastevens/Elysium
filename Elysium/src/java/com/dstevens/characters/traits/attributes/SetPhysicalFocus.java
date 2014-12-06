package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PhysicalFocus")
public class SetPhysicalFocus extends SetTrait {

	@Column(name="trait_ordinal")
    private PhysicalAttributeFocus trait;

    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetPhysicalFocus() {
        this(null, null);
    }
    
    public SetPhysicalFocus(TraitChangeStatus status, PhysicalAttributeFocus trait) {
    	super(status);
    	this.trait = trait;
    }
    
    @Override
    public PlayerCharacter apply(PlayerCharacter character) {
    	return character.withPhysicalAttributeFocus(trait);
    }
    
    @Override
    public PlayerCharacter remove(PlayerCharacter character) {
    	return character.withoutPhysicalAttributeFocus(trait);
    }
    
    @Override
    public String describe() {
        String nextTrait = (hasAssociatedTrait() ? String.format("with %1$s", associatedTrait().describe()) : "");
        return String.format("(%1$s) Added physical focus %2$s %3$s", status(), trait, nextTrait);
    }
}
