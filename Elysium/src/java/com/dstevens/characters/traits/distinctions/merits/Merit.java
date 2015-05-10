package com.dstevens.characters.traits.distinctions.merits;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dstevens.characters.clans.Bloodline;
import com.dstevens.characters.clans.Clan;
import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.Trait;
import com.dstevens.characters.traits.TraitQualities;
import com.dstevens.characters.traits.distinctions.DistinctionType;
import com.dstevens.players.Setting;

public enum Merit implements Trait {
	//General merits
	ACUTE_SENSE(0, DistinctionType.GENERAL, 1),
    ADDITIONAL_COMMON_DISCIPLINE(1, DistinctionType.GENERAL, 4),
    ADDITIONAL_UNCOMMON_DISCIPLINE(2, DistinctionType.GENERAL, 5),
    AMBIDEXTEROUS(3, DistinctionType.GENERAL, 2),
    ARCANE(4, DistinctionType.GENERAL, 1),
    BLASE(5, DistinctionType.GENERAL, 3),
    CALM_HEART(6, DistinctionType.GENERAL, 1),
    CLEAR_SIGHTED(7, DistinctionType.GENERAL, 3),
    CODE_OF_HONOR(8, DistinctionType.GENERAL, 2),
    DAREDEVIL(9, DistinctionType.GENERAL, 2),
    EFFICIENT_DIGESTION(10, DistinctionType.GENERAL, 1),
    EFFICIENT_LEARNER(11, DistinctionType.GENERAL, 2),
    GOLCONDA_SEEKER(12, DistinctionType.GENERAL, 5),
    INFERNAL_POWER(13, DistinctionType.GENERAL, 3),
    INTENSE_VITALITY(14, DistinctionType.GENERAL, 3),
    IRON_WILL(15, DistinctionType.GENERAL, 3),
    LIGHT_SLEEPER(16, DistinctionType.GENERAL, 1),
    LOREMASTER(17, DistinctionType.GENERAL, 1),
    LUCKY(18, DistinctionType.GENERAL, 2),
    MAGIC_RESISTANCE(19, DistinctionType.GENERAL, 3),
    MEDIUM(20, DistinctionType.GENERAL, 1),
    NATURAL_LINGUIST(21, DistinctionType.GENERAL, 1),
    NECROMANTIC_TRAINING(22, DistinctionType.GENERAL, 5),
    ORACULAR_ABILITY(23, DistinctionType.GENERAL, 2),
    PERSONAL_MASQUERADE(24, DistinctionType.GENERAL, 1),
    REPUTATION(25, DistinctionType.GENERAL, 2),
    RUGGED(26, DistinctionType.GENERAL, 3),
    SKILL_APTITUDE(27, DistinctionType.GENERAL, 2),
    SLIPPERY_CUSTOMER(28, DistinctionType.GENERAL, 2),
    THAUMATURGIC_TRAINING(29, DistinctionType.GENERAL, 4),
    UNBONDABLE(30, DistinctionType.GENERAL, 4),
    UNYIELDING(31, DistinctionType.GENERAL, 4),
    VERSATILE(32, DistinctionType.GENERAL, 3),
    WHISPER_OF_LIFE(33, DistinctionType.GENERAL, 1),
    
    //Morality Merits
    PATH_OF_BLOOD(34, DistinctionType.MORALITY, Clan.ASSAMITE, 3),
    PATH_OF_CAINE(35, DistinctionType.MORALITY, 3),
    PATH_OF_CHIVALRY(36, DistinctionType.MORALITY, 3),
    PATH_OF_DEATH_AND_THE_SOUL(37, DistinctionType.MORALITY, Clan.GIOVANNI, 3),
    PATH_OF_ECSTASY(38, DistinctionType.MORALITY, Clan.FOLLOWER_OF_SET, 3),
    PATH_OF_EVIL_REVELATIONS(39, DistinctionType.MORALITY, 3),
    PATH_OF_THE_FERAL_HEART(40, DistinctionType.MORALITY, 3),
    PATH_OF_HEAVEN(41, DistinctionType.MORALITY, 3),
    PATH_OF_HARMONY(42, DistinctionType.MORALITY, 3),
    PATH_OF_HONORABLE_ACCORD(43, DistinctionType.MORALITY, 3),
    PATH_OF_LILITH(44, DistinctionType.MORALITY, 3),
    PATH_OF_METAMORPHOSIS(45, DistinctionType.MORALITY, Clan.TZIMISCE, 3),
    PATH_OF_NIGHT(46, DistinctionType.MORALITY, Clan.LASOMBRA, 3),
    PATH_OF_ORION(47, DistinctionType.MORALITY, 3),
    PATH_OF_PARADOX(48, DistinctionType.MORALITY, Clan.RAVNOS, 3),
    PATH_OF_POWER_AND_THE_INNER_VOICE(49, DistinctionType.MORALITY, 3),
    PATH_OF_THE_SCORCHED_HEART(50, DistinctionType.MORALITY, Clan.BRUJAH, Bloodline.TRUE_BRUJAH, 3),
    PATH_OF_TYPHON_SET(51, DistinctionType.MORALITY, Clan.FOLLOWER_OF_SET, Bloodline.VIPER, 3),
    
