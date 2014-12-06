package com.dstevens.characters.traits;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("ChangeExperience")
public class ChangeExperience extends SetApplicableTrait<Experience> {

	@OneToOne(cascade={CascadeType.ALL}, optional=true)
	@JoinColumn(name="trait_id", referencedColumnName="id", foreignKey=@ForeignKey(name="none"))
    private final Experience trait;

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private ChangeExperience() {
        this(null, null);
    }
    
    public ChangeExperience(TraitChangeStatus status, Experience trait) {
    	super(status);
		this.trait = trait;
    }

	@Override
	protected Experience trait() {
		return trait;
	}
	
	public static ChangeExperience spend(int xp) {
		return new ChangeExperience(TraitChangeStatus.PENDING, new SpendExperience(xp));
	}
	
	public static ChangeExperience gain(int xp) {
		return new ChangeExperience(TraitChangeStatus.PENDING, new GainExperience(xp));
	}
}
