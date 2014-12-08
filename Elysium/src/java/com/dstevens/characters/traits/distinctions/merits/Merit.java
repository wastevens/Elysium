package com.dstevens.characters.traits.distinctions.merits;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dstevens.characters.clans.Bloodline;
import com.dstevens.characters.clans.Clan;
import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.SetTraitFactory;
import com.dstevens.characters.traits.TraitChangeStatus;
import com.dstevens.characters.traits.distinctions.DistinctionType;
import com.dstevens.players.Setting;

public enum Merit implements SetTraitFactory {
	//General merits
	ACUTE_SENSE(DistinctionType.GENERAL, 1),
    ADDITIONAL_COMMON_DISCIPLINE(DistinctionType.GENERAL, 4),
    ADDITIONAL_UNCOMMON_DISCIPLINE(DistinctionType.GENERAL, 5),
    AMBIDEXTEROUS(DistinctionType.GENERAL, 2),
    ARCANE(DistinctionType.GENERAL, 1),
    BLASE(DistinctionType.GENERAL, 3),
    CALM_HEART(DistinctionType.GENERAL, 1),
    CLEAR_SIGHTED(DistinctionType.GENERAL, 3),
    CODE_OF_HONOR(DistinctionType.GENERAL, 2),
    DAREDEVIL(DistinctionType.GENERAL, 2),
    EFFICIENT_DIGESTION(DistinctionType.GENERAL, 1),
    EFFICIENT_LEARNER(DistinctionType.GENERAL, 2),
    GOLCONDA_SEEKER(DistinctionType.GENERAL, 5),
    INFERNAL_POWER(DistinctionType.GENERAL, 3),
    INTENSE_VITALITY(DistinctionType.GENERAL, 3),
    IRON_WILL(DistinctionType.GENERAL, 3),
    LIGHT_SLEEPER(DistinctionType.GENERAL, 1),
    LOREMASTER(DistinctionType.GENERAL, 1),
    LUCKY(DistinctionType.GENERAL, 2),
    MAGIC_RESISTANCE(DistinctionType.GENERAL, 3),
    MEDIUM(DistinctionType.GENERAL, 1),
    NATURAL_LINGUIST(DistinctionType.GENERAL, 1),
    NECROMANTIC_TRAINING(DistinctionType.GENERAL, 5),
    ORACULAR_ABILITY(DistinctionType.GENERAL, 2),
    PERSONAL_MASQUERADE(DistinctionType.GENERAL, 1),
    REPUTATION(DistinctionType.GENERAL, 2),
    RUGGED(DistinctionType.GENERAL, 3),
    SKILL_APTITUDE(DistinctionType.GENERAL, 2),
    SLIPPERY_CUSTOMER(DistinctionType.GENERAL, 2),
    THAUMATURGIC_TRAINING(DistinctionType.GENERAL, 4),
    UNBONDABLE(DistinctionType.GENERAL, 4),
    UNYIELDING(DistinctionType.GENERAL, 4),
    VERSATILE(DistinctionType.GENERAL, 3),
    WHISPER_OF_LIFE(DistinctionType.GENERAL, 1),
    
    //Morality Merits
    PATH_OF_BLOOD(DistinctionType.MORALITY, Clan.ASSAMITE, 3),
    PATH_OF_CAINE(DistinctionType.MORALITY, 3),
    PATH_OF_CHIVALRY(DistinctionType.MORALITY, 3),
    PATH_OF_DEATH_AND_THE_SOUL(DistinctionType.MORALITY, Clan.GIOVANNI, 3),
    PATH_OF_ECSTASY(DistinctionType.MORALITY, Clan.FOLLOWER_OF_SET, 3),
    PATH_OF_EVIL_REVELATIONS(DistinctionType.MORALITY, 3),
    PATH_OF_THE_FERAL_HEART(DistinctionType.MORALITY, 3),
    PATH_OF_HEAVEN(DistinctionType.MORALITY, 3),
    PATH_OF_HARMONY(DistinctionType.MORALITY, 3),
    PATH_OF_HONORABLE_ACCORD(DistinctionType.MORALITY, 3),
    PATH_OF_LILITH(DistinctionType.MORALITY, 3),
    PATH_OF_METAMORPHOSIS(DistinctionType.MORALITY, Clan.TZIMISCE, 3),
    PATH_OF_NIGHT(DistinctionType.MORALITY, Clan.LASOMBRA, 3),
    PATH_OF_ORION(DistinctionType.MORALITY, 3),
    PATH_OF_PARADOX(DistinctionType.MORALITY, Clan.RAVNOS, 3),
    PATH_OF_POWER_AND_THE_INNER_VOICE(DistinctionType.MORALITY, 3),
    PATH_OF_THE_SCORCHED_HEART(DistinctionType.MORALITY, Clan.BRUJAH, Bloodline.TRUE_BRUJAH, 3),
    PATH_OF_TYPHON_SET(DistinctionType.MORALITY, Clan.FOLLOWER_OF_SET, Bloodline.VIPER, 3),
    
