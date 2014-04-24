package com.dstevens.characters.merits;

import com.dstevens.characters.traits.*;


public interface Flaw extends EnumeratedTrait, TypedTrait {
    
    int getPoints();
    
    default String getType() {
        return this.getClass().getAnnotation(FlawAnnotation.class).value();
    }
    
}
