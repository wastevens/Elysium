package com.dstevens.characters.traits;

import java.util.Comparator;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


public interface RatedTrait<T> extends EnumeratedTrait<T> {

    int rating();
    
    default boolean ratedTraitEquals(Object that) {
    	return EqualsBuilder.reflectionEquals(this, that, "id");
    }
    
    default int ratedTraitHashcode() {
    	return HashCodeBuilder.reflectionHashCode(this, "id");
    }
    
    default Comparator<? super RatedTrait<T>> ratedTraitComparator() {
        return Comparator.comparing((RatedTrait<T> t) -> t.rating()).reversed().
                      thenComparing((RatedTrait<T> t) -> t.ordinal());
    }
}
