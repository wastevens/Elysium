package com.dstevens.characters.traits.status;

import com.dstevens.characters.traits.SetApplicableTrait;
import com.dstevens.characters.traits.TraitChangeStatus;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("Status")
public class SetStatus extends SetApplicableTrait<ApplicableStatus> {

	@OneToOne(cascade={CascadeType.ALL}, optional=true)
	@JoinColumn(name="applicable_trait_id", referencedColumnName="id", foreignKey=@ForeignKey(name="none"))
    private ApplicableStatus trait;
	
	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetStatus() {
        this(null, null);
    }
    
    public SetStatus(TraitChangeStatus status, ApplicableStatus trait) {
    	super(status);
    	this.trait = trait;
    }
	
	@Override
	protected ApplicableStatus trait() {
		return trait;
	}

}
