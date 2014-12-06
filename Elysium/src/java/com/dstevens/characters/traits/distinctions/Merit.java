package com.dstevens.characters.traits.distinctions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dstevens.characters.clans.Bloodline;
import com.dstevens.characters.clans.Clan;
import com.dstevens.players.Setting;

public enum Merit {
	//General merits
	ACUTE_SENSE(MeritType.GENERAL, 1),
    ADDITIONAL_COMMON_DISCIPLINE(MeritType.GENERAL, 4),
    ADDITIONAL_UNCOMMON_DISCIPLINE(MeritType.GENERAL, 5),
    AMBIDEXTEROUS(MeritType.GENERAL, 2),
    ARCANE(MeritType.GENERAL, 1),
    BLASE(MeritType.GENERAL, 3),
    CALM_HEART(MeritType.GENERAL, 1),
    CLEAR_SIGHTED(MeritType.GENERAL, 3),
    CODE_OF_HONOR(MeritType.GENERAL, 2),
    DAREDEVIL(MeritType.GENERAL, 2),
    EFFICIENT_DIGESTION(MeritType.GENERAL, 1),
    EFFICIENT_LEARNER(MeritType.GENERAL, 2),
    GOLCONDA_SEEKER(MeritType.GENERAL, 5),
    INFERNAL_POWER(MeritType.GENERAL, 3),
    INTENSE_VITALITY(MeritType.GENERAL, 3),
    IRON_WILL(MeritType.GENERAL, 3),
    LIGHT_SLEEPER(MeritType.GENERAL, 1),
    LOREMASTER(MeritType.GENERAL, 1),
    LUCKY(MeritType.GENERAL, 2),
    MAGIC_RESISTANCE(MeritType.GENERAL, 3),
    MEDIUM(MeritType.GENERAL, 1),
    NATURAL_LINGUIST(MeritType.GENERAL, 1),
    NECROMANTIC_TRAINING(MeritType.GENERAL, 5),
    ORACULAR_ABILITY(MeritType.GENERAL, 2),
    PERSONAL_MASQUERADE(MeritType.GENERAL, 1),
    REPUTATION(MeritType.GENERAL, 2),
    RUGGED(MeritType.GENERAL, 3),
    SKILL_APTITUDE(MeritType.GENERAL, 2),
    SLIPPERY_CUSTOMER(MeritType.GENERAL, 2),
    THAUMATURGIC_TRAINING(MeritType.GENERAL, 4),
    UNBONDABLE(MeritType.GENERAL, 4),
    UNYIELDING(MeritType.GENERAL, 4),
    VERSATILE(MeritType.GENERAL, 3),
    WHISPER_OF_LIFE(MeritType.GENERAL, 1),
    
    //Morality Merits
    PATH_OF_BLOOD(MeritType.MORALITY, Clan.ASSAMITE, 3),
    PATH_OF_CAINE(MeritType.MORALITY, 3),
    PATH_OF_CHIVALRY(MeritType.MORALITY, 3),
    PATH_OF_DEATH_AND_THE_SOUL(MeritType.MORALITY, Clan.GIOVANNI, 3),
    PATH_OF_ECSTASY(MeritType.MORALITY, Clan.FOLLOWER_OF_SET, 3),
    PATH_OF_EVIL_REVELATIONS(MeritType.MORALITY, 3),
    PATH_OF_THE_FERAL_HEART(MeritType.MORALITY, 3),
    PATH_OF_HEAVEN(MeritType.MORALITY, 3),
    PATH_OF_HARMONY(MeritType.MORALITY, 3),
    PATH_OF_HONORABLE_ACCORD(MeritType.MORALITY, 3),
    PATH_OF_LILITH(MeritType.MORALITY, 3),
    PATH_OF_METAMORPHOSIS(MeritType.MORALITY, Clan.TZIMISCE, 3),
    PATH_OF_NIGHT(MeritType.MORALITY, Clan.LASOMBRA, 3),
    PATH_OF_ORION(MeritType.MORALITY, 3),
    PATH_OF_PARADOX(MeritType.MORALITY, Clan.RAVNOS, 3),
    PATH_OF_POWER_AND_THE_INNER_VOICE(MeritType.MORALITY, 3),
    PATH_OF_THE_SCORCHED_HEART(MeritType.MORALITY, Clan.BRUJAH, Bloodline.TRUE_BRUJAH, 3),
    PATH_OF_TYPHON_SET(MeritType.MORALITY, Clan.FOLLOWER_OF_SET, Bloodline.VIPER, 3),
    
