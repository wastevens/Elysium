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
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
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
    @OrderBy("changedOn DESC")
    @CollectionTable(name="TraitChangeStatus", joinColumns=@JoinColumn(name="traitChange_id"))
    @ForeignKey(name="TraitChange_TraitChangeStatus_FK", inverseName="TraitChangeStatus_TraitChange_FK")
    private List<TraitChangeStatus> traitChangeHistory;
    
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
    	this.traitChangeHistory = list();
    }
    
    public final TraitChange<?> and(TraitChange<?> andTrait) {
    	if(child != null) {
    		this.child.and(andTrait);
    	} else {
    		this.child = andTrait;
    	}
    	return this;
    }
    
    private final Stream<TraitChange<?>> stream() {
    	Builder<TraitChange<?>> builder = Stream.builder();
    	TraitChange<?> traitToAdd = this;
    	while(traitToAdd != null) {
    		builder.add(traitToAdd);
    		traitToAdd = traitToAdd.child;
    	}
    	return builder.build();
    }
    
	public final PlayerCharacter apply(final PlayerCharacter character) {
		stream().forEach((TraitChange<?> t) -> t.trait().applyTo(character));
		return character;
	}

	public final PlayerCharacter remove(PlayerCharacter character) {
		stream().forEach((TraitChange<?> t) -> t.trait().removeFrom(character));
		return character;
	}
    
    protected abstract T trait();
    
    public TraitChange<T> withStatus(TraitChangeStatus statusChanged) {
    	stream().forEach((TraitChange<?> t) -> t.traitChangeHistory.add(0, statusChanged));
    	return this;
    }
    
    public TraitChangeStatus currentStatus() {
    	return traitChangeHistory.get(0);
    }
    
    @Override
    public final String toString() {
        return ObjectExtensions.toStringFor(this);
    }
}
