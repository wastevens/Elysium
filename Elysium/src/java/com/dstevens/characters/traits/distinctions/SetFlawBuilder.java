package com.dstevens.characters.traits.distinctions;

import com.dstevens.characters.traits.ChangeExperience;
import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeBuilder;
import com.dstevens.characters.traits.TraitChangeStatus;

public class SetFlawBuilder implements TraitChangeBuilder {

    private Flaw flaw;
    private String specialization;
    private SetTrait associatedChange;

    public SetFlawBuilder(Flaw flaw) {
    	this(flaw, null, null);
    }
    
    public SetFlawBuilder(Flaw flaw, String specialization) {
    	this(flaw, specialization, null);
    }
    
    public SetFlawBuilder(Flaw flaw, String specialization, SetTrait associatedChange) {
    	this.flaw = flaw;
		this.specialization = specialization;
		this.associatedChange = associatedChange;
    }
    
    @Override
    public SetTrait buy() {
        return ChangeExperience.gain(flaw.getPoints()).and(setFlaw());
    }

    @Override
    public SetTrait add() {
        return setFlaw();
    }

    private SetTrait setFlaw() {
    	return new SetFlaw(TraitChangeStatus.PENDING, new CharacterFlaw(flaw, specialization)).and(associatedChange);
    }

	@Override
	public SetTrait sell() {
		throw new IllegalStateException("not yet implemented");
	}

	@Override
	public SetTrait remove() {
		throw new IllegalStateException("not yet implemented");
	}
}
