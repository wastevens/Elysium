package com.dstevens.characters.merits;

import com.dstevens.characters.clans.Clan;

@MeritAnnotation("ClanSpecifc")
public enum ClanSpecificMerit implements Merit {

    PARAGON(Clan.VENTRUE, 3);
    
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
    
}
