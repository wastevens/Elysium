package com.dstevens.characters.traits.backgrounds;

import static com.dstevens.collections.Sets.set;

import java.util.Set;

import org.hibernate.annotations.ForeignKey;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.CharacterDefinedTrait;
import com.dstevens.suppliers.IdSupplier;
import com.dstevens.utilities.ObjectExtensions;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("deprecation")
@Entity
@Table(name="Backgrounds")
public class CharacterBackground implements CharacterDefinedTrait<Background>, Comparable<CharacterBackground> {
    
    @Id
    private final String id;
    
    @Column(name="background")
    private final Background trait;
    
    @Column(name="specialization")
    private String specialization;
    
    @Column(name="rating")
    private int rating;
    
	@ElementCollection
    @ForeignKey(name="CharacterBackground_focuses_FK")
    private Set<String> focuses;

    //Hibernate only
    @Deprecated
    private CharacterBackground() {
        this(null, 0, null, set());
    }
    
    private static final String NO_SPECIALIZATION = null;
    private static final Set<String> NO_FOCUSES = set();
    
    public static CharacterBackground backgroundFor(Background background, int rating) {
        return new CharacterBackground(background, rating, NO_SPECIALIZATION, NO_FOCUSES);
    }
    
    public static CharacterBackground backgroundFor(Background background, int rating, String specialization) {
        return new CharacterBackground(background, rating, specialization, NO_FOCUSES);
    }
    
    public static CharacterBackground backgroundFor(Background background, int rating, Set<String> focuses) {
        return new CharacterBackground(background, rating, NO_SPECIALIZATION, focuses);
    }
    
    public static CharacterBackground backgroundFor(Background background, int rating, String specialization, Set<String> focuses) {
        return new CharacterBackground(background, rating, specialization, focuses);
    }
    
    private CharacterBackground(Background trait, int rating, String specialization, Set<String> focuses) {
        this.id = new IdSupplier().get();
        this.trait = trait;
        this.rating = rating;
        this.specialization = specialization;
        this.focuses = focuses;
    }

    @Override
    public final Background trait() {
        return trait;
    }

    @Override
    public final int ordinal() {
        return trait.ordinal();
    }

    @Override
    public final String getSpecialization() {
        return specialization;
    }

    @Override
    public final int rating() {
        return rating;
    }

    @Override
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
    public int compareTo(CharacterBackground that) {
        return characterDefinedTraitComparator().compare(this, that);
    }

    public PlayerCharacter applyTo(PlayerCharacter character) {
        return character.withBackground(this);
    }
    
    public PlayerCharacter removeFrom(PlayerCharacter character) {
    	return character.withoutBackground(this);
    }
}
