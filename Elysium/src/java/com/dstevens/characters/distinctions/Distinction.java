package com.dstevens.characters.distinctions;

public interface Distinction<T extends Enum<?>> {

    int getPoints();
    int ordinal();
    T trait();
    
    String getType();
    
}
