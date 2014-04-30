package com.dstevens.characters.changes;

import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;

@Entity
@DiscriminatorValue("GainXP")
class GainXp extends SetTrait {

    @Column(name="rating")
    private int xp;

    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private GainXp() {
        this(null, 0);
    }
    
    protected GainXp(TraitChangeStatus status, int xp) {
        super(status, null);
        this.xp = xp;
    }
    
    protected GainXp(TraitChangeStatus status, int xp, SetTrait associatedTrait) {
        super(status, associatedTrait);
        this.xp = xp;
    }
    
    @Override
    public final PlayerCharacter apply(PlayerCharacter character) {
        return character.setXp(character.getXp() + xp);
    }

    @Override
    public String describe() {
        String nextTrait = (hasAssociatedTrait() ? String.format (" for %1$s", associatedTrait().describe()) : "");
        return String.format("(%1$s) Gain %2$s xp%3$s", status(), xp, nextTrait);    }
}
