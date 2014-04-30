package com.dstevens.characters.merits;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.*;


public interface Flaw<T extends Enum<?>> extends EnumeratedTrait<T>, TypedTrait {
    
    int getPoints();
    
    default String getType() {
        return this.getClass().getAnnotation(FlawAnnotation.class).value();
    }
    
    default PlayerCharacter applyTo(PlayerCharacter character) {
        throw new IllegalStateException("Flaw does not implement applyTo!");
    }
    
}
