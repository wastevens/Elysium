package com.dstevens.characters.traits.powers;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.changes.SetTrait;
import com.dstevens.characters.traits.changes.SpendXp;
import com.dstevens.characters.traits.changes.TraitChangeStatus;
import com.dstevens.game.TraitChangeBuilder;

public class TechniqueChangeBuilder implements TraitChangeBuilder {

	private final PlayerCharacter character;
    private final Technique technique;

    public TechniqueChangeBuilder(PlayerCharacter character, Technique technique) {
        this.character = character;
		this.technique = technique;
    }
    
    @Override
    public SetTrait buy() {
    	if(character.getGeneration().orElse(1) >= 3) {
    		return new SpendXp(TraitChangeStatus.PENDING, 20).and(setTechnique());
    	} else {
    		return new SpendXp(TraitChangeStatus.PENDING, 12).and(setTechnique());
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
