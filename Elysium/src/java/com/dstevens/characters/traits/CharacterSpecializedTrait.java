package com.dstevens.characters.traits;

import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;

public interface CharacterSpecializedTrait {

	String getSpecialization();
	
	default Predicate<? super CharacterSpecializedTrait> specializedTraitMatches() {
		return (CharacterSpecializedTrait t) -> StringUtils.equalsIgnoreCase(t.getSpecialization(), this.getSpecialization());
	}
	
}
