package com.dstevens.characters.merits;

@MeritAnnotation("Morality")
public enum MoralityMerit implements Merit {
    ;

    private final int points;

    private MoralityMerit(int points) {
        this.points = points;
    }
    
    @Override
    public int getPoints() {
        return points;
    }
    
}
