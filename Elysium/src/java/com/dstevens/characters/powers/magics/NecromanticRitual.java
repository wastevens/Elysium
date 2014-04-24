package com.dstevens.characters.powers.magics;

import com.dstevens.characters.traits.EnumeratedTrait;

public enum NecromanticRitual implements EnumeratedTrait {

    CALL_OF_THE_HUNGRY_DEAD(1),
    CIRCLE_OF_CERBERUS(1),
    DARK_ASSISTANT(1),
    EYES_OF_THE_GRAVE(1),
    SMOKING_MIRROR(1),
    WARPING_THE_MORBID_VISAGE(1),
    
    BLACK_BLOOD(2),
    DIN_OF_THE_DAMNED(2),
    SEPULCHRAL_BEACON(2),
    STAINED_SIGHT(2),
    SCALES_OF_MAAT(2),
    
    MOLDERING_PRESENCE(3),
    RISE_CERBERUS(3),
    SPIRIT_BEACON(3),
    THE_SERVANT_UNDYING(3),
    
    BASTONE_DIABOLICO(4),
    LETHES_WATER(4),
    RITUAL_OF_XIPE_TOTEC(4),
    STRENGTH_OF_ROTTEN_FLESH(4),
    
    CHILL_OF_OBLIVION(5),
    WEIGHT_OF_THE_TOMB(5);
    
    private int rating;

    private NecromanticRitual(int rating) {
        this.rating = rating;
    }
    
    public int getRating() {
        return rating;
    }
    
}
