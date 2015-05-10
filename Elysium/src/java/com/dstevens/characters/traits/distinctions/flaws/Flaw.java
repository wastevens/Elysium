package com.dstevens.characters.traits.distinctions.flaws;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.Trait;
import com.dstevens.characters.traits.TraitQualities;
import com.dstevens.characters.traits.distinctions.DistinctionType;
import com.dstevens.players.Setting;

public enum Flaw implements Trait {

	ADDICTION(0, DistinctionType.GENERAL, 2),
    AMNESIA(1, DistinctionType.GENERAL, 1),
    ARCHAIC(2, DistinctionType.GENERAL, 2),
    AWKWARD_MOBILITY(3, DistinctionType.GENERAL, 2),
    BAD_SIGHT(4, DistinctionType.GENERAL, 2),
    BEACON_OF_THE_UNHOLY(5, DistinctionType.GENERAL, 3),
    BEAST_IN_THE_MIRROR(6, DistinctionType.GENERAL, 2),
    BESTIAL_INSTINCT(7, DistinctionType.GENERAL, 2),
    BLOOD_ROT(8, DistinctionType.GENERAL, 5),
    BOUND_TO_THE_EARTH(9, DistinctionType.GENERAL, 2),
    BLUNTED_FANGS(10, DistinctionType.GENERAL, 2),
    CANNOT_CROSS_RUNNING_WATER(11, DistinctionType.GENERAL, 2),
    CARELESS(12, DistinctionType.GENERAL, 1),
    CHILDLIKE(13, DistinctionType.GENERAL, 4),
    CONSPICIOUS_CONSUMPTION(14, DistinctionType.GENERAL, 3),
    CURIOUSITY(15, DistinctionType.GENERAL, 2),
    CURSED(16, DistinctionType.GENERAL, 1),
    DARK_FATE(17, DistinctionType.GENERAL, 5),
    DARK_SECRET(18, DistinctionType.GENERAL, 1),
    DEATH_SIGHT(19, DistinctionType.GENERAL, 2),
    DEEP_SLEEPER(20, DistinctionType.GENERAL, 2),
    DISEASE_CARRIER(21, DistinctionType.GENERAL, 4),
    DULL(22, DistinctionType.GENERAL, 2),
    EERIE_PRESENCE(23, DistinctionType.GENERAL, 1),
    FLESH_OF_THE_CORPSE(24, DistinctionType.GENERAL, 5),
    FRAGILE_BONES(25, DistinctionType.GENERAL, 4),
    GEHENNA_PROPHET(26, DistinctionType.GENERAL, 2),
    GRIP_OF_THE_DAMNED(27, DistinctionType.GENERAL, 2),
    HARD_OF_HEARING(28, DistinctionType.GENERAL, 2),
    HAUNTED(29, DistinctionType.GENERAL, 1),
    HUNTED(30, DistinctionType.GENERAL, 4),
    INFAMOUS_BROOD(31, DistinctionType.GENERAL, 3),
    ILLITERATE(32, DistinctionType.GENERAL, 1),
    IMPATIENT(33, DistinctionType.GENERAL, 2),
    INEPT(34, DistinctionType.GENERAL, 3),
    INTOLERANCE(35, DistinctionType.GENERAL, 1),
    KNOWN_TO_BE_DEAD(36, DistinctionType.GENERAL, 1),
    LESSER_GENERATION_1(37, DistinctionType.GENERAL, 1),
    LESSER_GENERATION_2(38, DistinctionType.GENERAL, 2),
    LOW_PAIN_THRESHOLD(39, DistinctionType.GENERAL, 3),
    MAGIC_SUSCEPTIBILITY(40, DistinctionType.GENERAL, 2),
    METHUSELAHS_THIRST(41, DistinctionType.GENERAL, 4),
    NIGHTMARES(42, DistinctionType.GENERAL, 1),
    NECROPHILE(43, DistinctionType.GENERAL, 1),
    NOTORIETY(44, DistinctionType.GENERAL, 2),
    OBVIOUS_PREDATOR(45, DistinctionType.GENERAL, 2),
    ONE_EYE(46, DistinctionType.GENERAL, 3),
    OVERCONFIDENT(47, DistinctionType.GENERAL, 2),
    PERMANENT_WOUND(48, DistinctionType.GENERAL, 4),
    POSEIDONS_CALL(49, DistinctionType.GENERAL, 3),
    PIED_PIPER(50, DistinctionType.GENERAL, 1),
    PREY_EXCLUSION(51, DistinctionType.GENERAL, 1),
    REPELLED_BY_RELIGION(52, DistinctionType.GENERAL, 3),
    SHORT_ATTENTION_SPAN(53, DistinctionType.GENERAL, 4),
    SHORT_FUSE(54, DistinctionType.GENERAL, 2),
    SLOW_HEALING(55, DistinctionType.GENERAL, 3),
    SLOW_REACTIONS(56, DistinctionType.GENERAL, 3),
    STOLEN_POTENTIAL(57, DistinctionType.GENERAL, 3),
    THIN_BLOODED(58, DistinctionType.GENERAL, 3),
    THIRST_FOR_INNOCENCE(59, DistinctionType.GENERAL, 1),
    TROUBLE_MAGNET(60, DistinctionType.GENERAL, 1),
    VULERABLE_TO_SILVER(61, DistinctionType.GENERAL, 2),
    WEAK_WILLED(62, DistinctionType.GENERAL, 3),
    WEAK_STOMACH(63, DistinctionType.GENERAL, 3),
    
