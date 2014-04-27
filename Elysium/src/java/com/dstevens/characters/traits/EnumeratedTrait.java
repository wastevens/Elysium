package com.dstevens.characters.traits;



public interface EnumeratedTrait<T extends Enum<?>> {

    int ordinal();
    T trait();
    
}
