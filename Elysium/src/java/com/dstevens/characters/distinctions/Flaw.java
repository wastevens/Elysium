package com.dstevens.characters.distinctions;



public interface Flaw<T extends Enum<?>> extends Distinction<T> {
    
    default String getType() {
        return this.getClass().getAnnotation(FlawAnnotation.class).value();
    }
    
}
