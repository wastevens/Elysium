package com.dstevens.characters.traits.distinctions;

public interface Distinction<T> {

    int getPoints();
    int ordinal();
    T trait();
    
    String getType();
    
}
