package com.dstevens.characters.clans;

import static com.dstevens.collections.Sets.set;

import java.util.Set;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.powers.Power;
import com.dstevens.characters.traits.powers.disciplines.Discipline;
import com.dstevens.characters.traits.powers.magic.necromancy.Necromancy;
import com.dstevens.characters.traits.powers.magic.thaumaturgy.Thaumaturgy;

public enum Bloodline implements ApplicableTrait {

    ASSAMITE(set(Discipline.CELERITY, Discipline.OBFUSCATE, Discipline.QUIETUS)),
    VIZIER(set(Discipline.AUSPEX, Discipline.CELERITY, Discipline.QUIETUS)),
    @SuppressWarnings("unchecked")
	SORCERER(set(Discipline.OBFUSCATE, Discipline.QUIETUS, Thaumaturgy.LURE_OF_FLAMES), 
             set(Thaumaturgy.MOVEMENT_OF_THE_MIND, Thaumaturgy.PATH_OF_BLOOD, Thaumaturgy.PATH_OF_CONJURING, Thaumaturgy.PATH_OF_CORRUPTION, 
                         Thaumaturgy.PATH_OF_ELEMENTAL_MASTERY, Thaumaturgy.PATH_OF_TECHNOMANCY, Thaumaturgy.PATH_OF_WEATHER_MASTERY)),
    BRUJAH(set(Discipline.CELERITY, Discipline.POTENCE, Discipline.PRESENCE)), 
    TRUE_BRUJAH(set(Discipline.TEMPORSIS, Discipline.POTENCE, Discipline.PRESENCE)), 
    FOLLOWER_OF_SET(set(Discipline.OBFUSCATE, Discipline.PRESENCE, Discipline.SERPENTIS)), 
    TLACLQUE(set(Discipline.PRESENCE, Discipline.OBFUSCATE, Discipline.PROTEAN)), 
    VIPER(set(Discipline.POTENCE, Discipline.PRESENCE, Discipline.SERPENTIS)), 
    GANGREL(set(Discipline.ANIMALISM, Discipline.FORTITUDE, Discipline.PROTEAN)), 
    COYOTE(set(Discipline.CELERITY, Discipline.OBFUSCATE, Discipline.PROTEAN)), 
    NOIAD(set(Discipline.ANIMALISM, Discipline.AUSPEX, Discipline.PROTEAN)), 
    @SuppressWarnings("unchecked")
    AHRIMANE(set(Discipline.ANIMALISM, Discipline.PRESENCE, Thaumaturgy.PATH_OF_ELEMENTAL_MASTERY)),
    @SuppressWarnings("unchecked")
    GIOVANNI(set(Discipline.DOMINATE, Discipline.POTENCE, Necromancy.SEPULCHRE_PATH)),
    @SuppressWarnings("unchecked")
    PREMASCINE(set(Discipline.DOMINATE, Discipline.PRESENCE, Necromancy.SEPULCHRE_PATH)),
    LASOMBRA(set(Discipline.DOMINATE, Discipline.POTENCE, Discipline.OBTENEBRATION)),
    KISAYD(set(Discipline.DOMINATE, Discipline.MYTHERCERIA, Discipline.OBTENEBRATION)),
    MALKAVIAN(set(Discipline.AUSPEX, Discipline.DEMENTATION, Discipline.OBFUSCATE)),
    ANANKE(set(Discipline.AUSPEX, Discipline.DEMENTATION, Discipline.PRESENCE)),
    KNIGHT_OF_THE_MOON(set(Discipline.AUSPEX, Discipline.DOMINATE, Discipline.OBFUSCATE)),
    NOSFERATU(set(Discipline.ANIMALISM, Discipline.OBFUSCATE, Discipline.POTENCE)),
    TOREADOR(set(Discipline.AUSPEX, Discipline.CELERITY, Discipline.PRESENCE)),
    ISHTARRI(set(Discipline.CELERITY, Discipline.FORTITUDE, Discipline.PRESENCE)),
    VOLGIRRE(set(Discipline.AUSPEX, Discipline.CELERITY, Discipline.PRESENCE)),
    @SuppressWarnings("unchecked")
    TREMERE(set(Discipline.AUSPEX, Discipline.DOMINATE, Thaumaturgy.PATH_OF_BLOOD)),
    @SuppressWarnings("unchecked")
    TELYAV(set(Discipline.AUSPEX, Discipline.PRESENCE, Thaumaturgy.PATH_OF_BLOOD)),
    TZIMISCE(set(Discipline.ANIMALISM, Discipline.AUSPEX, Discipline.VICISSITUDE)),
    CARPATHIAN(set(Discipline.ANIMALISM, Discipline.AUSPEX, Discipline.DOMINATE)),
    @SuppressWarnings("unchecked")
    KOLDUN(set(Discipline.ANIMALISM, Discipline.AUSPEX, Thaumaturgy.PATH_OF_ELEMENTAL_MASTERY), 
           set(Thaumaturgy.LURE_OF_FLAMES, Thaumaturgy.MOVEMENT_OF_THE_MIND, Thaumaturgy.PATH_OF_BLOOD, Thaumaturgy.PATH_OF_CONJURING, 
                       Thaumaturgy.PATH_OF_CORRUPTION, Thaumaturgy.PATH_OF_TECHNOMANCY, Thaumaturgy.PATH_OF_WEATHER_MASTERY)),
    VENTRUE(set(Discipline.DOMINATE, Discipline.FORTITUDE, Discipline.PRESENCE)),
    CRUSADER(set(Discipline.AUSPEX, Discipline.DOMINATE, Discipline.FORTITUDE)),
    CATIFF(set(), set(Discipline.ANIMALISM, Discipline.AUSPEX, Discipline.CELERITY, Discipline.DOMINATE, 
                      Discipline.FORTITUDE, Discipline.OBFUSCATE, Discipline.PRESENCE, Discipline.POTENCE), 3),
    BAALI(set(Discipline.DAIMOINON, Discipline.OBFUSCATE, Discipline.PRESENCE)),
    ANGELLIS_ATER(set(Discipline.DAIMOINON, Discipline.DOMINATE), set(Discipline.PRESENCE, Discipline.POTENCE, Discipline.OBFUSCATE)),
    @SuppressWarnings("unchecked")
    CAPPADOCIAN(set((Power<?>)Discipline.AUSPEX, Discipline.FORTITUDE, Necromancy.MORTIS_PATH)),
    SAMEDI(set(Discipline.FORTITUDE, Discipline.OBFUSCATE, Discipline.THANATOSIS)),
    @SuppressWarnings("unchecked")
    LAMIA(set(Discipline.FORTITUDE, Discipline.PRESENCE, Necromancy.MORTIS_PATH)),
    RAVNOS(set(Discipline.ANIMALISM, Discipline.FORTITUDE, Discipline.CHIMERSTRY)),                  
    BRAHMAN(set(Discipline.ANIMALISM, Discipline.AUSPEX, Discipline.CHIMERSTRY)),                  
    SALUBRI(set(Discipline.AUSPEX, Discipline.FORTITUDE, Discipline.VALEREN)),                  
    HEALER(set(Discipline.AUSPEX, Discipline.FORTITUDE, Discipline.OBEAH)),                  
    DAUGHTER_OF_CACOPHONY(set(Discipline.FORTITUDE, Discipline.MELPOMINEE, Discipline.PRESENCE)),                  
    GARGOYLE(set(Discipline.FORTITUDE, Discipline.POTENCE, Discipline.VISCERATIKA));                  
    
