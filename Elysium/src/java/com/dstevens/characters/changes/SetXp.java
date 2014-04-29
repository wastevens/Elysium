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
        super(id, status);
        this.xp = xp;
    }
    
    @Override
    public final PlayerCharacter apply(PlayerCharacter character) {
        return character.setXp(character.getXp() - xp);
    }

    @Override
    public String describe() {
        return String.format("(%1$s) Spend %1$s xp", status(), xp);
    }
}
