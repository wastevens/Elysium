package com.dstevens.character.trait.skill;

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
@Table(name="Skills")
public class CharacterSkill implements ApplicableTrait, RatedTrait, CharacterSpecializedTrait, CharacterFocusedTrait, Comparable<CharacterSkill> {
    
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tableGen")
	@TableGenerator(name = "tableGen", pkColumnValue = "characterSkill", table="ID_Sequences", allocationSize=1 )
	@Column(name="id", nullable=false, unique=true)
    private final Integer id;
    
    @Column(name="skill")
    private final Skill trait;
    
    @Column(name="specialization")
    private String specialization;
    
    @Column(name="rating")
    private int rating;
    
	@ElementCollection
	@CollectionTable(name="Skills_Focuses")
    @ForeignKey(name= "CharacterSkill_focuses_FK")
    private Set<String> focuses;

    //Hibernate only
    @SuppressWarnings("unused")
	@Deprecated
    private CharacterSkill() {
        this(null, 0, null, set());
    }
    
    public CharacterSkill(Skill trait, int rating, String specialization, Set<String> focuses) {
        this.id = null;
        this.trait = trait;
        this.rating = rating;
        this.specialization = specialization;
        this.focuses = focuses;
    }

    public final Skill trait() {
        return trait;
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
}