    //Rarity merits
    UNCOMMON(52, DistinctionType.GENERAL, 2),
    RARE(53, DistinctionType.GENERAL, 4),
    RESTRICTED(54, DistinctionType.GENERAL, 6),
	
    //Clan specific merits
    SURPRISE_ATTACK(55, DistinctionType.CLAN, Clan.ASSAMITE, 1),
    AWAKENING_THE_STEEL(56, DistinctionType.CLAN, Clan.ASSAMITE, 3),
    
    BROTHERHOOD(57, DistinctionType.CLAN, Clan.BRUJAH, 1),
    BURNING_WRATH(58, DistinctionType.CLAN, Clan.BRUJAH, 2),
    SCOURGE_OF_ALECTO(59, DistinctionType.CLAN, Clan.BRUJAH, 3),
    
    PERSONAL_CULT(60, DistinctionType.CLAN, Clan.FOLLOWER_OF_SET, 1),
    ADDICTIVE_BLOOD(61, DistinctionType.CLAN, Clan.FOLLOWER_OF_SET, 3),
    SETITE_SORCERY(62, DistinctionType.CLAN, Clan.FOLLOWER_OF_SET, 4),
    
    PROTEAN_BLOOD(63, DistinctionType.CLAN, Clan.GANGREL, 1),
    SHAPE_OF_BEASTS_WRATH(64, DistinctionType.CLAN, Clan.GANGREL, 3),
    
    NECROMANTIC_EXPERTISE(65, DistinctionType.CLAN, Clan.GIOVANNI, 1),
    MOOK(66, DistinctionType.CLAN, Clan.GIOVANNI, 2),
    GHOSTLY_RETAINER(67, DistinctionType.CLAN, Clan.GIOVANNI, 3),
    
    ANGELIC_VISAGE(68, DistinctionType.CLAN, Clan.LASOMBRA, 1),
    BORN_IN_SHADOW(69, DistinctionType.CLAN, Clan.LASOMBRA, 2),
    WALK_THE_ABYSS(70, DistinctionType.CLAN, Clan.LASOMBRA, 3),
    
    EXPANDED_CONSCIOUSNESS(71, DistinctionType.CLAN, Clan.MALKAVIAN, 1),
    LABYRINTHINE_MIND(72, DistinctionType.CLAN, Clan.MALKAVIAN, 3),
    SOPHISTRY(73, DistinctionType.CLAN, Clan.MALKAVIAN, 4),
    
    UNSEEING_EYE(74, DistinctionType.CLAN, Clan.NOSFERATU, 1),
    HIDDEN_ADVANTAGE(75, DistinctionType.CLAN, Clan.NOSFERATU, 2),
    PLIABLE_BLOOD(76, DistinctionType.CLAN, Clan.NOSFERATU, 3),
    UNNATURAL_ADAPTATION(77, DistinctionType.CLAN, Clan.NOSFERATU, 4),
    
    ARTISTS_BLESSING(78, DistinctionType.CLAN, Clan.TOREADOR, 1),
    ABSENT_SWAY(79, DistinctionType.CLAN, Clan.TOREADOR, 3),
    DANCERS_GRACE(80, DistinctionType.CLAN, Clan.TOREADOR, 4),
    