    //Rarity merits
    UNCOMMON(MeritType.GENERAL, 2),
    RARE(MeritType.GENERAL, 4),
    RESTRICTED(MeritType.GENERAL, 6),
	
    //Clan specific merits
    SURPRISE_ATTACK(MeritType.CLAN, Clan.ASSAMITE, 1),
    AWAKENING_THE_STEEL(MeritType.CLAN, Clan.ASSAMITE, 3),
    BROTHERHOOD(MeritType.CLAN, Clan.BRUJAH, 1),
    BURNING_WRATH(MeritType.CLAN, Clan.BRUJAH, 2),
    SCOURGE_OF_ALECTO(MeritType.CLAN, Clan.BRUJAH, 3),
    PERSONAL_CULT(MeritType.CLAN, Clan.FOLLOWER_OF_SET, 1),
    ADDICTIVE_BLOOD(MeritType.CLAN, Clan.FOLLOWER_OF_SET, 3),
    SETITE_SORCERY(MeritType.CLAN, Clan.FOLLOWER_OF_SET, 4),
    PROTEAN_BLOOD(MeritType.CLAN, Clan.GANGREL, 1),
    SHAPE_OF_BEASTS_WRATH(MeritType.CLAN, Clan.GANGREL, 3),
    NECROMANTIC_EXPERTISE(MeritType.CLAN, Clan.GIOVANNI, 1),
    MOOK(MeritType.CLAN, Clan.GIOVANNI, 2),
    GHOSTLY_RETAINER(MeritType.CLAN, Clan.GIOVANNI, 3),
    ANGELIC_VISAGE(MeritType.CLAN, Clan.LASOMBRA, 1),
    BORN_IN_SHADOW(MeritType.CLAN, Clan.LASOMBRA, 2),
    WALK_THE_ABYSS(MeritType.CLAN, Clan.LASOMBRA, 3),
    EXPANDED_CONSCIOUSNESS(MeritType.CLAN, Clan.MALKAVIAN, 1),
    LABYRINTHINE_MIND(MeritType.CLAN, Clan.MALKAVIAN, 3),
    SOPHISTRY(MeritType.CLAN, Clan.MALKAVIAN, 4),
    UNSEEING_EYE(MeritType.CLAN, Clan.NOSFERATU, 1),
    HIDDEN_ADVANTAGE(MeritType.CLAN, Clan.NOSFERATU, 2),
    PLIABLE_BLOOD(MeritType.CLAN, Clan.NOSFERATU, 3),
    UNNATURAL_ADAPTATION(MeritType.CLAN, Clan.NOSFERATU, 4),
    ARTISTS_BLESSING(MeritType.CLAN, Clan.TOREADOR, 1),
    ABSENT_SWAY(MeritType.CLAN, Clan.TOREADOR, 3),
    DANCERS_GRACE(MeritType.CLAN, Clan.TOREADOR, 4),
    THAUMATURGIC_EXPERTISE(MeritType.CLAN, Clan.TREMERE, 1),
    TALISMAN(MeritType.CLAN, Clan.TREMERE, 3),
    COUNTERMAGIC(MeritType.CLAN, Clan.TREMERE, 4),
    BLOOD_OF_THE_TZIMISCE(MeritType.CLAN, Clan.TZIMISCE, 1),
    SZLACHTA(MeritType.CLAN, Clan.TZIMISCE, 2),
    AURA_OF_COMMAND(MeritType.CLAN, Clan.VENTRUE, 1),
    PARAGON(MeritType.CLAN, Clan.VENTRUE, 3),
    REGAL_BEARING(MeritType.CLAN, Clan.VENTRUE, 4),
    AUSPICIOUS(MeritType.CLAN, Clan.CATIFF, 1),
    ECLIPSED_BLOOD(MeritType.CLAN, Clan.CATIFF, 2),
    VESTIGES_OF_GREATNESS(MeritType.CLAN, Clan.CATIFF, 3),
    INFERNAL_HERITAGE(MeritType.CLAN, Clan.BAALI, 1),
    NECROMANTIC_INSIGHT(MeritType.CLAN, Clan.CAPPADOCIAN, 1),
    PIERCED_SHROUD(MeritType.CLAN, Clan.CAPPADOCIAN, 3),
    WAKING_DREAM(MeritType.CLAN, Clan.RAVNOS, 1),
    ESCAPE_ARTIST(MeritType.CLAN, Clan.RAVNOS, 3),
    RIGHTEOUS_FURY(MeritType.CLAN, Clan.SALUBRI, 1),
    SPIRTUAL_ARMOR(MeritType.CLAN, Clan.SALUBRI, 2),
    SUPERNATURAL_ARIA(MeritType.CLAN, Clan.DAUGHTER_OF_CACOPHONY, 1),
    SOARING_OCTAVES(MeritType.CLAN, Clan.DAUGHTER_OF_CACOPHONY, 3),
    FLIGHT(MeritType.CLAN, Clan.GARGOYLE, 1),
    DARK_STATUE(MeritType.CLAN, Clan.GARGOYLE, 3),
    
