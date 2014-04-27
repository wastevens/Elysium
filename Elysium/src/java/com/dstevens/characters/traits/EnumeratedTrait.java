package com.dstevens.characters.traits;

import java.util.Comparator;


public interface EnumeratedTrait<T extends Enum<?>> {

    int ordinal();
    T getTrait();
    
    default Comparator<? super EnumeratedTrait<T>> enumeratedComparator() {
        return Comparator.comparing((EnumeratedTrait<T> t) -> t.ordinal());
    }
}
