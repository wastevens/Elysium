package com.dstevens.characters.traits.distinctions;


public interface Merit<T extends Enum<?>> extends Distinction<T> {
    
    default String getType() {
        return this.getClass().getAnnotation(MeritAnnotation.class).value();
    }
}
