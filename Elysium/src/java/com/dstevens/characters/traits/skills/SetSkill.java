package com.dstevens.characters.traits.skills;

import com.dstevens.characters.traits.SetApplicableTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("Skill")
class SetSkill extends SetApplicableTrait<CharacterSkill> {

	@OneToOne(cascade={CascadeType.ALL}, optional=true)
	@JoinColumn(name="trait_id", referencedColumnName="id", foreignKey=@ForeignKey(name="none"))
    private CharacterSkill trait;

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetSkill() {
        this(null, null);
    }
    
    public SetSkill(TraitChangeStatus status, CharacterSkill trait) {
    	super(status);
		this.trait = trait;
    }
    
    @Override
    protected CharacterSkill trait() {
    	return trait;
    }
}
