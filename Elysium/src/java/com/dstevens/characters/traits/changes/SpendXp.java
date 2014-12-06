package com.dstevens.characters.traits.changes;

import com.dstevens.characters.PlayerCharacter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SpendXP")
public class SpendXp extends SetTrait {

    @Column(name="rating")
    private int xp;

    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SpendXp() {
        this(null, 0);
    }
    
    public SpendXp(TraitChangeStatus status, int xp) {
        super(status);
        this.xp = xp;
    }
    
    @Override
    public final PlayerCharacter apply(PlayerCharacter character) {
        return character.setXp(character.getXp() - xp);
    }
    
    @Override
    public final PlayerCharacter remove(PlayerCharacter character) {
    	return character.setXp(character.getXp() + xp);
    }

    @Override
    public String describe() {
        String nextTrait = (hasAssociatedTrait() ? String.format (" for %1$s", associatedTrait().describe()) : "");
        return String.format("(%1$s) Spend %2$s xp%3$s", status(), xp, nextTrait);    }
}
