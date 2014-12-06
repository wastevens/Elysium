package com.dstevens.characters.traits.distinctions;

public interface Distinction<T extends Enum<?>> {

    int getPoints();
    int ordinal();
    T trait();
    
    String getType();
    
}
