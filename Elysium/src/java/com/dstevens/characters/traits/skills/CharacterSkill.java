package com.dstevens.characters.traits.skills;

import static com.dstevens.collections.Sets.set;

import java.util.Comparator;
import java.util.Set;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.ForeignKey;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.CharacterFocusedTrait;
import com.dstevens.characters.traits.CharacterSpecializedTrait;
import com.dstevens.characters.traits.EnumeratedTrait;
import com.dstevens.characters.traits.RatedTrait;
import com.dstevens.suppliers.IdSupplier;
import com.dstevens.utilities.ObjectExtensions;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("deprecation")
@Entity
@Table(name="Skills")
public class CharacterSkill implements EnumeratedTrait<Skill>, ApplicableTrait, RatedTrait, CharacterSpecializedTrait, CharacterFocusedTrait, Comparable<CharacterSkill> {
    
    @Id
    private final String id;
    
    @Column(name="skill")
    private final Skill trait;
    
    @Column(name="specialization")
    private String specialization;
    
    @Column(name="rating")
    private int rating;
    
	@ElementCollection
    @ForeignKey(name= "CharacterSkill_focuses_FK")
    private Set<String> focuses;

    //Hibernate only
    @SuppressWarnings("unused")
	@Deprecated
    private CharacterSkill() {
        this(null, 0, null, set());
    }
    
    public CharacterSkill(Skill trait, int rating, String specialization, Set<String> focuses) {
        this.id = new IdSupplier().get();
        this.trait = trait;
        this.rating = rating;
        this.specialization = specialization;
        this.focuses = focuses;
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
    public int compareTo(CharacterSkill that) {
		return Comparator.comparing((CharacterSkill t) -> t.trait).
			          thenComparing((CharacterSkill t) -> t.specialization).
			          thenComparing(byFocuses()).
			          compare(this, that);
    }
    
    @Override
    public PlayerCharacter applyTo(PlayerCharacter character) {
        return character.withSkill(this);
    }
    
    @Override
    public PlayerCharacter removeFrom(PlayerCharacter character) {
    	return character.withoutSkill(this);
    }

	public Predicate<CharacterSkill> matches() {
		return ((Predicate<CharacterSkill>)(CharacterSkill t) -> t.trait.equals(this.trait)).
		    and((Predicate<CharacterSkill>)(CharacterSkill t) -> StringUtils.equalsIgnoreCase(t.specialization, this.specialization));
	}

	@Override
	public ApplicableTrait copy() {
		return new CharacterSkill(trait, rating, specialization, focuses);
	}
}
