package com.dstevens.characters.traits;

import java.util.function.Predicate;

import com.dstevens.characters.PlayerCharacter;



public interface EnumeratedTrait<T extends Enum<?>> {

    int ordinal();
    T trait();
    PlayerCharacter applyTo(PlayerCharacter character);
    
    default Predicate<? super EnumeratedTrait<T>> matches() {
		return (EnumeratedTrait<T> t) -> t.trait().equals(this.trait());
	}
    
}
