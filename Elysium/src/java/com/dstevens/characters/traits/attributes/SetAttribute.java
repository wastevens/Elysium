package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Attribute")
public class SetAttribute extends SetTrait {

    @Column(name="rating")
    private int rating;

    @Column(name="factory")
    private AttributeFactory factory;
    
    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetAttribute() {
        this(null, 0, null);
    }
    
    public SetAttribute(TraitChangeStatus status, int rating, AttributeFactory factory) {
    	super(status);
    	this.rating = rating;
    	this.factory = factory;
    }
    
    @Override
    public PlayerCharacter apply(PlayerCharacter character) {
        return factory.applyTo(rating, character);
    }
    
    @Override
    public PlayerCharacter remove(PlayerCharacter character) {
    	throw new IllegalStateException("Cannot remove setting an attribute.");
    }
    
    @Override
    public String describe() {
        String nextTrait = (hasAssociatedTrait() ? String.format(" with %1$s", associatedTrait().describe()) : "");
        return String.format("(%1$s) Set %2$s%3$s", status(), factory.attributeName(), nextTrait);
    }
}
