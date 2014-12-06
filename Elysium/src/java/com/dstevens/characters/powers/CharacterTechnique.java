package com.dstevens.characters.powers;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.EnumeratedTrait;
import com.dstevens.suppliers.IdSupplier;
import com.dstevens.utilities.ObjectExtensions;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Techniques")
public class CharacterTechnique implements Comparable<CharacterTechnique>, EnumeratedTrait<Technique> {

	@Id
    private final String id;
	
    @Basic(optional=false)
    private final Technique trait;
	
  //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private CharacterTechnique() {
        this(null);
    }
    
    public CharacterTechnique(Technique trait) {
    	this.id = new IdSupplier().get();
        this.trait = trait;
    }
    
	@Override
	public int ordinal() {
		return trait.ordinal();
	}

	@Override
	public Technique trait() {
		return trait;
	}

	@Override
	public PlayerCharacter applyTo(PlayerCharacter character) {
		return character.withTechnique(this);
	}

	@Override
	public PlayerCharacter removeFrom(PlayerCharacter character) {
		return character.withoutTechnique(this);
	}

    @Override
    public boolean equals(Object that) {
        return characterTraitEquals(that);
    }
    
    @Override
    public int hashCode() {
        return characterTraitHashcode();
    }
    
    @Override
    public int compareTo(CharacterTechnique that) {
    	return enumeratedTraitComparator().compare(this, that);
    }
    
    @Override
    public String toString() {
        return ObjectExtensions.toStringFor(this);
    }
}
