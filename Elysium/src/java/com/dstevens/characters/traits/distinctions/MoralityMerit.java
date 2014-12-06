package com.dstevens.characters.traits.distinctions;

import com.dstevens.characters.clans.*;

@MeritAnnotation("Morality")
public enum MoralityMerit implements Merit<MoralityMerit> {

    PATH_OF_BLOOD(Clan.ASSAMITE),
    PATH_OF_CAINE(),
    PATH_OF_CHIVALRY(),
    PATH_OF_DEATH_AND_THE_SOUL(Clan.GIOVANNI),
    PATH_OF_ECSTASY(Clan.FOLLOWER_OF_SET),
    PATH_OF_EVIL_REVELATIONS(),
    PATH_OF_THE_FERAL_HEART(),
    PATH_OF_HEAVEN(),
    PATH_OF_HARMONY(),
    PATH_OF_HONORABLE_ACCORD(),
    PATH_OF_LILITH(),
    PATH_OF_METAMORPHOSIS(Clan.TZIMISCE),
    PATH_OF_NIGHT(Clan.LASOMBRA),
    PATH_OF_ORION(),
    PATH_OF_PARADOX(Clan.RAVNOS),
    PATH_OF_POWER_AND_THE_INNER_VOICE(),
    PATH_OF_THE_SCORCHED_HEART(Clan.BRUJAH, Bloodline.TRUE_BRUJAH),
    PATH_OF_TYPHON_SET(Clan.FOLLOWER_OF_SET, Bloodline.VIPER);
    
    private final Clan clan;
    private final Bloodline bloodline;
    private final int points;
    
    private MoralityMerit() {
        this(null, null, 3);
    }
    
    private MoralityMerit(Clan clan) {
        this(clan, null, 3);
    }
    
    private MoralityMerit(Clan clan, Bloodline bloodline) {
        this(clan, bloodline, 3);
    }
    
    private MoralityMerit(Clan clan, Bloodline bloodline, int points) {
        this.bloodline = bloodline;
        this.clan = clan;
        this.points = points;
    }
    
    Clan getClan() {
        return clan;
    }
    
    Bloodline getBloodline() {
        return bloodline;
    }
    
    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public MoralityMerit trait() {
        return this;
    }
}
