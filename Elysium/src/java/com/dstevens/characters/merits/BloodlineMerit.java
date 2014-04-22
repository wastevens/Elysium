package com.dstevens.characters.merits;

import com.dstevens.characters.clans.*;

@MeritAnnotation("Bloodline")
public enum BloodlineMerit implements Merit {

    ;
    
    private final Bloodline bloodline;
    private final Clan clan;
    private final int points;
    
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
