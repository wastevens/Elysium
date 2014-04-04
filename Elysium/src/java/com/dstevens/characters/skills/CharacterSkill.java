package com.dstevens.characters.skills;

import static com.dstevens.collections.Lists.list;

import java.util.List;

public class CharacterSkill {

    private static final String NO_SPECIALIZATION = null;
    private static final List<String> NO_FOCUSES = list();
    
    private final Skill skill;
    private final int rating;
    private final String specialization;
    private final List<String> focuses;
    
    public CharacterSkill(Skill skill, int rating) {
        this(skill, rating, NO_SPECIALIZATION, NO_FOCUSES);
    }
    
    public CharacterSkill(Skill skill, int rating, String specialization) {
        this(skill, rating, specialization, NO_FOCUSES);
    }
    
    public CharacterSkill(Skill skill, int rating, List<String> focuses) {
        this(skill, rating, NO_SPECIALIZATION, focuses);
    }

    private CharacterSkill(Skill skill, int rating, String specialization, List<String> focuses) {
        this.skill = skill;
        this.rating = rating;
        this.specialization = specialization;
        this.focuses = focuses;
    }
    
}
