package com.dstevens.characters.traits;

import java.util.Set;

public interface CharacterDefinedTrait {

    String getId();
    String getCharacterId();
    int getRating();
    int ordinal();
    String getSpecialization();
    Set<String> getFocuses();
    
}
