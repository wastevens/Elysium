package com.dstevens.characters.merits;

import com.dstevens.characters.traits.*;

public interface Merit<T extends Enum<?>> extends EnumeratedTrait<T>, TypedTrait {

    int getPoints();
    
    default String getType() {
        return this.getClass().getAnnotation(MeritAnnotation.class).value();
    }
}
