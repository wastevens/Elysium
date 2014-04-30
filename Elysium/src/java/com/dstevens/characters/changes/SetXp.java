package com.dstevens.characters.changes;

import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;

@Entity
@DiscriminatorValue("XP")
class SetXp extends SetTrait {

    @Column(name="rating")
    private int xp;

    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetXp() {
        this(null, null, 0);
    }
    
    protected SetXp(String id, TraitChangeStatus status, int xp) {
        super(id, status, null);
        this.xp = xp;
    }
    
    protected SetXp(String id, TraitChangeStatus status, int xp, SetTrait associatedTrait) {
        super(id, status, associatedTrait);
        this.xp = xp;
    }
    
    @Override
    public final PlayerCharacter apply(PlayerCharacter character) {
        return character.setXp(character.getXp() - xp);
    }

    @Override
    public String describe() {
        String format = String.format("(%1$s) %2$s %3$s xp", status(), (xp > 0 ? "Spend" : "Gain"), xp);
        if (hasAssociatedTrait()) {
            format += String.format(" for (%1$s)", associatedTrait().describe());
        }
        return format;
    }
}
