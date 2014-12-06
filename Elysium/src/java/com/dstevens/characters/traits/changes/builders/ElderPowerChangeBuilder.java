package com.dstevens.characters.traits.changes.builders;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.changes.SetElderPower;
import com.dstevens.characters.traits.changes.SetTrait;
import com.dstevens.characters.traits.changes.SpendXp;
import com.dstevens.characters.traits.changes.TraitChangeStatus;
import com.dstevens.characters.traits.powers.ElderPower;
import com.dstevens.game.TraitChangeBuilder;

public class ElderPowerChangeBuilder implements TraitChangeBuilder {

	private final PlayerCharacter character;
    private final ElderPower elderPower;

    public ElderPowerChangeBuilder(PlayerCharacter character, ElderPower elderPower) {
        this.character = character;
		this.elderPower = elderPower;
    }
    
    @Override
    public SetTrait buy() {
    	if(character.getInClanDisciplines().contains(elderPower.getPower())) {
    		return new SpendXp(TraitChangeStatus.PENDING, 18).and(setElderPower());
    	} else {
    		if(character.getGeneration().orElse(1) == 5) {
    			return new SpendXp(TraitChangeStatus.PENDING, 30).and(setElderPower());
    		} else {
    			return new SpendXp(TraitChangeStatus.PENDING, 24).and(setElderPower());
    		}
    	}
    }

    @Override
    public SetTrait add() {
        return setElderPower();
    }

    private SetTrait setElderPower() {
    	return new SetElderPower(TraitChangeStatus.PENDING, elderPower);
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
