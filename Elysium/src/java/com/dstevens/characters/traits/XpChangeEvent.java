package com.dstevens.characters.traits;

import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;

@Entity
@DiscriminatorValue("XP")
public class XpChangeEvent extends TraitChangeEvent {

    @Column(name="rating")
    private final int xp;
    
    @OneToOne
    @JoinColumn(name="purchased_change_id")
    private TraitChangeEvent purchasedChange;
    
    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private XpChangeEvent() {
        this(null, null, null, 0, null);
    }
    
    public XpChangeEvent(String id, String characterId, TraitChangeStatus status, int xp, TraitChangeEvent purchasedChange) {
        super(id, characterId, status);
        this.xp = xp;
        this.purchasedChange = purchasedChange;
    }
    
    @Override
    public PlayerCharacter apply(PlayerCharacter character) {
        character.setXp(character.getXp() - xp);
        return purchasedChange.approve(character.setXp(character.getXp() - xp));
    }

}