    THAUMATURGIC_EXPERTISE(81, DistinctionType.CLAN, Clan.TREMERE, 1),
    TALISMAN(82, DistinctionType.CLAN, Clan.TREMERE, 3),
    COUNTERMAGIC(83, DistinctionType.CLAN, Clan.TREMERE, 4),
    
    BLOOD_OF_THE_TZIMISCE(84, DistinctionType.CLAN, Clan.TZIMISCE, 1),
    SZLACHTA(85, DistinctionType.CLAN, Clan.TZIMISCE, 2),
    
    AURA_OF_COMMAND(86, DistinctionType.CLAN, Clan.VENTRUE, 1),
    PARAGON(87, DistinctionType.CLAN, Clan.VENTRUE, 3),
    REGAL_BEARING(88, DistinctionType.CLAN, Clan.VENTRUE, 4),
    
    AUSPICIOUS(89, DistinctionType.CLAN, Clan.CATIFF, 1),
    ECLIPSED_BLOOD(90, DistinctionType.CLAN, Clan.CATIFF, 2),
    VESTIGES_OF_GREATNESS(91, DistinctionType.CLAN, Clan.CATIFF, 3),
    
    INFERNAL_HERITAGE(92, DistinctionType.CLAN, Clan.BAALI, 1),
    
    NECROMANTIC_INSIGHT(93, DistinctionType.CLAN, Clan.CAPPADOCIAN, 1),
    PIERCED_SHROUD(94, DistinctionType.CLAN, Clan.CAPPADOCIAN, 3),
    
    WAKING_DREAM(95, DistinctionType.CLAN, Clan.RAVNOS, 1),
    ESCAPE_ARTIST(96, DistinctionType.CLAN, Clan.RAVNOS, 3),
    
    RIGHTEOUS_FURY(97, DistinctionType.CLAN, Clan.SALUBRI, 1),
    SPIRTUAL_ARMOR(98, DistinctionType.CLAN, Clan.SALUBRI, 2),
    
    SUPERNATURAL_ARIA(99, DistinctionType.CLAN, Clan.DAUGHTER_OF_CACOPHONY, 1),
    SOARING_OCTAVES(100, DistinctionType.CLAN, Clan.DAUGHTER_OF_CACOPHONY, 3),
    
    FLIGHT(101, DistinctionType.CLAN, Clan.GARGOYLE, 1),
    DARK_STATUE(102, DistinctionType.CLAN, Clan.GARGOYLE, 3),
    
