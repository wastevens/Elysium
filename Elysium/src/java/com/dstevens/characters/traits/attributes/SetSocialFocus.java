package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SocialFocus")
public class SetSocialFocus extends SetTrait {

	@Column(name="trait_ordinal")
    private SocialAttributeFocus trait;

    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetSocialFocus() {
        this(null, null);
    }
    
    public SetSocialFocus(TraitChangeStatus status, SocialAttributeFocus trait) {
    	super(status);
    	this.trait = trait;
    }
    
    @Override
    public PlayerCharacter apply(PlayerCharacter character) {
    	return character.withSocialAttributeFocus(trait);
    }
    
    @Override
    public PlayerCharacter remove(PlayerCharacter character) {
    	return character.withoutSocialAttributeFocus(trait);
    }
    
    @Override
    public String describe() {
        String nextTrait = (hasAssociatedTrait() ? String.format("with %1$s", associatedTrait().describe()) : "");
        return String.format("(%1$s) Added social focus %2$s %3$s", status(), trait, nextTrait);
    }
}
