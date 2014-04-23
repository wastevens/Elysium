package com.dstevens.characters.changes;

import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.utilities.ObjectExtensions;

@Entity
@Inheritance
@DiscriminatorColumn(name="trait_change_type")
@Table(name="TraitChanges")
public abstract class TraitChange {

    @Id
    private final String id;
    
    @Column(name="status")
    private TraitChangeStatus status;
    
    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private TraitChange() {
        this(null, null);
    }
    
    protected TraitChange(String id, TraitChangeStatus status) {
        this.id = id;
        this.status = status;
    }
    
    public final PlayerCharacter approve(PlayerCharacter character, TraitChangeFactory traitChangeFactory) {
        return status.apply(character, this, traitChangeFactory);
    }
    
    public final PlayerCharacter deny(PlayerCharacter character) {
        return status.deny(character, this);
    }

    public final void setStatus(TraitChangeStatus status) {
        this.status = status;
    }
    
    public final boolean isPending() {
        return this.status.equals(TraitChangeStatus.PENDING);
    }
    
    public abstract PlayerCharacter apply(PlayerCharacter character, TraitChangeFactory traitChangeFactory);
    
    @Override
    public final String toString() {
        return ObjectExtensions.toStringFor(this);
    }
}