    private final Set<Power<?>> inClanDisciplines;
    private final Set<Power<?>> potentialClanDisciplines;
    private final int potentialDisciplinesToSelect;

    private Bloodline(Set<Power<?>> inClanDisciplines) {
        this(inClanDisciplines, set(), 0);
    }
    
    private Bloodline(Set<Power<?>> inClanDisciplines, Set<Power<?>> potentialClanDisciplines) {
        this(inClanDisciplines, potentialClanDisciplines, 1);
    }
    
    private Bloodline(Set<Power<?>> inClanDisciplines, Set<Power<?>> potentialClanDisciplines, int potentialDisciplinesToSelect) {
        this.inClanDisciplines = inClanDisciplines;
        this.potentialClanDisciplines = potentialClanDisciplines;
        this.potentialDisciplinesToSelect = potentialDisciplinesToSelect;
    }

    public Set<Power<?>> getInClanDisciplines() {
        return inClanDisciplines;
    }
    
    public Set<Power<?>> getPotentialClanDisciplines() {
        return potentialClanDisciplines;
    }
    
    public int getPotentialDisciplinesToSelect() {
        return potentialDisciplinesToSelect;
    }

	@Override
	public PlayerCharacter applyTo(PlayerCharacter character) {
		return character.withBloodline(this);
	}

	@Override
	public PlayerCharacter removeFrom(PlayerCharacter character) {
		return character.withBloodline(null);
	}
    
}
