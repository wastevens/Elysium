package com.dstevens.characters.merits;

import com.dstevens.characters.clans.*;

@MeritAnnotation("Bloodline")
public enum BloodlineMerit implements Merit {

    ASSAMITE(Bloodline.ASSAMITE, Clan.ASSAMITE),
    VIZIER(Bloodline.VIZIER, Clan.ASSAMITE, 2),
    SORCERER(Bloodline.VIZIER, Clan.ASSAMITE, 4),
    
    BRUJAH(Bloodline.BRUJAH, Clan.BRUJAH),
    TRUE_BRUJAH(Bloodline.TRUE_BRUJAH, Clan.BRUJAH, 4),
    
    FOLLOWER_OF_SET(Bloodline.FOLLOWER_OF_SET, Clan.FOLLOWER_OF_SET),
    TLACLQUE(Bloodline.TLACLQUE, Clan.FOLLOWER_OF_SET, 2),
    VIPER(Bloodline.VIPER, Clan.FOLLOWER_OF_SET, 2),
    
    GANGREL(Bloodline.GANGREL, Clan.GANGREL),
    COYOTE(Bloodline.COYOTE, Clan.GANGREL, 2),
    NOIAD(Bloodline.NOIAD, Clan.GANGREL, 2),
    AHRIMANE(Bloodline.AHRIMANE, Clan.GANGREL, 4),
    
    GIOVANNI(Bloodline.GIOVANNI, Clan.GIOVANNI),
    PREMASCINE(Bloodline.PREMASCINE, Clan.GIOVANNI, 4),
    
    LASOMBRA(Bloodline.LASOMBRA, Clan.LASOMBRA),
    KISAYD(Bloodline.KISAYD, Clan.LASOMBRA, 4),
    
    MALKAVIAN(Bloodline.MALKAVIAN, Clan.MALKAVIAN),
    ANANKE(Bloodline.ANANKE, Clan.MALKAVIAN, 2),
    KNIGHT_OF_THE_MOON(Bloodline.KNIGHT_OF_THE_MOON, Clan.MALKAVIAN, 2),
    
    NOSFERATU(Bloodline.NOSFERATU, Clan.NOSFERATU),
    
    TOREADOR(Bloodline.TOREADOR, Clan.TOREADOR),
    ISHTARRI(Bloodline.ISHTARRI, Clan.TOREADOR, 2),
    VOLGIRRE(Bloodline.VOLGIRRE, Clan.TOREADOR, 2),
    
    TREMERE(Bloodline.TREMERE, Clan.TREMERE),
    TELYAV(Bloodline.TELYAV, Clan.TREMERE, 2),
    
    TZIMISCE(Bloodline.TZIMISCE, Clan.TZIMISCE),
    CARPATHIAN(Bloodline.CARPATHIAN, Clan.TZIMISCE, 3),
    KOLDUN(Bloodline.KOLDUN, Clan.TZIMISCE, 4),
    
    VENTRUE(Bloodline.VENTRUE, Clan.VENTRUE),
    CRUSADER(Bloodline.CRUSADER, Clan.VENTRUE, 2),
    
    CATIFF(Bloodline.CATIFF, Clan.CATIFF),
    
    BAALI(Bloodline.BAALI, Clan.BAALI),
    ANGELLIS_ATER(Bloodline.ANGELLIS_ATER, Clan.BAALI, 3),
    
    CAPPADOCIAN(Bloodline.CAPPADOCIAN, Clan.CAPPADOCIAN),
    SAMEDI(Bloodline.CAPPADOCIAN, Clan.CAPPADOCIAN, 2),
    LAMIA(Bloodline.CAPPADOCIAN, Clan.CAPPADOCIAN, 3),
    
    RAVNOS(Bloodline.RAVNOS, Clan.RAVNOS),
    BRAHMAN(Bloodline.BRAHMAN, Clan.RAVNOS, 2),
    
    SALUBRI(Bloodline.SALUBRI, Clan.SALUBRI),
    HEALER(Bloodline.HEALER, Clan.SALUBRI, 3),
    
    DAUGHTER_OF_CACOPHONY(Bloodline.DAUGHTER_OF_CACOPHONY, Clan.DAUGHTER_OF_CACOPHONY),
    
    GARGOYLE(Bloodline.GARGOYLE, Clan.GARGOYLE);
    
    private final Bloodline bloodline;
    private final Clan clan;
    private final int points;
    
    private BloodlineMerit(Bloodline bloodline, Clan clan) {
        this(bloodline, clan, 0);
    }
    
    private BloodlineMerit(Bloodline bloodline, Clan clan, int points) {
        this.bloodline = bloodline;
        this.clan = clan;
        this.points = points;
    }
    
    Bloodline getBloodline() {
        return bloodline;
    }
    
    Clan getClan() {
        return clan;
    }

    @Override
    public int getPoints() {
        return points;
    }
}
