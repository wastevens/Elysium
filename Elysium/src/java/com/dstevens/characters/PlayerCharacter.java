package com.dstevens.characters;

import java.util.List;

import com.dstevens.characters.attributes.Attribute;
import com.dstevens.characters.skills.CharacterSkill;

public class PlayerCharacter {

    private long id;
    
    private Attribute physicalTraits;
    private Attribute socialTraits;
    private Attribute mentalTraits;
    
    private List<CharacterSkill> skills;
    
}