    //Bloodline merits
    ASSAMITE(MeritType.BLOODLINE, Clan.ASSAMITE, Bloodline.ASSAMITE, 0),
    VIZIER(MeritType.BLOODLINE, Clan.ASSAMITE, Bloodline.VIZIER, 2),
    SORCERER(MeritType.BLOODLINE, Clan.ASSAMITE, Bloodline.SORCERER, 4),
    BRUJAH(MeritType.BLOODLINE, Clan.BRUJAH, Bloodline.BRUJAH, 0),
    TRUE_BRUJAH(MeritType.BLOODLINE, Clan.BRUJAH, Bloodline.TRUE_BRUJAH, 4),
    FOLLOWER_OF_SET(MeritType.BLOODLINE, Clan.FOLLOWER_OF_SET, Bloodline.FOLLOWER_OF_SET, 0),
    TLACLQUE(MeritType.BLOODLINE, Clan.FOLLOWER_OF_SET, Bloodline.TLACLQUE, 2),
    VIPER(MeritType.BLOODLINE, Clan.FOLLOWER_OF_SET, Bloodline.VIPER, 2),
    GANGREL(MeritType.BLOODLINE, Clan.GANGREL, Bloodline.GANGREL, 0),
    COYOTE(MeritType.BLOODLINE, Clan.GANGREL, Bloodline.COYOTE, 2),
    NOIAD(MeritType.BLOODLINE, Clan.GANGREL, Bloodline.NOIAD, 2),
    AHRIMANE(MeritType.BLOODLINE, Clan.GANGREL, Bloodline.AHRIMANE, 4),
    GIOVANNI(MeritType.BLOODLINE, Clan.GIOVANNI, Bloodline.GIOVANNI, 0),
    PREMASCINE(MeritType.BLOODLINE, Clan.GIOVANNI, Bloodline.PREMASCINE, 4),
    LASOMBRA(MeritType.BLOODLINE, Clan.LASOMBRA, Bloodline.LASOMBRA, 0),
    KISAYD(MeritType.BLOODLINE, Clan.LASOMBRA, Bloodline.KISAYD, 4),
    MALKAVIAN(MeritType.BLOODLINE, Clan.MALKAVIAN, Bloodline.MALKAVIAN, 0),
    ANANKE(MeritType.BLOODLINE, Clan.MALKAVIAN, Bloodline.ANANKE, 2),
    KNIGHT_OF_THE_MOON(MeritType.BLOODLINE, Clan.MALKAVIAN, Bloodline.KNIGHT_OF_THE_MOON, 2),
    NOSFERATU(MeritType.BLOODLINE, Clan.NOSFERATU, Bloodline.NOSFERATU, 0),
    TOREADOR(MeritType.BLOODLINE, Clan.TOREADOR, Bloodline.TOREADOR, 0),
    ISHTARRI(MeritType.BLOODLINE, Clan.TOREADOR, Bloodline.ISHTARRI, 2),
    VOLGIRRE(MeritType.BLOODLINE, Clan.TOREADOR, Bloodline.VOLGIRRE, 2),
    TREMERE(MeritType.BLOODLINE, Clan.TREMERE, Bloodline.TREMERE, 0),
    TELYAV(MeritType.BLOODLINE, Clan.TREMERE, Bloodline.TELYAV, 2),
    TZIMISCE(MeritType.BLOODLINE, Clan.TZIMISCE, Bloodline.TZIMISCE, 0),
    CARPATHIAN(MeritType.BLOODLINE, Clan.TZIMISCE, Bloodline.CARPATHIAN, 3),
    KOLDUN(MeritType.BLOODLINE, Clan.TZIMISCE, Bloodline.KOLDUN, 4),
    VENTRUE(MeritType.BLOODLINE, Clan.VENTRUE, Bloodline.VENTRUE, 0),
    CRUSADER(MeritType.BLOODLINE, Clan.VENTRUE, Bloodline.CRUSADER, 2),
    CATIFF(MeritType.BLOODLINE, Clan.CATIFF, Bloodline.CATIFF, 0),
    BAALI(MeritType.BLOODLINE, Clan.BAALI, Bloodline.BAALI, 0),
    ANGELLIS_ATER(MeritType.BLOODLINE, Clan.BAALI, Bloodline.ANGELLIS_ATER, 3),
    CAPPADOCIAN(MeritType.BLOODLINE, Clan.CAPPADOCIAN, Bloodline.CAPPADOCIAN, 0),
    SAMEDI(MeritType.BLOODLINE, Clan.CAPPADOCIAN, Bloodline.CAPPADOCIAN, 2),
    LAMIA(MeritType.BLOODLINE, Clan.CAPPADOCIAN, Bloodline.CAPPADOCIAN, 3),
    RAVNOS(MeritType.BLOODLINE, Clan.RAVNOS, Bloodline.RAVNOS, 0),
    BRAHMAN(MeritType.BLOODLINE, Clan.RAVNOS, Bloodline.BRAHMAN, 2),
    SALUBRI(MeritType.BLOODLINE, Clan.SALUBRI, Bloodline.SALUBRI, 0),
    HEALER(MeritType.BLOODLINE, Clan.SALUBRI, Bloodline.HEALER, 3),
    DAUGHTER_OF_CACOPHONY(MeritType.BLOODLINE, Clan.DAUGHTER_OF_CACOPHONY, Bloodline.DAUGHTER_OF_CACOPHONY, 0),
    GARGOYLE(MeritType.BLOODLINE, Clan.GARGOYLE, Bloodline.GARGOYLE, 0),
    
