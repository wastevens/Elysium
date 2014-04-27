package com.dstevens.characters.skills;

import static com.dstevens.collections.Sets.set;

import java.util.*;
import java.util.function.Function;
import javax.persistence.*;

import com.dstevens.characters.traits.CharacterDefinedTrait;
import com.dstevens.utilities.ObjectExtensions;

@Entity
@Table(name="CharacterSkills")
public class CharacterSkill implements CharacterDefinedTrait<Skill>, Comparable<CharacterSkill> {
    
    @Id
    private final String id;
    
    @Column(name="skill")
    private final Skill skill;
    
    @Column(name="specialization")
    private String specialization;
    
    @Column(name="rating")
    private int rating;
    
    @ElementCollection
    @CollectionTable(name="CharacterSkillFocuses")
    @Column(name="focus")
    private Set<String> focuses;

    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private CharacterSkill() {
        this(null, null, 0, null, set());
    }
    
    public CharacterSkill(String id, Skill skill, int rating, String specialization, Set<String> focuses) {
        this.id = id;
        this.skill = skill;
        this.rating = rating;
        this.specialization = specialization;
        this.focuses = focuses;
    }

    public final Skill getSkill() {
        return skill;
    }
    
    public final int ordinal() {
        return skill.ordinal();
    }

    public final String getSpecialization() {
        return specialization;
    }

    public final int getRating() {
        return rating;
    }

    public final Set<String> getFocuses() {
        return focuses;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CharacterSkill) {
            CharacterSkill that = CharacterSkill.class.cast(obj);
            return this.skill.equals(that.skill) &&
                   this.rating == that.rating &&
                   this.focuses.equals(that.focuses) &&
                   nullsafeEquals(this.specialization, that.specialization);
        }
        return false;
    }
    
    private boolean nullsafeEquals(String thisSpecialization, String thatSpecialization) {
        if (thisSpecialization == thatSpecialization) return true;
        if (thisSpecialization == null || thatSpecialization == null) return false;
        return thisSpecialization.equals(thatSpecialization);
    }

    @Override
    public int hashCode() {
        return skill.hashCode() + rating + focuses.hashCode();
    }
    
    @Override
    public String toString() {
        return ObjectExtensions.toStringFor(this);
    }

    @Override
    public int compareTo(CharacterSkill that) {
        Function<CharacterSkill, Integer> byRating = ((CharacterSkill s)-> s.rating);
        Function<CharacterSkill, Skill> bySkill = ((CharacterSkill s)-> s.skill);
        Function<CharacterSkill, String> bySpecialization = ((CharacterSkill s)-> s.specialization);
        return Comparator.comparing(byRating).reversed().
                      thenComparing(bySkill).
                      thenComparing(Comparator.comparing(bySpecialization, Comparator.nullsLast(Comparator.naturalOrder()))).
                      compare(this, that); 
    }

    @Override
    public Skill getTrait() {
        return skill;
    }
}
