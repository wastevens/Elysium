package com.dstevens.characters.powers.magics;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.powers.Power;

public enum Thaumaturgy implements Power<Thaumaturgy> {

    PATH_OF_BLOOD,
    PATH_OF_CONJURING,
    PATH_OF_CORRUPTION,
    PATH_OF_ELEMENTAL_MASTERY,
    LURE_OF_FLAMES,
    MOVEMENT_OF_THE_MIND,
    PATH_OF_TECHNOMANCY,
    PATH_OF_WEATHER_MASTERY;
    
	@Override
	public Thaumaturgy trait() {
		return this;
	}

	@Override
	public PlayerCharacter applyTo(PlayerCharacter character) {
		return character.withInClanDisciplines(this);
	}
    
}
