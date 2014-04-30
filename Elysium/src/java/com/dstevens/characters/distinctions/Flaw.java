package com.dstevens.characters.distinctions;

import com.dstevens.characters.traits.TypedTrait;


public interface Flaw<T extends Enum<?>> extends TypedTrait {
    
    int getPoints();
    int ordinal();
    T trait();
    
    default String getType() {
        return this.getClass().getAnnotation(FlawAnnotation.class).value();
    }
    
}
