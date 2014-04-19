package com.dstevens.characters.skills;

import java.util.List;
import javax.persistence.*;

import com.dstevens.utilities.ObjectExtensions;

@Embeddable
public class CharacterSkill {
    
    @Column(name="skill")
    private final Skill skill;
    
    @Column(name="specialization")
    private String specialization;
    
    @Column(name="rating")
    private int rating;
    
    @Transient
    private List<String> focuses;
    
    private CharacterSkill() {
        this(null, 0, null);
    }
    
    public CharacterSkill(Skill skill, int rating) {
        this(skill, rating, null);
    }
    
    public CharacterSkill(Skill skill, int rating, String specialization) {
        this.skill = skill;
        this.rating = rating;
        this.specialization = specialization;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CharacterSkill) {
            CharacterSkill that = (CharacterSkill) obj;
            if (this.skill.equals(that.skill)) {
                if (this.specialization == null && that.specialization == null) {
                    return true;
                }
                if (this.specialization != null && that.specialization != null) {
                    return this.specialization.equalsIgnoreCase(that.specialization);
                }
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return ObjectExtensions.hashCodeFor(this);
    }
    
    @Override
    public String toString() {
        return ObjectExtensions.toStringFor(this);
    }
}
