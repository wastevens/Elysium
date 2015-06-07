package com.dstevens.character.trait.change;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.ForeignKey;

import com.dstevens.character.PlayerCharacter;
import com.dstevens.character.trait.ApplicableTrait;
import com.dstevens.character.trait.Trait;
import com.dstevens.character.trait.TraitQualities;
import com.dstevens.character.trait.TraitType;
import com.dstevens.utilities.Identified;

@SuppressWarnings("deprecation")
@Entity
@Table(name="TraitChanges")
public class TraitChange implements Identified<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tableGen")
	@TableGenerator(name = "tableGen", pkColumnValue = "traitChange", table="ID_Sequences", allocationSize=1 )
	@Column(name="id", nullable=false, unique=true)
    private final Long id;
    
    @Column(name="traitTypeOrdinal")
    protected final TraitType traitType;
    
    @Column(name="traitOrdinal")
    protected final int trait;

    @Embedded
    private TraitQualities qualities;
    
    @Column(name="remove")
    protected boolean remove;
    
    @OneToOne(cascade={CascadeType.ALL})
    @ForeignKey(name="TraitChange_ChildTraitChange_FK", inverseName="ChildTraitChange_TraitChange_FK")
    private TraitChange child;
    
    @Column(name="cost")
    public Integer cost;
    
    //Hibernate only
    @Deprecated
    protected TraitChange() {
    	this(null, -1, null);
    }
    
    protected TraitChange(TraitType traitType, int trait, TraitQualities qualities) {
		this.id = null;
    	this.traitType = traitType;
		this.trait = trait;
		this.qualities = qualities;
    	this.remove = false;
    	
    }
    
    public final TraitChange and(TraitChange andTrait) {
    	if(child != null) {
    		this.child.and(andTrait);
    	} else {
    		this.child = andTrait;
    	}
    	return this;
    }
    
    private final Stream<TraitChange> stream() {
    	Builder<TraitChange> builder = Stream.builder();
    	TraitChange traitToAdd = this;
    	while(traitToAdd != null) {
    		builder.add(traitToAdd);
    		traitToAdd = traitToAdd.child;
    	}
    	return builder.build();
    }
    
	public final PlayerCharacter apply(final PlayerCharacter character) {
		if(!remove) {
			return applyTraitsTo(character);
		} else {
			return removeTraitsFrom(character);
		}
	}
	
	public final PlayerCharacter remove(PlayerCharacter character) {
		if(!remove) {
			return removeTraitsFrom(character);
		} else {
			return applyTraitsTo(character);
		}
	}

	private PlayerCharacter applyTraitsTo(final PlayerCharacter character) {
		stream().forEach((TraitChange t) -> t.characterTrait().applyTo(character));
		return character;
	}

	private PlayerCharacter removeTraitsFrom(PlayerCharacter character) {
		stream().forEach((TraitChange t) -> t.characterTrait().removeFrom(character));
		return character;
	}
    
    protected ApplicableTrait characterTrait() {
    	return trait().applyWith(qualities);
    }

	private Trait trait() {
		return traitType.traits[trait];
	}
    
    public Optional<Integer> cost() {
    	return Optional.ofNullable(cost);
    }
    
    public TraitChange costing(int xp) {
    	this.cost = xp;
    	return this;
    }
    
    public TraitChange remove() {
    	this.remove = true;
    	return this;
    }
    
    public TraitChange restore() {
    	this.remove = false;
    	return this;
    }

	@Override
	public Long getId() {
		return id;
	}
    
	@Override
	public boolean equals(Object that) {
		return EqualsBuilder.reflectionEquals(this, that);
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
