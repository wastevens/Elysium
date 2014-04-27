package com.dstevens.characters.traits;

import java.util.*;

import org.apache.commons.lang3.builder.*;

public interface CharacterDefinedTrait<T extends Enum<?>> extends RatedTrait<T> {

    String getSpecialization();
    Set<String> getFocuses();
    
    default boolean characterDefinedTraitEquals(Object that) {
        return EqualsBuilder.reflectionEquals(this, that, "id");
    }
    
    default int characterDefinedTraitHashcode() {
        return HashCodeBuilder.reflectionHashCode(this, "id");
    }
    
    default Comparator<? super CharacterDefinedTrait<T>> byFocuses() {
        return new Comparator<CharacterDefinedTrait<T>>() {
            @Override
            public int compare(CharacterDefinedTrait<T> o1, CharacterDefinedTrait<T> o2) {
                String[] array1 = (String[]) o1.getFocuses().stream().sorted().toArray();
                String[] array2 = (String[]) o1.getFocuses().stream().sorted().toArray();
                if (array1.length != array2.length) return array1.length - array2.length;
                for(int i=0;i<array1.length;i++) {
                    if (array1[i].compareTo(array2[i]) != 0) {
                        return array1[i].compareTo(array2[i]);
                    }
                }
                return 0;
            }
        };
    }
    
    default Comparator<? super CharacterDefinedTrait<T>> characterDefinedTraitComparator() {
        return Comparator.comparing((CharacterDefinedTrait<T> t) -> t.getRating()).reversed().
                      thenComparing((CharacterDefinedTrait<T> t) -> t.ordinal()).
                      thenComparing((CharacterDefinedTrait<T> t) -> t.getSpecialization()).
                      thenComparing(byFocuses());
    }
}
