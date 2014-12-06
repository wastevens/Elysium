package com.dstevens.characters.traits.distinctions;

import com.dstevens.players.Setting;

@MeritAnnotation("SettingSpecifc")
public enum SettingSpecificMerit implements Merit<SettingSpecificMerit> {
    
    ANTIQUITIES(Setting.CAMARILLA, 1),
    ARCHITECT_OF_THE_TOWER(Setting.CAMARILLA, 2),
    EMISSARY_TO_THE_CAMARILLA(Setting.CAMARILLA, 1),
    MACHIAVELLIAN_PRODIGY(Setting.CAMARILLA, 1),
    MASTER_OF_PUPPETS(Setting.CAMARILLA, 2),
    MONOPOLY(Setting.CAMARILLA, 1),
    PRESTIGIOUS_SIRE(Setting.CAMARILLA, 1),
    SOCIAL_NOBILITY(Setting.CAMARILLA, 3),
    
    BLACK_HAND_MEMBERSHIP(Setting.SABBAT, 2),
    EXECUTIONER(Setting.SABBAT, 1),
    FANATIC(Setting.SABBAT, 2),
    INQUISITION_MEMBERSHIP(Setting.SABBAT, 2),
    KEEPER_OF_A_SACRED_TEXT(Setting.SABBAT, 1),
    PACK_PLAYER(Setting.SABBAT, 3),
    REVELATOR(Setting.SABBAT, 3),
    SANCTIFIED(Setting.SABBAT, 1),
    SCHOLAR_OF_THE_SWORD(Setting.SABBAT, 1),
    VOLATILE(Setting.SABBAT, 1),
    ZEALOT(Setting.SABBAT, 1),
    
    DAUNTLESS(Setting.ANARCH, 2),
    DHAMPIR(Setting.ANARCH, 4),
    EAR_TO_THE_GROUND(Setting.ANARCH, 1),
    ELDER_OF_THE_REVOLUTION(Setting.ANARCH, 2),
    LION_OF_THE_CAUSE(Setting.ANARCH, 3),
    MONIKER(Setting.ANARCH, 1),
    MORAL_COMPASS(Setting.ANARCH, 1),
    OLD_DOG(Setting.ANARCH, 1),
    SORCEROUS_DABBLER(Setting.ANARCH, 2),
    TECH_JUNKIE(Setting.ANARCH, 2),
    WILD_ONE(Setting.ANARCH, 1);
    
    private final Setting setting;
    private final int points;
    
    private SettingSpecificMerit(Setting setting, int points) {
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

    @Override
    public SettingSpecificMerit trait() {
        return this;
    }
}
