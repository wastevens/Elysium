package com.dstevens.character.clan;

import java.util.List;

import com.dstevens.character.PlayerCharacter;
import com.dstevens.character.trait.ApplicableTrait;
import com.dstevens.character.trait.Trait;
import com.dstevens.character.trait.TraitQualities;
import com.dstevens.character.trait.power.discipline.Discipline;

import static com.dstevens.collections.Lists.list;

public enum Bloodline implements ApplicableTrait, Trait {

	ASSAMITE(0, list(Discipline.CELERITY), list(Discipline.OBFUSCATE), list(Discipline.QUIETUS)),
    VIZIER(1, list(Discipline.AUSPEX), list(Discipline.CELERITY), list(Discipline.QUIETUS)),
	SORCERER(2, list(Discipline.OBFUSCATE), list(Discipline.QUIETUS), list(Discipline.LURE_OF_FLAMES),
                list(Discipline.PATH_OF_BLOOD,            
	                 Discipline.PATH_OF_CONJURING,        
	                 Discipline.PATH_OF_CORRUPTION,       
	                 Discipline.PATH_OF_ELEMENTAL_MASTERY,
	                 Discipline.MOVEMENT_OF_THE_MIND,     
	                 Discipline.PATH_OF_TECHNOMANCY,      
	                 Discipline.PATH_OF_WEATHER_MASTERY)),
	
	BRUJAH(3, list(Discipline.CELERITY), list(Discipline.POTENCE), list(Discipline.PRESENCE)),
    TRUE_BRUJAH(4, list(Discipline.POTENCE), list(Discipline.PRESENCE), list(Discipline.TEMPORSIS)),
    
    FOLLOWER_OF_SET(5, list(Discipline.OBFUSCATE), list(Discipline.PRESENCE), list(Discipline.SERPENTIS)),
    TLACLQUE(6, list(Discipline.OBFUSCATE), list(Discipline.PRESENCE), list(Discipline.PROTEAN)), 
    VIPER(7, list(Discipline.POTENCE), list(Discipline.PRESENCE), list(Discipline.SERPENTIS)),
    
    GANGREL(8, list(Discipline.ANIMALISM), list(Discipline.FORTITUDE), list(Discipline.PROTEAN)),
    COYOTE(9, list(Discipline.CELERITY), list(Discipline.OBFUSCATE), list(Discipline.PROTEAN)), 
    NOIAD(10, list(Discipline.ANIMALISM), list(Discipline.AUSPEX), list(Discipline.PROTEAN)), 
    AHRIMANE(11, list(Discipline.ANIMALISM), list(Discipline.PRESENCE), list(Discipline.PATH_OF_ELEMENTAL_MASTERY)),
    
    GIOVANNI(12, list(Discipline.DOMINATE), list(Discipline.POTENCE), list(Discipline.SEPULCHRE_PATH)),
    PREMASCINE(13, list(Discipline.DOMINATE), list(Discipline.POTENCE), list(Discipline.SEPULCHRE_PATH)),
    
    LASOMBRA(14, list(Discipline.DOMINATE), list(Discipline.POTENCE), list(Discipline.OBTENEBRATION)),
    KISAYD(15, list(Discipline.DOMINATE), list(Discipline.MYTHERCERIA), list(Discipline.OBTENEBRATION)),
    
    MALKAVIAN(16, list(Discipline.AUSPEX), list(Discipline.DEMENTATION), list(Discipline.OBFUSCATE)),
    ANANKE(17, list(Discipline.AUSPEX), list(Discipline.DEMENTATION), list(Discipline.PRESENCE)),
    KNIGHT_OF_THE_MOON(18, list(Discipline.AUSPEX), list(Discipline.DOMINATE), list(Discipline.OBFUSCATE)),
    
    NOSFERATU(19, list(Discipline.ANIMALISM), list(Discipline.OBFUSCATE), list(Discipline.POTENCE)),
    
    TOREADOR(20, list(Discipline.AUSPEX), list(Discipline.CELERITY), list(Discipline.PRESENCE)),
    ISHTARRI(21, list(Discipline.CELERITY), list(Discipline.FORTITUDE), list(Discipline.PRESENCE)),
    VOLGIRRE(22, list(Discipline.AUSPEX), list(Discipline.CELERITY), list(Discipline.PRESENCE)),

