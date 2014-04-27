package com.dstevens.characters.changes;

import javax.persistence.*;

import com.dstevens.characters.traits.RatedTrait;

@Entity
@DiscriminatorValue("RatedTrait")
abstract class SetRatedTrait extends SetEnumeratedTrait {

    @Column(name="rating")
    private final int rating;
    
    protected SetRatedTrait(String id, TraitChangeStatus status, RatedTrait<?> trait) {
        this(id, status, trait.ordinal(), trait.rating());
    }
    
    protected SetRatedTrait(String id, TraitChangeStatus status, int ordinal, int rating) {
        super(id, status, ordinal);
        this.rating = rating;
    }

    protected final int rating() {
        return rating;
    }

}
