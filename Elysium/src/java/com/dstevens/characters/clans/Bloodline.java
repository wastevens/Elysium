package com.dstevens.characters.clans;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;

public enum Bloodline implements ApplicableTrait {

	NONE,
	
    VIZIER,
	SORCERER,
	
    TRUE_BRUJAH,
    
    TLACLQUE, 
    VIPER,
    
    COYOTE, 
    NOIAD, 
    AHRIMANE,
    
    PREMASCINE,
    
    KISAYD,
    
    ANANKE,
    KNIGHT_OF_THE_MOON,
    
    ISHTARRI,
    VOLGIRRE,

    TELYAV,

    CARPATHIAN,
    KOLDUN,

    CRUSADER,

    ANGELLIS_ATER,

    SAMEDI,
    LAMIA,
    
    BRAHMAN,                  
    
    HEALER;                  
    
	@Override
	public PlayerCharacter applyTo(PlayerCharacter character) {
		return character.withBloodline(this);
	}

	@Override
	public PlayerCharacter removeFrom(PlayerCharacter character) {
		return character.withBloodline(null);
	}
    
}
