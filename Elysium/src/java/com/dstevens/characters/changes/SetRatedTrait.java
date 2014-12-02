package com.dstevens.characters.changes;

import com.dstevens.characters.PlayerCharacter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("RatedTrait")
public class SetRatedTrait extends SetEnumeratedTrait {

    @Column(name="rating")
    private final int rating;
    
    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetRatedTrait() {
        this(null, 0, 0, null);
    }

    public SetRatedTrait(TraitChangeStatus status, Enum<?> trait, int rating, TraitFactory factory) {
        this(status, trait.ordinal(), rating, factory);
    }
    
    protected SetRatedTrait(TraitChangeStatus status, int ordinal, int rating, TraitFactory factory) {
        super(status, ordinal, factory);
        this.rating = rating;
    }

    protected final int rating() {
        return rating;
    }
    
    public PlayerCharacter apply(PlayerCharacter character) {
        return factory.traitFor(ordinal(), rating()).applyTo(character);
    }
    
    @Override
	public PlayerCharacter remove(PlayerCharacter character) {
    	return factory.traitFor(ordinal(), rating()).removeFrom(character);
	}
    
    @Override
    public String describe() {
        String nextTrait = (hasAssociatedTrait() ? String.format (" with %1$s", associatedTrait().describe()) : "");
        
        return String.format("(%1$s) Set %2$s to %3$s%4$s", status(), factory.trait(ordinal()), rating(), nextTrait);
    }

}
