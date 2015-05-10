package com.dstevens.characters.clans;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.Trait;
import com.dstevens.characters.traits.TraitQualities;

public enum Bloodline implements ApplicableTrait, Trait {

	ASSAMITE(0),
    VIZIER(1),
	SORCERER(2),
	
	BRUJAH(3),
    TRUE_BRUJAH(4),
    
    FOLLOWER_OF_SET(5),
    TLACLQUE(6), 
    VIPER(7),
    
    GANGREL(8),
    COYOTE(9), 
    NOIAD(10), 
    AHRIMANE(11),
    
    GIOVANNI(12),
    PREMASCINE(13),
    
    LASOMBRA(14),
    KISAYD(15),
    
    MALKAVIAN(16),
    ANANKE(17),
    KNIGHT_OF_THE_MOON(18),
    
    NOSFERATU(19),
    
    TOREADOR(20),
    ISHTARRI(21),
    VOLGIRRE(22),

    TREMERE(23),
    TELYAV(24),

    TZIMISCE(25),
    CARPATHIAN(26),
    KOLDUN(27),

    VENTRUE(28),
    CRUSADER(29),

    CATIFF(30),
    
    BAALI(31),
    ANGELLIS_ATER(32),

    CAPPADOCIAN(33),
    SAMEDI(34),
    LAMIA(35),
    
    RAVNOS(36),
    BRAHMAN(37),                  
    
    SALUBRI(38),
    HEALER(39),
    
    DAUGHTER_OF_CACOPHONY(40),
    
    GARGOYLE(41);                  
    
	private final int id;

	private Bloodline(int id) {
		this.id = id;
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public PlayerCharacter applyTo(PlayerCharacter character) {
		return character.withBloodline(this);
	}

	@Override
	public PlayerCharacter removeFrom(PlayerCharacter character) {
		return character.withBloodline(null);
	}

	@Override
	public ApplicableTrait applyWith(TraitQualities qualities) {
		return this;
	}
    
}
