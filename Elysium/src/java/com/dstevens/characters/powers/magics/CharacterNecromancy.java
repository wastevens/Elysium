package com.dstevens.characters.powers.magics;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.RatedTrait;
import com.dstevens.suppliers.IdSupplier;
import com.dstevens.utilities.ObjectExtensions;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="NecromanticPaths")
public class CharacterNecromancy implements Comparable<CharacterNecromancy>, RatedTrait<Necromancy> {

	@Id
    private final String id;
	
    @Basic(optional=false)
    private final Necromancy power;
    
    @Column(name="rating")
    private int rating;
    
    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private CharacterNecromancy() {
        this(null, 0);
    }
    
    public CharacterNecromancy(Necromancy trait, int rating) {
    	this.id = new IdSupplier().get();
        this.power = trait;
        this.rating = rating;
    }
    
    @Override
    public Necromancy trait() {
        return power;
    }
    
    @Override
    public final int ordinal() {
        return power.ordinal();
    }
    
    @Override
    public final int rating() {
        return rating;
    }
    
    public final CharacterNecromancy withRating(int rating) {
        return new CharacterNecromancy(power, rating);
    }
    
    @Override
    public boolean equals(Object that) {
        return ratedTraitEquals(that);
    }
    
    @Override
    public int hashCode() {
        return ratedTraitHashcode();
    }
    
    @Override
    public String toString() {
        return ObjectExtensions.toStringFor(this);
    }

    @Override
    public int compareTo(CharacterNecromancy that) {
        return ratedTraitComparator().compare(this, that);
    }

    @Override
    public PlayerCharacter applyTo(PlayerCharacter character) {
        return character.withNecromanticPath(this);
    }
    
    @Override
    public PlayerCharacter removeFrom(PlayerCharacter character) {
    	return character.withoutNecromanticPath(this);
    }
}
