package com.dstevens.characters.traits.distinctions;

import java.util.Comparator;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.CharacterSpecializedTrait;
import com.dstevens.suppliers.IdSupplier;
import com.dstevens.utilities.ObjectExtensions;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Merits")
public class CharacterMerit implements ApplicableTrait, CharacterSpecializedTrait, Comparable<CharacterMerit> {

	@Id
    private final String id;
	
	@Basic(optional=false)
	private Merit trait;
	
	@Column(name="specialization")
    private String specialization;

	//Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private CharacterMerit() {
        this(null, null);
    }
	
	public CharacterMerit(Merit trait) {
		this(trait, null);
	}
	
	public CharacterMerit(Merit trait, String specialization) {
		this.id = new IdSupplier().get();
		this.trait = trait;
		this.specialization = specialization;
	}
	
	@Override
	public String getSpecialization() {
		return specialization;
	}
	
	@Override
	public PlayerCharacter applyTo(PlayerCharacter character) {
		return character.withMerit(this);
	}

	@Override
	public PlayerCharacter removeFrom(PlayerCharacter character) {
		return character.withoutMerit(this);
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
    public int compareTo(CharacterMerit that) {
        return characterDistinctionComparator().compare(this, that);
    }
    
    private Comparator<? super CharacterMerit> characterDistinctionComparator() {
        return Comparator.comparing((CharacterMerit t) -> t.trait).
 thenComparing(Comparator.comparing((CharacterMerit t) -> t.specialization));
    }

	public Predicate<CharacterMerit> matches() {
		return ((Predicate<CharacterMerit>)(CharacterMerit t) -> t.trait.equals(this.trait)).
		    and((Predicate<CharacterMerit>)(CharacterMerit t) -> StringUtils.equalsIgnoreCase(t.specialization, this.specialization));
	}
}