    //Rarity merits
    UNCOMMON(DistinctionType.GENERAL, 2),
    RARE(DistinctionType.GENERAL, 4),
    RESTRICTED(DistinctionType.GENERAL, 6),
	
    //Clan specific merits
    SURPRISE_ATTACK(DistinctionType.CLAN, Clan.ASSAMITE, 1),
    AWAKENING_THE_STEEL(DistinctionType.CLAN, Clan.ASSAMITE, 3),
    BROTHERHOOD(DistinctionType.CLAN, Clan.BRUJAH, 1),
    BURNING_WRATH(DistinctionType.CLAN, Clan.BRUJAH, 2),
    SCOURGE_OF_ALECTO(DistinctionType.CLAN, Clan.BRUJAH, 3),
    PERSONAL_CULT(DistinctionType.CLAN, Clan.FOLLOWER_OF_SET, 1),
    ADDICTIVE_BLOOD(DistinctionType.CLAN, Clan.FOLLOWER_OF_SET, 3),
    SETITE_SORCERY(DistinctionType.CLAN, Clan.FOLLOWER_OF_SET, 4),
    PROTEAN_BLOOD(DistinctionType.CLAN, Clan.GANGREL, 1),
    SHAPE_OF_BEASTS_WRATH(DistinctionType.CLAN, Clan.GANGREL, 3),
    NECROMANTIC_EXPERTISE(DistinctionType.CLAN, Clan.GIOVANNI, 1),
    MOOK(DistinctionType.CLAN, Clan.GIOVANNI, 2),
    GHOSTLY_RETAINER(DistinctionType.CLAN, Clan.GIOVANNI, 3),
    ANGELIC_VISAGE(DistinctionType.CLAN, Clan.LASOMBRA, 1),
    BORN_IN_SHADOW(DistinctionType.CLAN, Clan.LASOMBRA, 2),
    WALK_THE_ABYSS(DistinctionType.CLAN, Clan.LASOMBRA, 3),
    EXPANDED_CONSCIOUSNESS(DistinctionType.CLAN, Clan.MALKAVIAN, 1),
    LABYRINTHINE_MIND(DistinctionType.CLAN, Clan.MALKAVIAN, 3),
    SOPHISTRY(DistinctionType.CLAN, Clan.MALKAVIAN, 4),
    UNSEEING_EYE(DistinctionType.CLAN, Clan.NOSFERATU, 1),
    HIDDEN_ADVANTAGE(DistinctionType.CLAN, Clan.NOSFERATU, 2),
    PLIABLE_BLOOD(DistinctionType.CLAN, Clan.NOSFERATU, 3),
    UNNATURAL_ADAPTATION(DistinctionType.CLAN, Clan.NOSFERATU, 4),
    ARTISTS_BLESSING(DistinctionType.CLAN, Clan.TOREADOR, 1),
    ABSENT_SWAY(DistinctionType.CLAN, Clan.TOREADOR, 3),
    DANCERS_GRACE(DistinctionType.CLAN, Clan.TOREADOR, 4),
    THAUMATURGIC_EXPERTISE(DistinctionType.CLAN, Clan.TREMERE, 1),
    TALISMAN(DistinctionType.CLAN, Clan.TREMERE, 3),
    COUNTERMAGIC(DistinctionType.CLAN, Clan.TREMERE, 4),
    BLOOD_OF_THE_TZIMISCE(DistinctionType.CLAN, Clan.TZIMISCE, 1),
    SZLACHTA(DistinctionType.CLAN, Clan.TZIMISCE, 2),
    AURA_OF_COMMAND(DistinctionType.CLAN, Clan.VENTRUE, 1),
    PARAGON(DistinctionType.CLAN, Clan.VENTRUE, 3),
    REGAL_BEARING(DistinctionType.CLAN, Clan.VENTRUE, 4),
    AUSPICIOUS(DistinctionType.CLAN, Clan.CATIFF, 1),
    ECLIPSED_BLOOD(DistinctionType.CLAN, Clan.CATIFF, 2),
    VESTIGES_OF_GREATNESS(DistinctionType.CLAN, Clan.CATIFF, 3),
    INFERNAL_HERITAGE(DistinctionType.CLAN, Clan.BAALI, 1),
    NECROMANTIC_INSIGHT(DistinctionType.CLAN, Clan.CAPPADOCIAN, 1),
    PIERCED_SHROUD(DistinctionType.CLAN, Clan.CAPPADOCIAN, 3),
    WAKING_DREAM(DistinctionType.CLAN, Clan.RAVNOS, 1),
    ESCAPE_ARTIST(DistinctionType.CLAN, Clan.RAVNOS, 3),
    RIGHTEOUS_FURY(DistinctionType.CLAN, Clan.SALUBRI, 1),
    SPIRTUAL_ARMOR(DistinctionType.CLAN, Clan.SALUBRI, 2),
    SUPERNATURAL_ARIA(DistinctionType.CLAN, Clan.DAUGHTER_OF_CACOPHONY, 1),
    SOARING_OCTAVES(DistinctionType.CLAN, Clan.DAUGHTER_OF_CACOPHONY, 3),
    FLIGHT(DistinctionType.CLAN, Clan.GARGOYLE, 1),
    DARK_STATUE(DistinctionType.CLAN, Clan.GARGOYLE, 3),
    
