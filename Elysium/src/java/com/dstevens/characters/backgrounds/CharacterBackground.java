package com.dstevens.characters.backgrounds;

import static com.dstevens.collections.Sets.set;

import java.util.*;
import java.util.function.Function;
import javax.persistence.*;

import com.dstevens.utilities.ObjectExtensions;

@Entity
@Table(name="CharacterBackgrounds")
public class CharacterBackground implements Comparable<CharacterBackground> {
    
    @Id
    private final String id;
    
    @Column(name="character_id")
    private String characterId;
    
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
        this(null, null, null, 0, null, set());
    }
    
    public CharacterBackground(String id, String characterId, Background background, int rating, String specialization, Set<String> focuses) {
        this.id = id;
        this.characterId = characterId;
        this.background = background;
        this.rating = rating;
        this.specialization = specialization;
        this.focuses = focuses;
    }

    public final String getId() {
        return id;
    }

    public final String getCharacterId() {
        return characterId;
    }
    
    public final Background getBackground() {
        return background;
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
        if (obj instanceof CharacterBackground) {
            CharacterBackground that = CharacterBackground.class.cast(obj);
            return this.id.equals(that.id);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
    
    @Override
    public String toString() {
        return ObjectExtensions.toStringFor(this);
    }

    @Override
    public int compareTo(CharacterBackground that) {
        Function<CharacterBackground, Background> bySkill = ((CharacterBackground s )-> s.background);
        Function<CharacterBackground, String> bySpecialization = ((CharacterBackground s )-> s.specialization);
        return Comparator.comparing(bySkill).thenComparing(Comparator.comparing(bySpecialization)).compare(this, that); 
    }
}
