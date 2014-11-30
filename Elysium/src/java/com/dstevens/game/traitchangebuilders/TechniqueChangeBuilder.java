package com.dstevens.game.traitchangebuilders;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.changes.SetEnumeratedTrait;
import com.dstevens.characters.changes.SetTrait;
import com.dstevens.characters.changes.SpendXp;
import com.dstevens.characters.changes.TraitChangeStatus;
import com.dstevens.characters.changes.TraitFactory;
import com.dstevens.characters.powers.Technique;
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
    		return new SpendXp(TraitChangeStatus.PENDING, 20, setTechnique());
    	} else {
    		return new SpendXp(TraitChangeStatus.PENDING, 12, setTechnique());
    	}
    }

    @Override
    public SetTrait add() {
        return setTechnique();
    }

    private SetEnumeratedTrait setTechnique() {
        return new SetEnumeratedTrait(TraitChangeStatus.PENDING, technique, TraitFactory.TECHNIQUE);
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