    //Bloodline merits
    ASSAMITE(DistinctionType.BLOODLINE, Clan.ASSAMITE, Bloodline.ASSAMITE, 0),
    VIZIER(DistinctionType.BLOODLINE, Clan.ASSAMITE, Bloodline.VIZIER, 2),
    SORCERER(DistinctionType.BLOODLINE, Clan.ASSAMITE, Bloodline.SORCERER, 4),
    BRUJAH(DistinctionType.BLOODLINE, Clan.BRUJAH, Bloodline.BRUJAH, 0),
    TRUE_BRUJAH(DistinctionType.BLOODLINE, Clan.BRUJAH, Bloodline.TRUE_BRUJAH, 4),
    FOLLOWER_OF_SET(DistinctionType.BLOODLINE, Clan.FOLLOWER_OF_SET, Bloodline.FOLLOWER_OF_SET, 0),
    TLACLQUE(DistinctionType.BLOODLINE, Clan.FOLLOWER_OF_SET, Bloodline.TLACLQUE, 2),
    VIPER(DistinctionType.BLOODLINE, Clan.FOLLOWER_OF_SET, Bloodline.VIPER, 2),
    GANGREL(DistinctionType.BLOODLINE, Clan.GANGREL, Bloodline.GANGREL, 0),
    COYOTE(DistinctionType.BLOODLINE, Clan.GANGREL, Bloodline.COYOTE, 2),
    NOIAD(DistinctionType.BLOODLINE, Clan.GANGREL, Bloodline.NOIAD, 2),
    AHRIMANE(DistinctionType.BLOODLINE, Clan.GANGREL, Bloodline.AHRIMANE, 4),
    GIOVANNI(DistinctionType.BLOODLINE, Clan.GIOVANNI, Bloodline.GIOVANNI, 0),
    PREMASCINE(DistinctionType.BLOODLINE, Clan.GIOVANNI, Bloodline.PREMASCINE, 4),
    LASOMBRA(DistinctionType.BLOODLINE, Clan.LASOMBRA, Bloodline.LASOMBRA, 0),
    KISAYD(DistinctionType.BLOODLINE, Clan.LASOMBRA, Bloodline.KISAYD, 4),
    MALKAVIAN(DistinctionType.BLOODLINE, Clan.MALKAVIAN, Bloodline.MALKAVIAN, 0),
    ANANKE(DistinctionType.BLOODLINE, Clan.MALKAVIAN, Bloodline.ANANKE, 2),
    KNIGHT_OF_THE_MOON(DistinctionType.BLOODLINE, Clan.MALKAVIAN, Bloodline.KNIGHT_OF_THE_MOON, 2),
    NOSFERATU(DistinctionType.BLOODLINE, Clan.NOSFERATU, Bloodline.NOSFERATU, 0),
    TOREADOR(DistinctionType.BLOODLINE, Clan.TOREADOR, Bloodline.TOREADOR, 0),
    ISHTARRI(DistinctionType.BLOODLINE, Clan.TOREADOR, Bloodline.ISHTARRI, 2),
    VOLGIRRE(DistinctionType.BLOODLINE, Clan.TOREADOR, Bloodline.VOLGIRRE, 2),
    TREMERE(DistinctionType.BLOODLINE, Clan.TREMERE, Bloodline.TREMERE, 0),
    TELYAV(DistinctionType.BLOODLINE, Clan.TREMERE, Bloodline.TELYAV, 2),
    TZIMISCE(DistinctionType.BLOODLINE, Clan.TZIMISCE, Bloodline.TZIMISCE, 0),
    CARPATHIAN(DistinctionType.BLOODLINE, Clan.TZIMISCE, Bloodline.CARPATHIAN, 3),
    KOLDUN(DistinctionType.BLOODLINE, Clan.TZIMISCE, Bloodline.KOLDUN, 4),
    VENTRUE(DistinctionType.BLOODLINE, Clan.VENTRUE, Bloodline.VENTRUE, 0),
    CRUSADER(DistinctionType.BLOODLINE, Clan.VENTRUE, Bloodline.CRUSADER, 2),
    CATIFF(DistinctionType.BLOODLINE, Clan.CATIFF, Bloodline.CATIFF, 0),
    BAALI(DistinctionType.BLOODLINE, Clan.BAALI, Bloodline.BAALI, 0),
    ANGELLIS_ATER(DistinctionType.BLOODLINE, Clan.BAALI, Bloodline.ANGELLIS_ATER, 3),
    CAPPADOCIAN(DistinctionType.BLOODLINE, Clan.CAPPADOCIAN, Bloodline.CAPPADOCIAN, 0),
    SAMEDI(DistinctionType.BLOODLINE, Clan.CAPPADOCIAN, Bloodline.CAPPADOCIAN, 2),
    LAMIA(DistinctionType.BLOODLINE, Clan.CAPPADOCIAN, Bloodline.CAPPADOCIAN, 3),
    RAVNOS(DistinctionType.BLOODLINE, Clan.RAVNOS, Bloodline.RAVNOS, 0),
    BRAHMAN(DistinctionType.BLOODLINE, Clan.RAVNOS, Bloodline.BRAHMAN, 2),
    SALUBRI(DistinctionType.BLOODLINE, Clan.SALUBRI, Bloodline.SALUBRI, 0),
    HEALER(DistinctionType.BLOODLINE, Clan.SALUBRI, Bloodline.HEALER, 3),
    DAUGHTER_OF_CACOPHONY(DistinctionType.BLOODLINE, Clan.DAUGHTER_OF_CACOPHONY, Bloodline.DAUGHTER_OF_CACOPHONY, 0),
    GARGOYLE(DistinctionType.BLOODLINE, Clan.GARGOYLE, Bloodline.GARGOYLE, 0),
    
