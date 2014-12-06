package com.dstevens.characters.traits;

import java.util.Comparator;
import java.util.function.Predicate;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.dstevens.characters.PlayerCharacter;



public interface EnumeratedTrait<T> {

    int ordinal();
    T trait();
    PlayerCharacter applyTo(PlayerCharacter character);
    PlayerCharacter removeFrom(PlayerCharacter character);
    
    default Predicate<? super EnumeratedTrait<T>> matches() {
		return (EnumeratedTrait<T> t) -> t.trait().equals(this.trait());
	}
    
    default boolean characterTraitEquals(Object that) {
    	return EqualsBuilder.reflectionEquals(this, that, "id");
    }
    
    default int characterTraitHashcode() {
    	return HashCodeBuilder.reflectionHashCode(this, "id");
    }
    
    default Comparator<? super EnumeratedTrait<T>> enumeratedTraitComparator() {
        return Comparator.comparing((EnumeratedTrait<T> t) -> t.ordinal());
    }
    
}
