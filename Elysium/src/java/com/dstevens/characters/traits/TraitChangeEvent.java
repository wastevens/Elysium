package com.dstevens.characters.traits;

import java.util.Set;
import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.utilities.ObjectExtensions;

@Entity
@Inheritance
@DiscriminatorColumn(name="trait_change_type")
@Table(name="TraitChanges")
public abstract class TraitChangeEvent {

    @Id
    private final String id;
    
    @Column(name="character_id")
    private final String characterId;
    
    @Column(name="status")
    private TraitChangeStatus status;
    
    @Column(name="ordinal")
    private final int ordinal;
    
    @Column(name="rating")
    private final int rating;
    
    @Column(name="specialization")
    private final String specialization;
    
    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name="TraitChangeFocuses")
    @Column(name="focus")
    private final Set<String> focuses;
    
    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private TraitChangeEvent() {
        this(null, null, null, 0, 0, null, null);
    }
    
    protected TraitChangeEvent(String id, String characterId, TraitChangeStatus status, int ordinal, int rating, String specialization, Set<String> focuses) {
        this.id = id;
        this.characterId = characterId;
        this.status = status;
        this.ordinal = ordinal;
        this.rating = rating;
        this.specialization = specialization;
        this.focuses = focuses;
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
    
    public final boolean isPending() {
        return this.status.equals(TraitChangeStatus.PENDING);
    }
    
    public abstract PlayerCharacter apply(PlayerCharacter character);
    
    @Override
    public final String toString() {
        return ObjectExtensions.toStringFor(this);
    }

    protected final int getOrdinal() {
        return ordinal;
    }

    protected final int getRating() {
        return rating;
    }

    protected final String getSpecialization() {
        return specialization;
    }

    protected final Set<String> getFocuses() {
        return focuses;
    }
}
