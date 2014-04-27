package com.dstevens.characters.traits;

public interface RatedTrait<T extends Enum<?>> extends EnumeratedTrait<T> {

    int getRating();

    
}
