package com.dstevens.character.trait.power.magic.necromancy;

import com.dstevens.character.PlayerCharacter;
import com.dstevens.character.trait.ApplicableTrait;
import com.dstevens.character.trait.Trait;
import com.dstevens.character.trait.TraitQualities;
import com.dstevens.utilities.IdentityUtilities;

public enum NecromanticRitual implements Trait, ApplicableTrait {

    CALL_OF_THE_HUNGRY_DEAD(0, 1),
    CIRCLE_OF_CERBERUS(1, 1),
    DARK_ASSISTANT(2, 1),
    EYES_OF_THE_GRAVE(3, 1),
    SMOKING_MIRROR(4, 1),
    WARPING_THE_MORBID_VISAGE(5, 1),
    
    BLACK_BLOOD(6, 2),
    DIN_OF_THE_DAMNED(7, 2),
    SEPULCHRAL_BEACON(8, 2),
    STAINED_SIGHT(9, 2),
    SCALES_OF_MAAT(10, 2),
    
    MOLDERING_PRESENCE(11, 3),
    RISE_CERBERUS(12, 3),
    SPIRIT_BEACON(13, 3),
    THE_SERVANT_UNDYING(14, 3),
    
    BASTONE_DIABOLICO(15, 4),
    LETHES_WATER(16, 4),
    RITUAL_OF_XIPE_TOTEC(17, 4),
    STRENGTH_OF_ROTTEN_FLESH(18, 4),
    
    CHILL_OF_OBLIVION(19, 5),
    WEIGHT_OF_THE_TOMB(20, 5);
    
    private final int id;
    private final int rating;

    private NecromanticRitual(int id, int rating) {
        this.id = id;
		this.rating = rating;
    }

	public static NecromanticRitual from(int id) {
		return IdentityUtilities.withId(id, NecromanticRitual.values());
	}
    
	@Override
	public Integer getId() {
		return id;
	}
    
    public int rating() {
        return rating;
    }

    @Override
    public PlayerCharacter applyTo(PlayerCharacter character) {
        return character.withNecromanticRitual(this);
    }
    
    @Override
    public PlayerCharacter removeFrom(PlayerCharacter character) {
    	return character.withoutNecromanticRitual(this);
    }
    
	@Override
	public ApplicableTrait applyWith(TraitQualities qualities) {
		return this;
	}
    
}
