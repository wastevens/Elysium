package com.dstevens.characters.traits;

import com.dstevens.characters.PlayerCharacter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("GainXP")
public class GainXp extends SetTrait {

    @Column(name="rating")
    private int xp;

    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private GainXp() {
        this(null, 0);
    }
    
    public GainXp(TraitChangeStatus status, int xp) {
        super(status);
        this.xp = xp;
    }
    
    @Override
    public final PlayerCharacter apply(PlayerCharacter character) {
        return character.setXp(character.getXp() + xp);
    }
    
    @Override
    public PlayerCharacter remove(PlayerCharacter character) {
    	return character.setXp(character.getXp() - xp);
    }

    @Override
    public String describe() {
        String nextTrait = (hasAssociatedTrait() ? String.format (" for %1$s", associatedTrait().describe()) : "");
        return String.format("(%1$s) Gain %2$s xp%3$s", status(), xp, nextTrait);    }
}
