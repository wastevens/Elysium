package com.dstevens.characters.traits;

import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;

public interface CharacterSpecializedTrait<T extends Enum<?>> extends EnumeratedTrait<T> {

	String getSpecialization();
	
	default Predicate<? super CharacterSpecializedTrait<T>> specializedTraitMatches() {
		return (CharacterSpecializedTrait<T> t) -> t.trait().equals(this.trait()) && StringUtils.equalsIgnoreCase(t.getSpecialization(), this.getSpecialization());
	}
	
}
