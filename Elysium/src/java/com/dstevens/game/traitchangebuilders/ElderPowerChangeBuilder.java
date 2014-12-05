package com.dstevens.game.traitchangebuilders;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.changes.SetElderPower;
import com.dstevens.characters.changes.SetTrait;
import com.dstevens.characters.changes.SpendXp;
import com.dstevens.characters.changes.TraitChangeStatus;
import com.dstevens.characters.powers.CharacterElderPower;
import com.dstevens.characters.powers.ElderPower;
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
    	return new SetElderPower(TraitChangeStatus.PENDING, new CharacterElderPower(elderPower));
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
