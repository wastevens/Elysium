package com.dstevens.characters.traits.backgrounds;

import com.dstevens.characters.traits.SetApplicableTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("Background")
class SetBackground extends SetApplicableTrait<CharacterBackground> {

	@OneToOne(cascade={CascadeType.ALL}, optional=true)
	@JoinColumn(name="applicable_trait_id", referencedColumnName="id", foreignKey=@ForeignKey(name="none"))
	private final CharacterBackground trait;
	
	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetBackground() {
        this(null, null);
    }
    
    public SetBackground(TraitChangeStatus status, CharacterBackground trait) {
    	super(status);
		this.trait = trait;
    }
    
    @Override
    protected CharacterBackground trait() {
    	return trait;
    }
}
