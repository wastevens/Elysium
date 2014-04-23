package com.dstevens.characters.changes;

import javax.persistence.*;

import com.dstevens.characters.traits.RatedTrait;

@Entity
@DiscriminatorValue("RatedTrait")
public abstract class SetRatedTrait extends TraitChange {

    @Column(name="ordinal")
    private final int ordinal;
    
    @Column(name="rating")
    private final int rating;
    
    public SetRatedTrait(String id, TraitChangeStatus status, RatedTrait trait) {
        this(id, status, trait.ordinal(), trait.getRating());
    }
    
    public SetRatedTrait(String id, TraitChangeStatus status, int ordinal, int rating) {
        super(id, status);
        this.ordinal = ordinal;
        this.rating = rating;
    }

    protected final int ordinal() {
        return ordinal;
    }

    protected final int rating() {
        return rating;
    }

}
