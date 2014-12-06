package com.dstevens.characters.traits.distinctions;

import com.dstevens.characters.PlayerCharacter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

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
    
    @Override
    public PlayerCharacter applyTo(PlayerCharacter character) {
        return character.withFlaw(this);
    }
    
    @Override
    public PlayerCharacter removeFrom(PlayerCharacter character) {
    	return character.withoutFlaw(this);
    }
}
