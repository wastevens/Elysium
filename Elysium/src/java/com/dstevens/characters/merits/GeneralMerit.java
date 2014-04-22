package com.dstevens.characters.merits;

@MeritAnnotation("General")
public enum GeneralMerit implements Merit {
    ;

    private final int points;

    private GeneralMerit(int points) {
        this.points = points;
    }
    
    @Override
    public int getPoints() {
        return points;
    }
    
}
