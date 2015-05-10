package com.dstevens.characters.traits.powers.magic.thaumaturgy;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.Trait;
import com.dstevens.characters.traits.TraitQualities;

public enum ThaumaturgicalRitual implements Trait, ApplicableTrait {

    BLOOD_MASTERY(0, 1),
    BIND_THE_ACCUSING_TONGUE(1, 1),
    COMMUNICATE_WITH_KINDRED_SIRE(2, 1),
    DEFENSE_OF_THE_SACRED_HAVEN(3, 1),
    ENGAGING_THE_VESSEL_OF_TRANSFERENCE(4, 1),
    ILLUMINATE_THE_TRAIL_OF_PREY(5, 1),
    PRINCIPAL_FOCUS_OF_VITAE_INFUSION(6, 1),
    WARDING_CIRCLE(7, 1),
    
    BANISH_BIG_BROTHER(8, 2),
    BURNING_BLADE(9, 2),
    CRAFT_BLOODSTONE(10, 2),
    EYES_OF_THE_NIGHTHAWK(11, 2),
    ILLUSION_OF_PEACEFUL_DEATH(12, 2),
    MACHINE_BLITZ(13, 2),
    RECURE_OF_THE_HOMELAND(14, 2),
    
    DETECT_THE_HIDDEN_OBSERVER(15, 3),
    FLESH_OF_FIERY_TOUCH(16, 3),
    INCORPOREAL_PASSAGE(17, 3),
    MIRROR_OF_SECOND_SIGHT(18, 3),
    PAVIS_OF_FOUL_PRESENCE(19, 3),
    SOUL_OF_THE_HOMUNCULI(20, 3),
    SHAFT_OF_BELATED_QUIESCENCE(21, 3),
    
    INNOCENCE_OF_THE_CHILDS_HEART(22, 4),
    MIRROR_WALK(23, 4),
    SEVERED_HAND(24, 4),
    SCRY(25, 4),
    
    BLOOD_CONTRACT(26, 5),
    COBRAS_FAVOR(27, 5),
    PAPER_FLESH(28, 5),
    STONE_OF_THE_TRUE_FORM(29, 5);
    
    private final int id;
    private final int rating;

    private ThaumaturgicalRitual(int id, int rating) {
        this.id = id;
		this.rating = rating;
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
        return character.withThaumaturgicalRitual(this);
    }
    
    @Override
    public PlayerCharacter removeFrom(PlayerCharacter character) {
    	return character.withoutThaumaturgicalRitual(this);
    }
    
	@Override
	public ApplicableTrait applyWith(TraitQualities qualities) {
		return this;
	}
}
