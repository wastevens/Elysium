package com.dstevens.characters.distinctions;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value="Flaw")
public class CharacterFlaw extends CharacterDistinction {
    
    //Hibernate only
    @Deprecated
    private CharacterFlaw() {
        super(0, null, null);
    }
    
    public CharacterFlaw(Flaw<?> flaw) {
        super(flaw);
    }
    
    public CharacterFlaw(Flaw<?> flaw, String details) {
        super(flaw, details);
    }
    
    public Distinction<?> getDistinction() {
        return FlawTranslator.ofTypeWithId(type(), ordinal());
    }
}
