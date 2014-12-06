package com.dstevens.characters.traits;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("ChangeExperience")
public class ChangeXp extends SetApplicableTrait<Experience> {

	@OneToOne(cascade={CascadeType.ALL}, optional=true)
	@JoinColumn(name="trait_id", referencedColumnName="id", foreignKey=@ForeignKey(name="none"))
    private final Experience trait;

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private ChangeXp() {
        this(null, null);
    }
    
    public ChangeXp(TraitChangeStatus status, Experience trait) {
    	super(status);
		this.trait = trait;
    }

	@Override
	protected Experience trait() {
		return trait;
	}
	
	public static ChangeXp spend(int xp) {
		return new ChangeXp(TraitChangeStatus.PENDING, new SpendExperience(xp));
	}
	
	public static ChangeXp gain(int xp) {
		return new ChangeXp(TraitChangeStatus.PENDING, new GainExperience(xp));
	}
}
