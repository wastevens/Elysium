package com.dstevens.characters.traits;

import java.util.Comparator;

import org.apache.commons.lang3.builder.*;


public interface RatedTrait<T extends Enum<?>> extends EnumeratedTrait<T> {

    int rating();
    
    default boolean ratedTraitEquals(Object that) {
        return EqualsBuilder.reflectionEquals(this, that, "rating");
    }
    
    default int ratedTraitHashcode() {
        return HashCodeBuilder.reflectionHashCode(this, "rating");
    }
    
    default Comparator<? super RatedTrait<T>> ratedTraitComparator() {
        return Comparator.comparing((RatedTrait<T> t) -> t.rating()).reversed().
                      thenComparing((RatedTrait<T> t) -> t.ordinal());
    }
}
