package com.dstevens.character.trait.background;

import java.util.Comparator;
import java.util.Set;
import java.util.function.Predicate;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.ForeignKey;

import com.dstevens.character.PlayerCharacter;
import com.dstevens.character.trait.ApplicableTrait;
import com.dstevens.character.trait.CharacterFocusedTrait;
import com.dstevens.character.trait.CharacterSpecializedTrait;
import com.dstevens.character.trait.RatedTrait;
import com.dstevens.utilities.ObjectExtensions;

import static com.dstevens.collections.Sets.set;

@SuppressWarnings("deprecation")
@Entity
@Table(name="Backgrounds")
public class CharacterBackground implements ApplicableTrait, RatedTrait, CharacterSpecializedTrait, CharacterFocusedTrait, Comparable<CharacterBackground> {
    
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tableGen")
	@TableGenerator(name = "tableGen", pkColumnValue = "characterBackground", table="ID_Sequences", allocationSize=1 )
	@Column(name="id", nullable=false, unique=true)
    private final Integer id;
    
    @Column(name="background")
    private final Background trait;
    
    @Column(name="specialization")
    private String specialization;
    
    @Column(name="rating")
    private int rating;
    
	@ElementCollection
	@CollectionTable(name="Backgrounds_Focuses")
    @ForeignKey(name="CharacterBackground_focuses_FK")
    private Set<String> focuses;

    //Hibernate only
    @SuppressWarnings("unused")
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
    
    CharacterBackground(Background trait, int rating, String specialization, Set<String> focuses) {
        this.id = null;
        this.trait = trait;
        this.rating = rating;
        this.specialization = specialization;
        this.focuses = focuses;
    }

    public final Background trait() {
        return trait;
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
    public boolean equals(Object that) {
    	return EqualsBuilder.reflectionEquals(this, that, "id");
    }

    @Override
    public int hashCode() {
    	return HashCodeBuilder.reflectionHashCode(this, "id");
    }
    
    @Override
    public String toString() {
        return ObjectExtensions.toStringFor(this);
    }

    @Override
    public int compareTo(CharacterBackground that) {
		return Comparator.comparing((CharacterBackground t) -> t.trait).
				      thenComparing((CharacterBackground t) -> t.specialization).
				      thenComparing(byFocuses()).
				      compare(this, that);
    }

    public PlayerCharacter applyTo(PlayerCharacter character) {
        return character.withBackground(this);
    }
    
    public PlayerCharacter removeFrom(PlayerCharacter character) {
    	return character.withoutBackground(this);
    }

	public Predicate<CharacterBackground> matches() {
		return ((Predicate<CharacterBackground>)(CharacterBackground t) -> t.trait.equals(this.trait)).
		    and((Predicate<CharacterBackground>)(CharacterBackground t) -> StringUtils.equalsIgnoreCase(t.specialization, this.specialization));
	}
}
