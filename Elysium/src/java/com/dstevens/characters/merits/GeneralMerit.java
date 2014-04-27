package com.dstevens.characters.merits;

@MeritAnnotation("General")
public enum GeneralMerit implements Merit<GeneralMerit> {
    ACUTE_SENSE(1),
    ADDITIONAL_COMMON_DISCIPLINE(4),
    ADDITIONAL_UNCOMMON_DISCIPLINE(5),
    AMBIDEXTEROUS(2),
    ARCANE(1),
    BLASE(3),
    CALM_HEART(1),
    CLEAR_SIGHTED(3),
    CODE_OF_HONOR(2),
    DAREDEVIL(2),
    EFFICIENT_DIGESTION(1),
    EFFICIENT_LEARNER(2),
    GOLCONDA_SEEKER(5),
    INFERNAL_POWER(3),
    INTENSE_VITALITY(3),
    IRON_WILL(3),
    LIGHT_SLEEPER(1),
    LOREMASTER(1),
    LUCKY(2),
    MAGIC_RESISTANCE(3),
    MEDIUM(1),
    NATURAL_LINGUIST(1),
    NECROMANTIC_TRAINING(5),
    ORACULAR_ABILITY(2),
    PERSONAL_MASQUERADE(1),
    REPUTATION(2),
    RUGGED(3),
    SKILL_APTITUDE(2),
    SLIPPERY_CUSTOMER(2),
    THAUMATURGIC_TRAINING(4),
    UNBONDABLE(4),
    UNYIELDING(4),
    VERSATILE(3),
    WHISPER_OF_LIFE(1);
    ;

    private final int points;

    private GeneralMerit(int points) {
        this.points = points;
    }
    
    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public GeneralMerit trait() {
        return this;
    }
}