    //Setting specific merits
    ANTIQUITIES(DistinctionType.SETTING, Setting.CAMARILLA, 1),
    ARCHITECT_OF_THE_TOWER(DistinctionType.SETTING, Setting.CAMARILLA, 2),
    EMISSARY_TO_THE_CAMARILLA(DistinctionType.SETTING, Setting.CAMARILLA, 1),
    MACHIAVELLIAN_PRODIGY(DistinctionType.SETTING, Setting.CAMARILLA, 1),
    MASTER_OF_PUPPETS(DistinctionType.SETTING, Setting.CAMARILLA, 2),
    MONOPOLY(DistinctionType.SETTING, Setting.CAMARILLA, 1),
    PRESTIGIOUS_SIRE(DistinctionType.SETTING, Setting.CAMARILLA, 1),
    SOCIAL_NOBILITY(DistinctionType.SETTING, Setting.CAMARILLA, 3),
    BLACK_HAND_MEMBERSHIP(DistinctionType.SETTING, Setting.SABBAT, 2),
    EXECUTIONER(DistinctionType.SETTING, Setting.SABBAT, 1),
    FANATIC(DistinctionType.SETTING, Setting.SABBAT, 2),
    INQUISITION_MEMBERSHIP(DistinctionType.SETTING, Setting.SABBAT, 2),
    KEEPER_OF_A_SACRED_TEXT(DistinctionType.SETTING, Setting.SABBAT, 1),
    PACK_PLAYER(DistinctionType.SETTING, Setting.SABBAT, 3),
    REVELATOR(DistinctionType.SETTING, Setting.SABBAT, 3),
    SANCTIFIED(DistinctionType.SETTING, Setting.SABBAT, 1),
    SCHOLAR_OF_THE_SWORD(DistinctionType.SETTING, Setting.SABBAT, 1),
    VOLATILE(DistinctionType.SETTING, Setting.SABBAT, 1),
    ZEALOT(DistinctionType.SETTING, Setting.SABBAT, 1),
    DAUNTLESS(DistinctionType.SETTING, Setting.ANARCH, 2),
    DHAMPIR(DistinctionType.SETTING, Setting.ANARCH, 4),
    EAR_TO_THE_GROUND(DistinctionType.SETTING, Setting.ANARCH, 1),
    ELDER_OF_THE_REVOLUTION(DistinctionType.SETTING, Setting.ANARCH, 2),
    LION_OF_THE_CAUSE(DistinctionType.SETTING, Setting.ANARCH, 3),
    MONIKER(DistinctionType.SETTING, Setting.ANARCH, 1),
    MORAL_COMPASS(DistinctionType.SETTING, Setting.ANARCH, 1),
    OLD_DOG(DistinctionType.SETTING, Setting.ANARCH, 1),
    SORCEROUS_DABBLER(DistinctionType.SETTING, Setting.ANARCH, 2),
    TECH_JUNKIE(DistinctionType.SETTING, Setting.ANARCH, 2),
    WILD_ONE(DistinctionType.SETTING, Setting.ANARCH, 1);

