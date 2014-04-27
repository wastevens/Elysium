package com.dstevens.characters.traits;

import java.util.Set;

public interface CharacterDefinedTrait<T extends Enum<?>> extends RatedTrait<T> {

    String getSpecialization();
    Set<String> getFocuses();
    
}
