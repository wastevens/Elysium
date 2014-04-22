package com.dstevens.characters.merits;

import com.dstevens.players.Setting;

@FlawAnnotation("SettingSpecifc")
public enum SettingSpecificFlaw implements Flaw {
    
    ACCUSED_OF_HERESY(Setting.CAMARILLA, 2),
    FORGIVEN_DIABLERIE(Setting.CAMARILLA, 1),
    SECTARIAN(Setting.CAMARILLA, 2),
    TAINTED_EMBRACE(Setting.CAMARILLA, 2),
    UNTRAINED_COMBATANT(Setting.CAMARILLA, 3),
    
    CRISIS_OF_FAITH(Setting.SABBAT, 2),
    MISTRUSTED(Setting.SABBAT, 1),
    SOUL_SHARD(Setting.SABBAT, 3),
    TENUOUS_LOYALTY(Setting.SABBAT, 3),
    
    BASTARD_CHILDE(Setting.ANARCH, 2),
    BLACK_SHEEP(Setting.ANARCH, 2),
    DUBIOUS_LOYALTIES(Setting.ANARCH, 2),
    ESCAPED_SHOVELHEAD(Setting.ANARCH, 2),
    ONCE_ENSLAVED(Setting.ANARCH, 2);
    
    private final Setting setting;
    private final int points;
    
    private SettingSpecificFlaw(Setting setting, int points) {
        this.setting = setting;
        this.points = points;
    }
    
    Setting getSetting() {
        return setting;
    }

    @Override
    public int getPoints() {
        return points;
    }
    
}
