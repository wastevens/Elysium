package com.dstevens.characters.skills;

import java.util.Set;
import java.util.function.Predicate;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.CharacterDefinedTrait;
import com.dstevens.suppliers.IdSupplier;
import com.dstevens.utilities.ObjectExtensions;

import static com.dstevens.collections.Sets.set;

@Entity
@Table(name="Skills")
public class CharacterSkill implements CharacterDefinedTrait<Skill>, Comparable<CharacterSkill> {
    
    @Id
    private final String id;
    
    @Column(name="skill")
    private final Skill trait;
    
    @Column(name="specialization")
    private String specialization;
    
    @Column(name="rating")
    private int rating;
    
    @ElementCollection
    private Set<String> focuses;

    //Hibernate only
    @Deprecated
    private CharacterSkill() {
        this(null, 0, null, set());
    }
    
    private CharacterSkill(Skill trait, int rating, String specialization, Set<String> focuses) {
        this.id = new IdSupplier().get();
        this.trait = trait;
        this.rating = rating;
        this.specialization = specialization;
        this.focuses = focuses;
    }

    private static final String NO_SPECIALIZATION = null;
    private static final Set<String> NO_FOCUSES = set();
    
    public static CharacterSkill skillFor(Skill skill, int rating) {
        return skillFor(skill, rating, NO_SPECIALIZATION, NO_FOCUSES);
    }
    
    public static CharacterSkill skillFor(Skill skill, int rating, String specialization) {
        return skillFor(skill, rating, specialization, NO_FOCUSES);
    }
    
    public static CharacterSkill skillFor(Skill skill, int rating, Set<String> focuses) {
        return skillFor(skill, rating, NO_SPECIALIZATION, focuses);
    }
    
    public static CharacterSkill skillFor(Skill skill, int rating, String specialization,  Set<String> focuses) {
        return new CharacterSkill(skill, rating, specialization, focuses);
    }
    
    public static Predicate<CharacterSkill> matching(CharacterSkill trait) {
		return (CharacterSkill t) -> t.trait().equals(trait.trait()) && (t.getSpecialization() == trait.getSpecialization() || t.getSpecialization().equalsIgnoreCase(trait.getSpecialization()));
	}
    
    @Override
    public final Skill trait() {
        return trait;
    }
    
    public final int ordinal() {
        return trait.ordinal();
    }

    public final String getSpecialization() {
        return specialization;
    }

    public final int rating() {
        return rating;
    }

    public final Set<String> getFocuses() {
        return focuses;
    }

    @Override
    public boolean equals(Object obj) {
        return characterDefinedTraitEquals(obj);
    }

    @Override
    public int hashCode() {
        return characterDefinedTraitHashcode();
    }
    
    @Override
    public String toString() {
        return ObjectExtensions.toStringFor(this);
    }

    @Override
    public int compareTo(CharacterSkill that) {
        return characterDefinedTraitComparator().compare(this, that);
    }
    
    @Override
    public PlayerCharacter applyTo(PlayerCharacter character) {
        return character.withSkill(this);
    }
}
