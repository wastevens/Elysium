package com.dstevens.characters.changes;

import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;

@Entity
@DiscriminatorValue("RatedTrait")
public class SetRatedTrait extends SetEnumeratedTrait {

    @Column(name="rating")
    private final int rating;
    
    @Column(name="factory")
    final TraitFactory factory;
    
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
        super(status, ordinal);
        this.rating = rating;
        this.factory = factory;
    }

    protected final int rating() {
        return rating;
    }
    
    public PlayerCharacter apply(PlayerCharacter character) {
        return factory.traitFor(ordinal(), rating()).applyTo(character);
    }

}
