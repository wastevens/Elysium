package com.dstevens.characters.traits.changes;

import static com.dstevens.collections.Lists.list;

import static com.dstevens.collections.Sets.set;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import org.hibernate.annotations.ForeignKey;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.suppliers.IdSupplier;
import com.dstevens.utilities.ObjectExtensions;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@SuppressWarnings("deprecation")
@Entity
@Inheritance
@DiscriminatorColumn(name="trait_change_type")
@Table(name="TraitChanges")
public abstract class TraitChange<T extends ApplicableTrait> {

    @Id
    private final String id;
    
    @Column(name="ordinal")
    protected final int ordinal;
    
    @Column(name="rating")
    protected final int rating;
    
    @Column(name="specialization")
    protected final String specialization;
    
    @ElementCollection
    @ForeignKey(name= "TraitChanges_focuses_FK")
    protected Set<String> focuses;
    
    @OneToOne(cascade={CascadeType.ALL})
    @ForeignKey(name="TraitChange_ChildTraitChange_FK", inverseName="ChildTraitChange_TraitChange_FK")
    private TraitChange<?> child;
    
    @ElementCollection
    @OrderBy("order")
    @ForeignKey(name= "TraitChanges_StatusChanges_FK")
    private List<StatusChange> statusChanges;
    
    //Hibernate only
    @Deprecated
    protected TraitChange() {
    	this(-1, -1, null, set());
    }
    
    protected TraitChange(int ordinal) {
    	this(ordinal, -1, null, set());
    }
    
    protected TraitChange(int ordinal, int rating) {
    	this(ordinal, rating, null, set());
    }
    
    protected TraitChange(int ordinal, String specialization) {
    	this(ordinal, -1, specialization, set());
    }
    
    protected TraitChange(int ordinal, int rating, String specialization, Set<String> focuses) {
    	this.id = new IdSupplier().get();
    	this.ordinal = ordinal;
    	this.rating = rating;
    	this.specialization = specialization;
    	this.focuses = focuses;
    	this.statusChanges = list();
    }
    
    public final TraitChange<?> and(TraitChange<?> andTrait) {
    	if(child != null) {
    		this.child.and(andTrait);
    	} else {
    		this.child = andTrait;
    	}
    	return this;
    }
    
    public final PlayerCharacter approve(final PlayerCharacter character) {
    	this.stream().forEach((TraitChange<?> t) -> t.apply(character));
        return character;
    }

    protected final Stream<TraitChange<?>> stream() {
    	Builder<TraitChange<?>> builder = Stream.builder();
    	TraitChange<?> traitToAdd = this;
    	while(traitToAdd != null) {
    		builder.add(traitToAdd);
    		traitToAdd = traitToAdd.child;
    	}
    	return builder.build();
    }
    
	public PlayerCharacter apply(PlayerCharacter character) {
		return trait().applyTo(character);
	}

	public PlayerCharacter remove(PlayerCharacter character) {
		return trait().removeFrom(character);
	}
    
    protected abstract T trait();
    
    @Override
    public final String toString() {
        return ObjectExtensions.toStringFor(this);
    }
}
