package com.dstevens.characters.changes;

import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.EnumeratedTrait;

@Entity
@DiscriminatorValue("EnumeratedTrait")
public class SetEnumeratedTrait extends SetTrait {

    @Column(name="ordinal")
    private final int ordinal;
    
    @Column(name="factory")
    final TraitFactory factory;
    
    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetEnumeratedTrait() {
        this(null, 0, null);
    }
    
    protected SetEnumeratedTrait(TraitChangeStatus status, EnumeratedTrait<?> trait, TraitFactory factory) {
        this(status, trait.ordinal(), factory);
    }
    
    protected SetEnumeratedTrait(TraitChangeStatus status, int ordinal, TraitFactory factory) {
        super(status);
        this.ordinal = ordinal;
        this.factory = factory;
    }

    protected final int ordinal() {
        return ordinal;
    }

    @Override
    public PlayerCharacter apply(PlayerCharacter character) {
        return factory.traitFor(ordinal).applyTo(character);
    }
    
    @Override
    public String describe() {
        return String.format("(%1$s) Set %1$s", status(), factory.traitFor(ordinal));
    }

}
