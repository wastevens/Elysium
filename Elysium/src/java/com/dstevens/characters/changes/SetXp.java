package com.dstevens.characters.changes;

import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;

@Entity
@DiscriminatorValue("XP")
public class SetXp extends TraitChange {

    @Column(name="rating")
    private int xp;

    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetXp() {
        this(null, null, 0);
    }
    
    public SetXp(String id, TraitChangeStatus status, int xp) {
        super(id, status);
        this.xp = xp;
    }
    
    @Override
    public PlayerCharacter apply(PlayerCharacter character, TraitChangeFactory traitChangeFactory) {
        return character.setXp(character.getXp() - xp);
    }
}
