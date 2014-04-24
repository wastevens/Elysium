package com.dstevens.characters.merits;

import com.dstevens.characters.traits.*;

public interface Merit extends EnumeratedTrait, TypedTrait {

    int getPoints();
    
    default String getType() {
        return this.getClass().getAnnotation(MeritAnnotation.class).value();
    }
    
}