    TREMERE(23,list(Discipline.AUSPEX), list(Discipline.DOMINATE), list(Discipline.PATH_OF_BLOOD)),
    TELYAV(24, list(Discipline.AUSPEX), list(Discipline.PRESENCE), list(Discipline.PATH_OF_BLOOD)),

    TZIMISCE(25, list(Discipline.ANIMALISM), list(Discipline.AUSPEX), list(Discipline.VICISSITUDE)),
    CARPATHIAN(26, list(Discipline.ANIMALISM), list(Discipline.AUSPEX), list(Discipline.DOMINATE)),
    KOLDUN(27, list(Discipline.ANIMALISM), list(Discipline.AUSPEX), list(Discipline.PATH_OF_ELEMENTAL_MASTERY),
	           list(Discipline.PATH_OF_BLOOD,            
                    Discipline.PATH_OF_CONJURING,        
                    Discipline.PATH_OF_CORRUPTION,       
                    Discipline.LURE_OF_FLAMES,
                    Discipline.MOVEMENT_OF_THE_MIND,     
                    Discipline.PATH_OF_TECHNOMANCY,      
                    Discipline.PATH_OF_WEATHER_MASTERY)),

    VENTRUE(28,  list(Discipline.DOMINATE), list(Discipline.FORTITUDE), list(Discipline.PRESENCE)),
    CRUSADER(29, list(Discipline.AUSPEX), list(Discipline.DOMINATE), list(Discipline.FORTITUDE)),

    CATIFF(30, list(Discipline.ANIMALISM, Discipline.AUSPEX, Discipline.CELERITY, Discipline.DOMINATE, Discipline.FORTITUDE, Discipline.OBFUSCATE, Discipline.POTENCE, Discipline.PRESENCE),
    		   list(Discipline.ANIMALISM, Discipline.AUSPEX, Discipline.CELERITY, Discipline.DOMINATE, Discipline.FORTITUDE, Discipline.OBFUSCATE, Discipline.POTENCE, Discipline.PRESENCE),
    		   list(Discipline.ANIMALISM, Discipline.AUSPEX, Discipline.CELERITY, Discipline.DOMINATE, Discipline.FORTITUDE, Discipline.OBFUSCATE, Discipline.POTENCE, Discipline.PRESENCE)),
    
    BAALI(31, list(Discipline.DAIMOINON), list(Discipline.OBFUSCATE), list(Discipline.PRESENCE)),
    ANGELLIS_ATER(32, list(Discipline.DAIMOINON), list(Discipline.DOMINATE), list(Discipline.POTENCE, Discipline.PRESENCE, Discipline.OBFUSCATE)),

    CAPPADOCIAN(33, list(Discipline.AUSPEX), list(Discipline.FORTITUDE), list(Discipline.MORTIS_PATH)),
    SAMEDI(34, list(Discipline.FORTITUDE), list(Discipline.OBFUSCATE), list(Discipline.THANATOSIS)),
    LAMIA(35, list(Discipline.FORTITUDE), list(Discipline.POTENCE), list(Discipline.MORTIS_PATH)),
    
    RAVNOS(36, list(Discipline.ANIMALISM), list(Discipline.CHIMERSTRY), list(Discipline.FORTITUDE)),
    BRAHMAN(37, list(Discipline.ANIMALISM), list(Discipline.AUSPEX), list(Discipline.FORTITUDE)),                  
    
    SALUBRI(38,list(Discipline.AUSPEX), list(Discipline.FORTITUDE), list(Discipline.VALEREN)),
    HEALER(39, list(Discipline.AUSPEX), list(Discipline.FORTITUDE), list(Discipline.OBEAH)),
    
    DAUGHTER_OF_CACOPHONY(40, list(Discipline.FORTITUDE), list(Discipline.MELPOMINEE), list(Discipline.PRESENCE)),
    
    GARGOYLE(41, list(Discipline.FORTITUDE), list(Discipline.POTENCE), list(Discipline.VISCERATIKA));                  
    
	private final int id;
	private List<List<Discipline>> disciplines;

	@SafeVarargs
	private Bloodline(int id, List<Discipline>... disciplines) {
		this.id = id;
		this.disciplines = list(disciplines);
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	
	public List<List<Discipline>> getDisciplines() {
		return disciplines;
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
