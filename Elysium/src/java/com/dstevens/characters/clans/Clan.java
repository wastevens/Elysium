package com.dstevens.characters.clans;

import static com.dstevens.collections.Sets.set;

import java.util.Set;

public enum Clan {

    ASSAMITE(Bloodline.ASSAMITE, Bloodline.VIZIER, Bloodline.SORCERER),
    BRUJAH(Bloodline.BRUJAH, Bloodline.TRUE_BRUJAH),
    FOLLOWER_OF_SET(Bloodline.FOLLOWER_OF_SET, Bloodline.TLACLQUE, Bloodline.VIPER),
    GANGREL(Bloodline.GANGREL, Bloodline.COYOTE, Bloodline.NOIAD, Bloodline.AHRIMANE),
    GIOVANNI(Bloodline.GIOVANNI, Bloodline.PREMASCINE),
    LASOMBRA(Bloodline.LASOMBRA, Bloodline.KISAYD),
    MALKAVIAN(Bloodline.MALKAVIAN, Bloodline.ANANKE, Bloodline.KNIGHT_OF_THE_MOON),
    NOSFERATU(Bloodline.NOSFERATU),
    TOREADOR(Bloodline.TOREADOR, Bloodline.ISHTARRI, Bloodline.VOLGIRRE),
    TREMERE(Bloodline.TREMERE, Bloodline.TELYAV),
    TZIMISCE(Bloodline.TZIMISCE, Bloodline.CARPATHIAN, Bloodline.KOLDUN),
    VENTRUE(Bloodline.VENTRUE, Bloodline.CRUSADER),
    CATIFF(Bloodline.CATIFF),
    BAALI(Bloodline.BAALI, Bloodline.ANGELLIS_ATER),
    CAPPADOCIAN(Bloodline.CAPPADOCIAN, Bloodline.SAMEDI, Bloodline.LAMIA),
    RAVNOS(Bloodline.RAVNOS, Bloodline.BRAHMAN),
    SALUBRI(Bloodline.SALUBRI, Bloodline.HEALER),
    DAUGHTER_OF_CACOPHONY(Bloodline.DAUGHTER_OF_CACOPHONY),
    GARGOYLE(Bloodline.GARGOYLE);
    
    private final Set<Bloodline> bloodlines;

    private Clan(Bloodline...bloodlines) {
        this.bloodlines = set(bloodlines);
    }
    
    public Set<Bloodline> getBloodlines() {
        return bloodlines;
    }
}
