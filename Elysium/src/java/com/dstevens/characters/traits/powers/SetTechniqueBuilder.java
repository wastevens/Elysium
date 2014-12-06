package com.dstevens.characters.traits.powers;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ChangeExperience;
import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.TraitChangeBuilder;
import com.dstevens.characters.traits.TraitChangeStatus;

public class SetTechniqueBuilder implements TraitChangeBuilder {

	private final PlayerCharacter character;
    private final Technique technique;

    public SetTechniqueBuilder(PlayerCharacter character, Technique technique) {
        this.character = character;
		this.technique = technique;
    }
    
    @Override
    public SetTrait buy() {
    	if(character.getGeneration().orElse(1) >= 3) {
    		return ChangeExperience.spend(20).and(setTechnique());
    	} else {
    		return ChangeExperience.spend(12).and(setTechnique());
    	}
    }

    @Override
    public SetTrait add() {
        return setTechnique();
    }

    private SetTrait setTechnique() {
        return new SetTechnique(TraitChangeStatus.PENDING, technique);
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