    ACCUSED_OF_HERESY(64, DistinctionType.SETTING, Setting.CAMARILLA, 2),
    FORGIVEN_DIABLERIE(65, DistinctionType.SETTING, Setting.CAMARILLA, 1),
    SECTARIAN(66, DistinctionType.SETTING, Setting.CAMARILLA, 2),
    TAINTED_EMBRACE(67, DistinctionType.SETTING, Setting.CAMARILLA, 2),
    UNTRAINED_COMBATANT(68, DistinctionType.SETTING, Setting.CAMARILLA, 3),
    
    CRISIS_OF_FAITH(69, DistinctionType.SETTING, Setting.SABBAT, 2),
    MISTRUSTED(70, DistinctionType.SETTING, Setting.SABBAT, 1),
    SOUL_SHARD(71, DistinctionType.SETTING, Setting.SABBAT, 3),
    TENUOUS_LOYALTY(72, DistinctionType.SETTING, Setting.SABBAT, 3),
    
    BASTARD_CHILDE(73, DistinctionType.SETTING, Setting.ANARCH, 2),
    BLACK_SHEEP(74, DistinctionType.SETTING, Setting.ANARCH, 2),
    DUBIOUS_LOYALTIES(75, DistinctionType.SETTING, Setting.ANARCH, 2),
    ESCAPED_SHOVELHEAD(76, DistinctionType.SETTING, Setting.ANARCH, 2),
    ONCE_ENSLAVED(77, DistinctionType.SETTING, Setting.ANARCH, 2);
	
	private final int id;
    private final int points;
	private final DistinctionType type;
	private final Setting setting;
	
    private Flaw(int id, DistinctionType distinctionType, int points) {
    	this(id, distinctionType, points, null);
    }
    
    private Flaw(int id, DistinctionType distinctionType, Setting setting, int points) {
    	this(id, distinctionType, points, setting);
    }
	
    private Flaw(int id, DistinctionType type, int points, Setting setting) {
        this.id = id;
		this.points = points;
		this.type = type;
		this.setting = setting;
    }
	
	@Override
	public int id() {
		return id;
	}
    
    public int getPoints() {
        return points;
    }
    
    public DistinctionType getType() {
		return type;
	}
    
    public Setting getSetting() {
		return setting;
	}
    
    public static List<Flaw> general() {
    	return ofType(DistinctionType.GENERAL);
    }
    
    public static List<Flaw> setting(Setting setting) {
    	return ofType(DistinctionType.SETTING, setting);
    }
    
    private static Stream<Flaw> flawStream() {
    	return Arrays.stream(Flaw.values());
    }
    
    private static List<Flaw> ofType(DistinctionType flawType) {
    	return flawStream().filter((Flaw f) -> f.getType() == flawType).
    		                 collect(Collectors.toList());
    }
    
    private static List<Flaw> ofType(DistinctionType flawType, Setting setting) {
    	return flawStream().filter((Flaw f) -> f.getType() == flawType).
    			             filter((Flaw f) -> f.getSetting() == setting).
    			             collect(Collectors.toList());
    }

	@Override
	public ApplicableTrait applyWith(TraitQualities qualities) {
		return new CharacterFlaw(this, qualities.specialization);
	}
	
}
