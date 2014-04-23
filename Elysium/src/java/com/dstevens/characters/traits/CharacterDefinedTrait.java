package com.dstevens.characters.traits;

import java.util.Set;

public interface CharacterDefinedTrait extends RatedTrait {

    String getId();
    String getSpecialization();
    Set<String> getFocuses();
    
}
