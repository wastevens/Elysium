package com.dstevens.characters.backgrounds;

import static com.dstevens.collections.Sets.set;

import java.util.*;
import java.util.function.Function;
import javax.persistence.*;

import com.dstevens.characters.traits.CharacterDefinedTrait;
import com.dstevens.utilities.ObjectExtensions;

@Entity
@Table(name="CharacterBackgrounds")
public class CharacterBackground implements CharacterDefinedTrait<Background>, Comparable<CharacterBackground> {
    
    @Id
    private final String id;
    
    @Column(name="background")
    private final Background background;
    
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
    
    public CharacterBackground(String id, Background background, int rating, String specialization, Set<String> focuses) {
        this.id = id;
        this.background = background;
        this.rating = rating;
        this.specialization = specialization;
        this.focuses = focuses;
    }

    public final Background getBackground() {
        return background;
    }

    @Override
    public final Background getTrait() {
        return background;
    }
    
    @Override
    public final int ordinal() {
        return background.ordinal();
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
        if (obj instanceof CharacterBackground) {
            CharacterBackground that = CharacterBackground.class.cast(obj);
            return this.background.equals(that.background) &&
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
        return background.hashCode() + rating + focuses.hashCode();
    }
    
    @Override
    public String toString() {
        return ObjectExtensions.toStringFor(this);
    }

    @Override
    public int compareTo(CharacterBackground that) {
        Function<CharacterBackground, Integer> byRating = ((CharacterBackground s)-> s.rating);
        Function<CharacterBackground, Background> bySkill = ((CharacterBackground s)-> s.background);
        Function<CharacterBackground, String> bySpecialization = ((CharacterBackground s)-> s.specialization);
        return Comparator.comparing(byRating).reversed().
                      thenComparing(bySkill).
                      thenComparing(Comparator.comparing(bySpecialization, Comparator.nullsLast(Comparator.naturalOrder()))).
                      compare(this, that); 
    }
}
