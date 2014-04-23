package com.dstevens.characters.traits;

import static com.dstevens.collections.Sets.set;
import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;

@Entity
@DiscriminatorValue("XP")
public class XpChangeEvent extends TraitChangeEvent {

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
        super(id, characterId, status, 0, xp, null, set());
        this.purchasedChange = purchasedChange;
    }
    
    @Override
    public PlayerCharacter apply(PlayerCharacter character) {
        return purchasedChange.approve(character.setXp(character.getXp() - getXp()));
    }
    
    private int getXp() {
        return getRating();
    }

}