    //Bloodline merits
    ASSAMITE(103, DistinctionType.BLOODLINE, Clan.ASSAMITE, Bloodline.ASSAMITE, 0),
    VIZIER(104, DistinctionType.BLOODLINE, Clan.ASSAMITE, Bloodline.VIZIER, 2),
    SORCERER(105, DistinctionType.BLOODLINE, Clan.ASSAMITE, Bloodline.SORCERER, 4),
    BRUJAH(106, DistinctionType.BLOODLINE, Clan.BRUJAH, Bloodline.BRUJAH, 0),
    TRUE_BRUJAH(107, DistinctionType.BLOODLINE, Clan.BRUJAH, Bloodline.TRUE_BRUJAH, 4),
    FOLLOWER_OF_SET(108, DistinctionType.BLOODLINE, Clan.FOLLOWER_OF_SET, Bloodline.FOLLOWER_OF_SET, 0),
    TLACLQUE(109, DistinctionType.BLOODLINE, Clan.FOLLOWER_OF_SET, Bloodline.TLACLQUE, 2),
    VIPER(110, DistinctionType.BLOODLINE, Clan.FOLLOWER_OF_SET, Bloodline.VIPER, 2),
    GANGREL(111, DistinctionType.BLOODLINE, Clan.GANGREL, Bloodline.GANGREL, 0),
    COYOTE(112, DistinctionType.BLOODLINE, Clan.GANGREL, Bloodline.COYOTE, 2),
    NOIAD(113, DistinctionType.BLOODLINE, Clan.GANGREL, Bloodline.NOIAD, 2),
    AHRIMANE(114, DistinctionType.BLOODLINE, Clan.GANGREL, Bloodline.AHRIMANE, 4),
    GIOVANNI(115, DistinctionType.BLOODLINE, Clan.GIOVANNI, Bloodline.GIOVANNI, 0),
    PREMASCINE(116, DistinctionType.BLOODLINE, Clan.GIOVANNI, Bloodline.PREMASCINE, 4),
    LASOMBRA(117, DistinctionType.BLOODLINE, Clan.LASOMBRA, Bloodline.LASOMBRA, 0),
    KISAYD(118, DistinctionType.BLOODLINE, Clan.LASOMBRA, Bloodline.KISAYD, 4),
    MALKAVIAN(119, DistinctionType.BLOODLINE, Clan.MALKAVIAN, Bloodline.MALKAVIAN, 0),
    ANANKE(120, DistinctionType.BLOODLINE, Clan.MALKAVIAN, Bloodline.ANANKE, 2),
    KNIGHT_OF_THE_MOON(121, DistinctionType.BLOODLINE, Clan.MALKAVIAN, Bloodline.KNIGHT_OF_THE_MOON, 2),
    NOSFERATU(122, DistinctionType.BLOODLINE, Clan.NOSFERATU, Bloodline.NOSFERATU, 0),
    TOREADOR(123, DistinctionType.BLOODLINE, Clan.TOREADOR, Bloodline.TOREADOR, 0),
    ISHTARRI(124, DistinctionType.BLOODLINE, Clan.TOREADOR, Bloodline.ISHTARRI, 2),
    VOLGIRRE(125, DistinctionType.BLOODLINE, Clan.TOREADOR, Bloodline.VOLGIRRE, 2),
    TREMERE(126, DistinctionType.BLOODLINE, Clan.TREMERE, Bloodline.TREMERE, 0),
    TELYAV(127, DistinctionType.BLOODLINE, Clan.TREMERE, Bloodline.TELYAV, 2),
    TZIMISCE(128, DistinctionType.BLOODLINE, Clan.TZIMISCE, Bloodline.TZIMISCE, 0),
    CARPATHIAN(129, DistinctionType.BLOODLINE, Clan.TZIMISCE, Bloodline.CARPATHIAN, 3),
    KOLDUN(130, DistinctionType.BLOODLINE, Clan.TZIMISCE, Bloodline.KOLDUN, 4),
    VENTRUE(131, DistinctionType.BLOODLINE, Clan.VENTRUE, Bloodline.VENTRUE, 0),
    CRUSADER(132, DistinctionType.BLOODLINE, Clan.VENTRUE, Bloodline.CRUSADER, 2),
    CATIFF(133, DistinctionType.BLOODLINE, Clan.CATIFF, Bloodline.CATIFF, 0),
    BAALI(134, DistinctionType.BLOODLINE, Clan.BAALI, Bloodline.BAALI, 0),
    ANGELLIS_ATER(135, DistinctionType.BLOODLINE, Clan.BAALI, Bloodline.ANGELLIS_ATER, 3),
    CAPPADOCIAN(136, DistinctionType.BLOODLINE, Clan.CAPPADOCIAN, Bloodline.CAPPADOCIAN, 0),
    SAMEDI(137, DistinctionType.BLOODLINE, Clan.CAPPADOCIAN, Bloodline.SAMEDI, 2),
    LAMIA(138, DistinctionType.BLOODLINE, Clan.CAPPADOCIAN, Bloodline.LAMIA, 3),
    RAVNOS(139, DistinctionType.BLOODLINE, Clan.RAVNOS, Bloodline.RAVNOS, 0),
    BRAHMAN(140, DistinctionType.BLOODLINE, Clan.RAVNOS, Bloodline.BRAHMAN, 2),
    SALUBRI(141, DistinctionType.BLOODLINE, Clan.SALUBRI, Bloodline.SALUBRI, 0),
    HEALER(142, DistinctionType.BLOODLINE, Clan.SALUBRI, Bloodline.HEALER, 3),
    DAUGHTER_OF_CACOPHONY(143, DistinctionType.BLOODLINE, Clan.DAUGHTER_OF_CACOPHONY, Bloodline.DAUGHTER_OF_CACOPHONY, 0),
    GARGOYLE(144, DistinctionType.BLOODLINE, Clan.GARGOYLE, Bloodline.GARGOYLE, 0),
    
