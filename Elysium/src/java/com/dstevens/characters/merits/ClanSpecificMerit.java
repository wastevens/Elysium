package com.dstevens.characters.merits;

import com.dstevens.characters.clans.Clan;

@MeritAnnotation("ClanSpecifc")
public enum ClanSpecificMerit implements Merit<ClanSpecificMerit> {

    SURPRISE_ATTACK(Clan.ASSAMITE, 1),
    AWAKENING_THE_STEEL(Clan.ASSAMITE, 3),
    
    BROTHERHOOD(Clan.BRUJAH, 1),
    BURNING_WRATH(Clan.BRUJAH, 2),
    SCOURGE_OF_ALECTO(Clan.BRUJAH, 3),
    
    PERSONAL_CULT(Clan.FOLLOWER_OF_SET, 1),
    ADDICTIVE_BLOOD(Clan.FOLLOWER_OF_SET, 3),
    SETITE_SORCERY(Clan.FOLLOWER_OF_SET, 4),
    
    PROTEAN_BLOOD(Clan.GANGREL, 1),
    SHAPE_OF_BEASTS_WRATH(Clan.GANGREL, 3),
    
    NECROMANTIC_EXPERTISE(Clan.GIOVANNI, 1),
    MOOK(Clan.GIOVANNI, 2),
    GHOSTLY_RETAINER(Clan.GIOVANNI, 3),
    
    ANGELIC_VISAGE(Clan.LASOMBRA, 1),
    BORN_IN_SHADOW(Clan.LASOMBRA, 2),
    WALK_THE_ABYSS(Clan.LASOMBRA, 3),
    
    EXPANDED_CONSCIOUSNESS(Clan.MALKAVIAN, 1),
    LABYRINTHINE_MIND(Clan.MALKAVIAN, 3),
    SOPHISTRY(Clan.MALKAVIAN, 4),
    
    UNSEEING_EYE(Clan.NOSFERATU, 1),
    HIDDEN_ADVANTAGE(Clan.NOSFERATU, 2),
    PLIABLE_BLOOD(Clan.NOSFERATU, 3),
    UNNATURAL_ADAPTATION(Clan.NOSFERATU, 4),
    
    ARTISTS_BLESSING(Clan.TOREADOR, 1),
    ABSENT_SWAY(Clan.TOREADOR, 3),
    DANCERS_GRACE(Clan.TOREADOR, 4),
    
    THAUMATURGIC_EXPERTISE(Clan.TREMERE, 1),
    TALISMAN(Clan.TREMERE, 3),
    COUNTERMAGIC(Clan.TREMERE, 4),
    
    BLOOD_OF_THE_TZIMISCE(Clan.TZIMISCE, 1),
    SZLACHTA(Clan.TZIMISCE, 2),
    
    AURA_OF_COMMAND(Clan.VENTRUE, 1),
    PARAGON(Clan.VENTRUE, 3),
    REGAL_BEARING(Clan.VENTRUE, 4),
    
    AUSPICIOUS(Clan.CATIFF, 1),
    ECLIPSED_BLOOD(Clan.CATIFF, 2),
    VESTIGES_OF_GREATNESS(Clan.CATIFF, 3),
    
    INFERNAL_HERITAGE(Clan.BAALI, 1),
    
    NECROMANTIC_INSIGHT(Clan.CAPPADOCIAN, 1),
    PIERCED_SHROUD(Clan.CAPPADOCIAN, 3),
    
    WAKING_DREAM(Clan.RAVNOS, 1),
    ESCAPE_ARTIST(Clan.RAVNOS, 3),
    
    RIGHTEOUS_FURY(Clan.SALUBRI, 1),
    SPIRTUAL_ARMOR(Clan.SALUBRI, 2),
    
    SUPERNATURAL_ARIA(Clan.DAUGHTER_OF_CACOPHONY, 1),
    SOARING_OCTAVES(Clan.DAUGHTER_OF_CACOPHONY, 3),
    
    FLIGHT(Clan.GARGOYLE, 1),
    DARK_STATUE(Clan.GARGOYLE, 3);
    
    private final Clan clan;
    private final int points;
    
    private ClanSpecificMerit(Clan clan, int points) {
        this.clan = clan;
        this.points = points;
    }
    
    Clan getClan() {
        return clan;
    }

    @Override
    public int getPoints() {
        return points;
    }
    
    @Override
    public ClanSpecificMerit getTrait() {
        return this;
    }
}
