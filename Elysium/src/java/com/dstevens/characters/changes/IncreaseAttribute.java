package com.dstevens.characters.changes;

import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;

@Entity
@DiscriminatorValue("IncreaseAttribute")
class IncreaseAttribute extends SetTrait {

    @Column(name="factory")
    private AttributeFactory factory;
    
    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private IncreaseAttribute() {
        this(null,null);
    }
    
    protected IncreaseAttribute(TraitChangeStatus status, AttributeFactory factory) {
        this(status, null, factory);
    }
    
    private IncreaseAttribute(TraitChangeStatus status, SetTrait associatedTrait, AttributeFactory factory) {
        super(status, associatedTrait);
        this.factory = factory;
    }
    
    @Override
    public PlayerCharacter apply(PlayerCharacter character) {
        return factory.increase(character);
    }
    
    @Override
    public String describe() {
        String nextTrait = (hasAssociatedTrait() ? String.format(" with %1$s", associatedTrait().describe()) : "");
        return String.format("(%1$s) Increase %2$s%3$s", status(), factory.attributeName(), nextTrait);
    }
}
