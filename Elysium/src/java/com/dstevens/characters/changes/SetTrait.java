package com.dstevens.characters.changes;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.suppliers.IdSupplier;
import com.dstevens.utilities.ObjectExtensions;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Inheritance
@DiscriminatorColumn(name="trait_change_type")
@Table(name="TraitChanges")
public abstract class SetTrait {

    @Id
    private final String id;
    
    @Column(name="status")
    private TraitChangeStatus status;
    
    @OneToOne(cascade={CascadeType.ALL})
    private SetTrait associatedTrait;
    
    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private SetTrait() {
        this(null);
    }
    
    protected SetTrait(TraitChangeStatus status) {
        this(status, null);
    }
    
    private SetTrait(TraitChangeStatus status, SetTrait associatedTrait) {
        this.id = new IdSupplier().get();
        this.status = status;
        this.associatedTrait = associatedTrait;
    }
    
    public SetTrait and(SetTrait andTrait) {
    	if(associatedTrait != null) {
    		this.associatedTrait.and(andTrait);
    	} else {
    		this.associatedTrait = andTrait;
    	}
    	return this;
    }
    
    public final PlayerCharacter approve(PlayerCharacter character) {
        return status.apply(character, this);
    }
    
    public final PlayerCharacter deny(PlayerCharacter character) {
        return status.deny(character, this);
    }

    public boolean hasAssociatedTrait() {
        return associatedTrait != null;
    }

    public SetTrait associatedTrait() {
        return associatedTrait;
    }
    
    public final void setStatus(TraitChangeStatus status) {
        this.status = status;
    }
    
    protected final TraitChangeStatus status() {
        return status;
    }
    
    public final boolean isPending() {
        return this.status.equals(TraitChangeStatus.PENDING);
    }

    public abstract PlayerCharacter apply(PlayerCharacter character);
    public abstract String describe();
    
    @Override
    public final String toString() {
        return ObjectExtensions.toStringFor(this);
    }
}