    //Setting specific merits
    ANTIQUITIES(MeritType.SETTING, Setting.CAMARILLA, 1),
    ARCHITECT_OF_THE_TOWER(MeritType.SETTING, Setting.CAMARILLA, 2),
    EMISSARY_TO_THE_CAMARILLA(MeritType.SETTING, Setting.CAMARILLA, 1),
    MACHIAVELLIAN_PRODIGY(MeritType.SETTING, Setting.CAMARILLA, 1),
    MASTER_OF_PUPPETS(MeritType.SETTING, Setting.CAMARILLA, 2),
    MONOPOLY(MeritType.SETTING, Setting.CAMARILLA, 1),
    PRESTIGIOUS_SIRE(MeritType.SETTING, Setting.CAMARILLA, 1),
    SOCIAL_NOBILITY(MeritType.SETTING, Setting.CAMARILLA, 3),
    BLACK_HAND_MEMBERSHIP(MeritType.SETTING, Setting.SABBAT, 2),
    EXECUTIONER(MeritType.SETTING, Setting.SABBAT, 1),
    FANATIC(MeritType.SETTING, Setting.SABBAT, 2),
    INQUISITION_MEMBERSHIP(MeritType.SETTING, Setting.SABBAT, 2),
    KEEPER_OF_A_SACRED_TEXT(MeritType.SETTING, Setting.SABBAT, 1),
    PACK_PLAYER(MeritType.SETTING, Setting.SABBAT, 3),
    REVELATOR(MeritType.SETTING, Setting.SABBAT, 3),
    SANCTIFIED(MeritType.SETTING, Setting.SABBAT, 1),
    SCHOLAR_OF_THE_SWORD(MeritType.SETTING, Setting.SABBAT, 1),
    VOLATILE(MeritType.SETTING, Setting.SABBAT, 1),
    ZEALOT(MeritType.SETTING, Setting.SABBAT, 1),
    DAUNTLESS(MeritType.SETTING, Setting.ANARCH, 2),
    DHAMPIR(MeritType.SETTING, Setting.ANARCH, 4),
    EAR_TO_THE_GROUND(MeritType.SETTING, Setting.ANARCH, 1),
    ELDER_OF_THE_REVOLUTION(MeritType.SETTING, Setting.ANARCH, 2),
    LION_OF_THE_CAUSE(MeritType.SETTING, Setting.ANARCH, 3),
    MONIKER(MeritType.SETTING, Setting.ANARCH, 1),
    MORAL_COMPASS(MeritType.SETTING, Setting.ANARCH, 1),
    OLD_DOG(MeritType.SETTING, Setting.ANARCH, 1),
    SORCEROUS_DABBLER(MeritType.SETTING, Setting.ANARCH, 2),
    TECH_JUNKIE(MeritType.SETTING, Setting.ANARCH, 2),
    WILD_ONE(MeritType.SETTING, Setting.ANARCH, 1);

