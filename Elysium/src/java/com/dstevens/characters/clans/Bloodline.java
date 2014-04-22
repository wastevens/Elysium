package com.dstevens.characters.clans;

import static com.dstevens.collections.Sets.set;

import java.util.Set;

import com.dstevens.characters.powers.*;
import com.dstevens.characters.powers.magics.*;

public enum Bloodline {

    ASSAMITE(set((Power) Discipline.CELERITY, Discipline.OBFUSCATE, Discipline.QUIETUS)),
    VIZIER(set((Power) Discipline.AUSPEX, Discipline.CELERITY, Discipline.QUIETUS)),
    SORCERER(set((Power) Discipline.OBFUSCATE, Discipline.QUIETUS, Thaumaturgy.LURE_OF_FLAMES), 
             set((Power) Thaumaturgy.MOVEMENT_OF_THE_MIND, Thaumaturgy.PATH_OF_BLOOD, Thaumaturgy.PATH_OF_CONJURING, Thaumaturgy.PATH_OF_CORRUPTION, 
                         Thaumaturgy.PATH_OF_ELEMENTAL_MASTERY, Thaumaturgy.PATH_OF_TECHNOMANCY, Thaumaturgy.PATH_OF_WEATHER_MASTERY)),
    BRUJAH(set((Power) Discipline.CELERITY, Discipline.POTENCE, Discipline.PRESENCE)), 
    TRUE_BRUJAH(set((Power) Discipline.TEMPORSIS, Discipline.POTENCE, Discipline.PRESENCE)), 
    FOLLOWER_OF_SET(set((Power) Discipline.OBFUSCATE, Discipline.PRESENCE, Discipline.SERPENTIS)), 
    TLACLQUE(set((Power) Discipline.PRESENCE, Discipline.OBFUSCATE, Discipline.PROTEAN)), 
    VIPER(set((Power) Discipline.POTENCE, Discipline.PRESENCE, Discipline.SERPENTIS)), 
    GANGREL(set((Power) Discipline.ANIMALISM, Discipline.FORTITUDE, Discipline.PROTEAN)), 
    COYOTE(set((Power) Discipline.CELERITY, Discipline.OBFUSCATE, Discipline.PROTEAN)), 
    NOIAD(set((Power) Discipline.ANIMALISM, Discipline.AUSPEX, Discipline.PROTEAN)), 
    AHRIMANE(set((Power) Discipline.ANIMALISM, Discipline.PRESENCE, Thaumaturgy.PATH_OF_ELEMENTAL_MASTERY)), 
    GIOVANNI(set((Power) Discipline.DOMINATE, Discipline.POTENCE, Necromancy.SEPULCHRE_PATH)), 
    PREMASCINE(set((Power) Discipline.DOMINATE, Discipline.PRESENCE, Necromancy.SEPULCHRE_PATH)),
    LASOMBRA(set((Power) Discipline.DOMINATE, Discipline.POTENCE, Discipline.OBTENEBRATION)),
    KISAYD(set((Power) Discipline.DOMINATE, Discipline.MYTHERCERIA, Discipline.OBTENEBRATION)),
    MALKAVIAN(set((Power) Discipline.AUSPEX, Discipline.DEMENTATION, Discipline.OBFUSCATE)),
    ANANKE(set((Power) Discipline.AUSPEX, Discipline.DEMENTATION, Discipline.PRESENCE)),
    KNIGHT_OF_THE_MOON(set((Power) Discipline.AUSPEX, Discipline.DOMINATE, Discipline.OBFUSCATE)),
    NOSFERATU(set((Power) Discipline.ANIMALISM, Discipline.OBFUSCATE, Discipline.POTENCE)),
    TOREADOR(set((Power) Discipline.AUSPEX, Discipline.CELERITY, Discipline.PRESENCE)),
    ISHTARRI(set((Power) Discipline.CELERITY, Discipline.FORTITUDE, Discipline.PRESENCE)),
    VOLGIRRE(set((Power) Discipline.AUSPEX, Discipline.CELERITY, Discipline.PRESENCE)),
    TREMERE(set((Power) Discipline.AUSPEX, Discipline.DOMINATE, Thaumaturgy.PATH_OF_BLOOD)),
    TELYAV(set((Power) Discipline.AUSPEX, Discipline.PRESENCE, Thaumaturgy.PATH_OF_BLOOD)),
    TZIMISCE(set((Power) Discipline.ANIMALISM, Discipline.AUSPEX, Discipline.VICISSITUDE)),
    CARPATHIAN(set((Power) Discipline.ANIMALISM, Discipline.AUSPEX, Discipline.DOMINATE)),
    KOLDUN(set((Power) Discipline.ANIMALISM, Discipline.AUSPEX, Thaumaturgy.PATH_OF_ELEMENTAL_MASTERY), 
           set((Power) Thaumaturgy.LURE_OF_FLAMES, Thaumaturgy.MOVEMENT_OF_THE_MIND, Thaumaturgy.PATH_OF_BLOOD, Thaumaturgy.PATH_OF_CONJURING, 
                       Thaumaturgy.PATH_OF_CORRUPTION, Thaumaturgy.PATH_OF_TECHNOMANCY, Thaumaturgy.PATH_OF_WEATHER_MASTERY)),
    VENTRUE(set((Power) Discipline.DOMINATE, Discipline.FORTITUDE, Discipline.PRESENCE)),
    CRUSADER(set((Power) Discipline.AUSPEX, Discipline.DOMINATE, Discipline.FORTITUDE)),
    CATIFF(set(), set(Discipline.ANIMALISM, Discipline.AUSPEX, Discipline.CELERITY, Discipline.DOMINATE, 
                      Discipline.FORTITUDE, Discipline.OBFUSCATE, Discipline.PRESENCE, Discipline.POTENCE), 3),
    BAALI(set((Power) Discipline.DAIMOINON, Discipline.OBFUSCATE, Discipline.PRESENCE)),
    ANGELLIS_ATER(set((Power) Discipline.DAIMOINON, Discipline.DOMINATE), set(Discipline.PRESENCE, Discipline.POTENCE, Discipline.OBFUSCATE)),
    CAPPADOCIAN(set((Power) Discipline.AUSPEX, Discipline.FORTITUDE, Necromancy.MORTIS_PATH)),
    SAMEDI(set((Power) Discipline.FORTITUDE, Discipline.OBFUSCATE, Discipline.THANATOSIS)),
    LAMIA(set((Power) Discipline.FORTITUDE, Discipline.PRESENCE, Necromancy.MORTIS_PATH)),
    RAVNOS(set((Power) Discipline.ANIMALISM, Discipline.FORTITUDE, Discipline.CHIMERSTRY)),                  
    BRAHMAN(set((Power) Discipline.ANIMALISM, Discipline.AUSPEX, Discipline.CHIMERSTRY)),                  
    SALUBRI(set((Power) Discipline.AUSPEX, Discipline.FORTITUDE, Discipline.VALEREN)),                  
    HEALER(set((Power) Discipline.AUSPEX, Discipline.FORTITUDE, Discipline.OBEAH)),                  
    DAUGHTER_OF_CACOPHONY(set((Power) Discipline.FORTITUDE, Discipline.MELPOMINEE, Discipline.PRESENCE)),                  
    GARGOYLE(set((Power) Discipline.FORTITUDE, Discipline.POTENCE, Discipline.VISCERATIKA));                  
    
    private final Set<Power> inClanDisciplines;
    private final Set<Power> potentialClanDisciplines;
    private final int potentialDisciplinesToSelect;

    private Bloodline(Set<Power> inClanDisciplines) {
        this(inClanDisciplines, set(), 0);
    }
    
    private Bloodline(Set<Power> inClanDisciplines, Set<Power> potentialClanDisciplines) {
        this(inClanDisciplines, potentialClanDisciplines, 1);
    }
    
    private Bloodline(Set<Power> inClanDisciplines, Set<Power> potentialClanDisciplines, int potentialDisciplinesToSelect) {
        this.inClanDisciplines = inClanDisciplines;
        this.potentialClanDisciplines = potentialClanDisciplines;
        this.potentialDisciplinesToSelect = potentialDisciplinesToSelect;
    }

    public Set<Power> getInClanDisciplines() {
        return inClanDisciplines;
    }
    
    public Set<Power> getPotentialClanDisciplines() {
        return potentialClanDisciplines;
    }
    
    public int getPotentialDisciplinesToSelect() {
        return potentialDisciplinesToSelect;
    }
    
}
