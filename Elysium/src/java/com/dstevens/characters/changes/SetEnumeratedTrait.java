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
    
    @SuppressWarnings("unchecked")
    public <E> E trait() {
        return (E) this.getClass().getAnnotation(TraitType.class).type().getEnumConstants()[ordinal];
    }
    
    @Override
    public String describe() {
        return String.format("(%1$s) Set %1$s", status(), trait());
    }

}