    private final int points;
	private final DistinctionType type;
	private final Clan clan;
	private final Bloodline bloodline;
	private final Setting setting;
	
    private Merit(DistinctionType meritType, int points) {
    	this(meritType, points, null, null, null);
    }
    
    private Merit(DistinctionType meritType, Clan clan, int points) {
    	this(meritType, points, clan, null, null);
    }

	private Merit(DistinctionType meritType, Clan clan, Bloodline bloodline, int points) {
		this(meritType, points, clan, bloodline, null);
	}
	
	private Merit(DistinctionType meritType, Setting setting, int points) {
		this(meritType, points, null, null, setting);
	}
	
    private Merit(DistinctionType type, int points, Clan clan, Bloodline bloodline, Setting setting) {
        this.points = points;
		this.type = type;
		this.clan = clan;
		this.bloodline = bloodline;
		this.setting = setting;
    }
    
    public int getPoints() {
        return points;
    }
    
    public DistinctionType getType() {
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
    	return ofType(DistinctionType.GENERAL);
    }
    
    public static List<Merit> rarity() {
    	return ofType(DistinctionType.RARITY);
    }
    
    public static List<Merit> morality() {
    	return ofType(DistinctionType.MORALITY);
    }
    
    public static List<Merit> bloodline(Clan clan) {
    	return ofType(DistinctionType.BLOODLINE, clan);
    }
    
    public static List<Merit> clan(Clan clan) {
    	return ofType(DistinctionType.CLAN, clan);
    }
    
    public static List<Merit> setting(Setting setting) {
    	return ofType(DistinctionType.SETTING, setting);
    }
    
    private static Stream<Merit> meritStream() {
    	return Arrays.stream(Merit.values());
    }
    
    private static List<Merit> ofType(DistinctionType meritType) {
    	return meritStream().filter((Merit m) -> m.getType() == meritType).
    		                 collect(Collectors.toList());
    }
    
    private static List<Merit> ofType(DistinctionType meritType, Clan clan) {
    	return meritStream().filter((Merit m) -> m.getType() == meritType).
    			             filter((Merit m) -> m.getClan() == clan).
    			             collect(Collectors.toList());
    }
    
    private static List<Merit> ofType(DistinctionType meritType, Setting setting) {
    	return meritStream().filter((Merit m) -> m.getType() == meritType).
    			             filter((Merit m) -> m.getSetting() == setting).
    			             collect(Collectors.toList());
    }
    
    @Override
    public SetTrait set(TraitChangeStatus status, String specialization) {
    	return new SetMerit(status, this.ordinal(), specialization);
    }
}
