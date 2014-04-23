package com.dstevens.characters.traits;

import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;

@Entity
@DiscriminatorValue("XP")
public class XpChangeEvent extends TraitChangeEvent {

    @Column(name="rating")
    private int xp;

    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private XpChangeEvent() {
        this(null, null, null, 0);
    }
    
    public XpChangeEvent(String id, String characterId, TraitChangeStatus status, int xp) {
        super(id, characterId, status);
        this.xp = xp;
    }
    
    @Override
    public PlayerCharacter apply(PlayerCharacter character) {
        return character.setXp(character.getXp() - xp);
    }
}
