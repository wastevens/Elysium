package com.dstevens.characters.backgrounds;

import static com.dstevens.collections.Sets.set;

import java.util.Set;
import javax.persistence.*;

import com.dstevens.characters.traits.CharacterDefinedTrait;
import com.dstevens.utilities.ObjectExtensions;

@Entity
@Table(name="CharacterBackgrounds")
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
    @CollectionTable(name="CharacterBackgroundFocuses")
    @Column(name="focus")
    private Set<String> focuses;

    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private CharacterBackground() {
        this(null, null, 0, null, set());
    }
    
    public CharacterBackground(String id, Background trait, int rating, String specialization, Set<String> focuses) {
        this.id = id;
        this.trait = trait;
        this.rating = rating;
        this.specialization = specialization;
        this.focuses = focuses;
    }

    @Override
    public final Background getTrait() {
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
    public final int getRating() {
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
}
