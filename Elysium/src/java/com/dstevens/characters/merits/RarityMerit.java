package com.dstevens.characters.merits;

@MeritAnnotation("Rarity")
public enum RarityMerit implements Merit<RarityMerit> {
    UNCOMMON(2),
    RARE(4),
    RESTRICTED(6);

    private final int points;

    private RarityMerit(int points) {
        this.points = points;
    }
    
    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public RarityMerit trait() {
        return this;
    }
}
