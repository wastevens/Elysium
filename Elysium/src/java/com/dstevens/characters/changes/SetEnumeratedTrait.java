package com.dstevens.characters.changes;

import javax.persistence.*;

import com.dstevens.characters.traits.EnumeratedTrait;

@Entity
@DiscriminatorValue("EnumeratedTrait")
abstract class SetEnumeratedTrait extends SetTrait {

    @Column(name="ordinal")
    private final int ordinal;
    
    protected SetEnumeratedTrait(String id, TraitChangeStatus status, EnumeratedTrait<?> trait) {
        this(id, status, trait.ordinal());
    }
    
    protected SetEnumeratedTrait(String id, TraitChangeStatus status, int ordinal) {
        super(id, status);
        this.ordinal = ordinal;
    }

    protected final int ordinal() {
        return ordinal;
    }

}