    //Setting specific merits
    ANTIQUITIES(145, DistinctionType.SETTING, Setting.CAMARILLA, 1),
    ARCHITECT_OF_THE_TOWER(146, DistinctionType.SETTING, Setting.CAMARILLA, 2),
    EMISSARY_TO_THE_CAMARILLA(147, DistinctionType.SETTING, Setting.CAMARILLA, 1),
    MACHIAVELLIAN_PRODIGY(148, DistinctionType.SETTING, Setting.CAMARILLA, 1),
    MASTER_OF_PUPPETS(149, DistinctionType.SETTING, Setting.CAMARILLA, 2),
    MONOPOLY(150, DistinctionType.SETTING, Setting.CAMARILLA, 1),
    PRESTIGIOUS_SIRE(151, DistinctionType.SETTING, Setting.CAMARILLA, 1),
    SOCIAL_NOBILITY(152, DistinctionType.SETTING, Setting.CAMARILLA, 3),
    
    BLACK_HAND_MEMBERSHIP(153, DistinctionType.SETTING, Setting.SABBAT, 2),
    EXECUTIONER(154, DistinctionType.SETTING, Setting.SABBAT, 1),
    FANATIC(155, DistinctionType.SETTING, Setting.SABBAT, 2),
    INQUISITION_MEMBERSHIP(156, DistinctionType.SETTING, Setting.SABBAT, 2),
    KEEPER_OF_A_SACRED_TEXT(157, DistinctionType.SETTING, Setting.SABBAT, 1),
    PACK_PLAYER(158, DistinctionType.SETTING, Setting.SABBAT, 3),
    REVELATOR(159, DistinctionType.SETTING, Setting.SABBAT, 3),
    SANCTIFIED(160, DistinctionType.SETTING, Setting.SABBAT, 1),
    SCHOLAR_OF_THE_SWORD(161, DistinctionType.SETTING, Setting.SABBAT, 1),
    VOLATILE(162, DistinctionType.SETTING, Setting.SABBAT, 1),
    ZEALOT(163, DistinctionType.SETTING, Setting.SABBAT, 1),
    
    DAUNTLESS(164, DistinctionType.SETTING, Setting.ANARCH, 2),
    DHAMPIR(165, DistinctionType.SETTING, Setting.ANARCH, 4),
    EAR_TO_THE_GROUND(166, DistinctionType.SETTING, Setting.ANARCH, 1),
    ELDER_OF_THE_REVOLUTION(167, DistinctionType.SETTING, Setting.ANARCH, 2),
    LION_OF_THE_CAUSE(168, DistinctionType.SETTING, Setting.ANARCH, 3),
    MONIKER(169, DistinctionType.SETTING, Setting.ANARCH, 1),
    MORAL_COMPASS(170, DistinctionType.SETTING, Setting.ANARCH, 1),
    OLD_DOG(171, DistinctionType.SETTING, Setting.ANARCH, 1),
    SORCEROUS_DABBLER(172, DistinctionType.SETTING, Setting.ANARCH, 2),
    TECH_JUNKIE(173, DistinctionType.SETTING, Setting.ANARCH, 2),
    WILD_ONE(174, DistinctionType.SETTING, Setting.ANARCH, 1);

	private final int id;
    private final int points;
	private final DistinctionType type;
	private final Clan clan;
	private final Bloodline bloodline;
	private final Setting setting;
	
    private Merit(int id, DistinctionType meritType, int points) {
    	this(id, meritType, points, null, null, null);
    }
    
    private Merit(int id, DistinctionType meritType, Clan clan, int points) {
    	this(id, meritType, points, clan, null, null);
    }

	private Merit(int id, DistinctionType meritType, Clan clan, Bloodline bloodline, int points) {
		this(id, meritType, points, clan, bloodline, null);
	}
	
	private Merit(int id, DistinctionType meritType, Setting setting, int points) {
		this(id, meritType, points, null, null, setting);
	}
	
    private Merit(int id, DistinctionType type, int points, Clan clan, Bloodline bloodline, Setting setting) {
        this.id = id;
		this.points = points;
		this.type = type;
		this.clan = clan;
		this.bloodline = bloodline;
		this.setting = setting;
    }
    
	@Override
	public Integer getId() {
		return id;
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
	public ApplicableTrait applyWith(TraitQualities qualities) {
		return new CharacterMerit(this, qualities.specialization);
	}
}