    private final int points;
	private final MeritType type;
	private final Clan clan;
	private final Bloodline bloodline;
	private final Setting setting;
	
    private Merit(MeritType meritType, int points) {
    	this(meritType, points, null, null, null);
    }
    
    private Merit(MeritType meritType, Clan clan, int points) {
    	this(meritType, points, clan, null, null);
    }

	private Merit(MeritType meritType, Clan clan, Bloodline bloodline, int points) {
		this(meritType, points, clan, bloodline, null);
	}
	
	private Merit(MeritType meritType, Setting setting, int points) {
		this(meritType, points, null, null, setting);
	}
	
    private Merit(MeritType type, int points, Clan clan, Bloodline bloodline, Setting setting) {
        this.points = points;
		this.type = type;
		this.clan = clan;
		this.bloodline = bloodline;
		this.setting = setting;
    }
    
    public int getPoints() {
        return points;
    }
    
    public MeritType getType() {
		return type;
	}
    
    public Clan getClan() {
		return clan;
	}
    
    public Bloodline getBloodline() {
		return bloodline;
	}
    
    public Setting getSetting() {
		return setting;
	}
    
    public static List<Merit> general() {
    	return ofType(MeritType.GENERAL);
    }
    
    public static List<Merit> rarity() {
    	return ofType(MeritType.RARITY);
    }
    
    public static List<Merit> morality() {
    	return ofType(MeritType.MORALITY);
    }
    
    public static List<Merit> bloodline(Clan clan) {
    	return ofType(MeritType.BLOODLINE, clan);
    }
    
    public static List<Merit> clan(Clan clan) {
    	return ofType(MeritType.CLAN, clan);
    }
    
    public static List<Merit> setting(Setting setting) {
    	return ofType(MeritType.SETTING, setting);
    }
    
    private static Stream<Merit> meritStream() {
    	return Arrays.stream(Merit.values());
    }
    
    private static List<Merit> ofType(MeritType meritType) {
    	return meritStream().filter((Merit m) -> m.getType() == meritType).
    		                 collect(Collectors.toList());
    }
    
    private static List<Merit> ofType(MeritType meritType, Clan clan) {
    	return meritStream().filter((Merit m) -> m.getType() == meritType).
    			             filter((Merit m) -> m.getClan() == clan).
    			             collect(Collectors.toList());
    }
    
    private static List<Merit> ofType(MeritType meritType, Setting setting) {
    	return meritStream().filter((Merit m) -> m.getType() == meritType).
    			             filter((Merit m) -> m.getSetting() == setting).
    			             collect(Collectors.toList());
    }
}
