package com.dstevens.characters.powers.magics;

import com.dstevens.characters.PlayerCharacter;

public enum ThaumaturgicalRitual implements Ritual<ThaumaturgicalRitual> {

    BLOOD_MASTERY(1),
    BIND_THE_ACCUSING_TONGUE(1),
    COMMUNICATE_WITH_KINDRED_SIRE(1),
    DEFENSE_OF_THE_SACRED_HAVEN(1),
    ENGAGING_THE_VESSEL_OF_TRANSFERENCE(1),
    ILLUMINATE_THE_TRAIL_OF_PREY(1),
    PRINCIPAL_FOCUS_OF_VITAE_INFUSION(1),
    WARDING_CIRCLE(1),
    
    BANISH_BIG_BROTHER(2),
    BURNING_BLADE(2),
    CRAFT_BLOODSTONE(2),
    EYES_OF_THE_NIGHTHAWK(2),
    ILLUSION_OF_PEACEFUL_DEATH(2),
    MACHINE_BLITZ(2),
    RECURE_OF_THE_HOMELAND(2),
    
    DETECT_THE_HIDDEN_OBSERVER(3),
    FLESH_OF_FIERY_TOUCH(3),
    INCORPOREAL_PASSAGE(3),
    MIRROR_OF_SECOND_SIGHT(3),
    PAVIS_OF_FOUL_PRESENCE(3),
    SOUL_OF_THE_HOMUNCULI(3),
    SHAFT_OF_BELATED_QUIESCENCE(3),
    
    INNOCENCE_OF_THE_CHILDS_HEART(4),
    MIRROR_WALK(4),
    SEVERED_HAND(4),
    SCRY(4),
    
    BLOOD_CONTRACT(5),
    COBRAS_FAVOR(5),
    PAPER_FLESH(5),
    STONE_OF_THE_TRUE_FORM(5);
    
    private int rating;

    private ThaumaturgicalRitual(int rating) {
        this.rating = rating;
    }
    
    public int rating() {
        return rating;
    }

    @Override
    public ThaumaturgicalRitual trait() {
        return this;
    }
    
    @Override
    public PlayerCharacter applyTo(PlayerCharacter character) {
        return character.withThaumaturgicalRitual(this);
    }
    
    @Override
    public PlayerCharacter removeFrom(PlayerCharacter character) {
    	return character.withoutThaumaturgicalRitual(this);
    }
    
}
