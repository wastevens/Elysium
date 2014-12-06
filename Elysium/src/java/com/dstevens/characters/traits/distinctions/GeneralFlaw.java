package com.dstevens.characters.traits.distinctions;

@FlawAnnotation("General")
public enum GeneralFlaw implements Flaw<GeneralFlaw> {

    ADDICTION(2),
    AMNESIA(1),
    ARCHAIC(2),
    AWKWARD_MOBILITY(2),
    BAD_SIGHT(2),
    BEACON_OF_THE_UNHOLY(3),
    BEAST_IN_THE_MIRROR(2),
    BESTIAL_INSTINCT(2),
    BLOOD_ROT(5),
    BOUND_TO_THE_EARTH(2),
    BLUNTED_FANGS(2),
    CANNOT_CROSS_RUNNING_WATER(2),
    CARELESS(1),
    CHILDLIKE(4),
    CONSPICIOUS_CONSUMPTION(3),
    CURIOUSITY(2),
    CURSED(1),
    DARK_FATE(5),
    DARK_SECRET(1),
    DEATH_SIGHT(2),
    DEEP_SLEEPER(2),
    DISEASE_CARRIER(4),
    DULL(2),
    EERIE_PRESENCE(1),
    FLESH_OF_THE_CORPSE(5),
    FRAGILE_BONES(4),
    GEHENNA_PROPHET(2),
    GRIP_OF_THE_DAMNED(2),
    HARD_OF_HEARING(2),
    HAUNTED(1),
    HUNTED(4),
    INFAMOUS_BROOD(3),
    ILLITERATE(1),
    IMPATIENT(2),
    INEPT(3),
    INTOLERANCE(1),
    KNOWN_TO_BE_DEAD(1),
    LESSER_GENERATION_1(1),
    LESSER_GENERATION_2(2),
    LOW_PAIN_THRESHOLD(3),
    MAGIC_SUSCEPTIBILITY(2),
    METHUSELAHS_THIRST(4),
    NIGHTMARES(1),
    NECROPHILE(1),
    NOTORIETY(2),
    OBVIOUS_PREDATOR(2),
    ONE_EYE(3),
    OVERCONFIDENT(2),
    PERMANENT_WOUND(4),
    POSEIDONS_CALL(3),
    PIED_PIPER(1),
    PREY_EXCLUSION(1),
    REPELLED_BY_RELIGION(3),
    SHORT_ATTENTION_SPAN(4),
    SHORT_FUSE(2),
    SLOW_HEALING(3),
    SLOW_REACTIONS(3),
    STOLEN_POTENTIAL(3),
    THIN_BLOODED(3),
    THIRST_FOR_INNOCENCE(1),
    TROUBLE_MAGNET(1),
    VULERABLE_TO_SILVER(2),
    WEAK_WILLED(3),
    WEAK_STOMACH(3);
    
    
    ;

    private int points;

    private GeneralFlaw(int points) {
        this.points = points;
    }
    
    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public GeneralFlaw trait() {
        return this;
    }
}
