package com.dstevens.characters.traits;

import java.util.Comparator;


public interface RatedTrait<T extends Enum<?>> extends EnumeratedTrait<T> {

    int getRating();
    
    default Comparator<? super RatedTrait<T>> ratedTraitComparator() {
        return Comparator.comparing((RatedTrait<T> t) -> t.getRating()).reversed().
                      thenComparing((RatedTrait<T> t) -> t.ordinal());
    }
    
}
