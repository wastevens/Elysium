package com.dstevens.characters.distinctions;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value="Merit")
public class CharacterMerit extends CharacterDistinction {
    
    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private CharacterMerit() {
        this(0, null, null);
    }
    
    public CharacterMerit(Merit<?> merit) {
        this(merit, null);
    }
    
    public CharacterMerit(Merit<?> merit, String details) {
        this(merit.ordinal(), merit.getType(), details);
    }
    
    private CharacterMerit(int meritId, String meritType, String details) {
        super(meritId, meritType, details);
    }
    
    public Distinction<?> getDistinction() {
        return MeritTranslator.ofTypeWithId(type(), ordinal());
    }
    
}
