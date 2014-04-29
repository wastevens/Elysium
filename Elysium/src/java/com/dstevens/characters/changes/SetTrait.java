package com.dstevens.characters.changes;

import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.utilities.ObjectExtensions;

@Entity
@Inheritance
@DiscriminatorColumn(name="trait_change_type")
@Table(name="TraitChanges")
public abstract class SetTrait {

    @Id
    private final String id;
    
    @Column(name="status")
    private TraitChangeStatus status;
    
    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private SetTrait() {
        this(null, null);
    }
    
    protected SetTrait(String id, TraitChangeStatus status) {
        this.id = id;
        this.status = status;
    }
    
    public final PlayerCharacter approve(PlayerCharacter character) {
        return status.apply(character, this);
    }
    
    public final PlayerCharacter deny(PlayerCharacter character) {
        return status.deny(character, this);
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
