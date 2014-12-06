package com.dstevens.characters.traits.powers;

import com.dstevens.characters.traits.SetApplicableTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("Discipline")
public class SetDiscipline extends SetApplicableTrait<CharacterDiscipline> {

	@OneToOne(cascade={CascadeType.ALL}, optional=true)
	@JoinColumn(name="trait_id", referencedColumnName="id", foreignKey=@ForeignKey(name="none"))
    private CharacterDiscipline trait;

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetDiscipline() {
        this(null, null);
    }
    
    public SetDiscipline(TraitChangeStatus status, CharacterDiscipline trait) {
    	super(status);
		this.trait = trait;
    }
    
    @Override
    protected CharacterDiscipline trait() {
    	return trait;
    }

}
