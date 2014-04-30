package com.dstevens.characters.distinctions;

import com.dstevens.characters.traits.TypedTrait;

public interface Merit<T extends Enum<?>> extends TypedTrait {

    int getPoints();
    int ordinal();
    T trait();
    
    default String getType() {
        return this.getClass().getAnnotation(MeritAnnotation.class).value();
    }
}
