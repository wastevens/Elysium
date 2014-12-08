package com.dstevens.characters.traits.distinctions.flaws;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dstevens.characters.traits.changes.TraitChange;
import com.dstevens.characters.traits.changes.TraitChangeStatus;
import com.dstevens.characters.traits.distinctions.DistinctionType;
import com.dstevens.players.Setting;

public enum Flaw {

	ADDICTION(DistinctionType.GENERAL, 2),
    AMNESIA(DistinctionType.GENERAL, 1),
    ARCHAIC(DistinctionType.GENERAL, 2),
    AWKWARD_MOBILITY(DistinctionType.GENERAL, 2),
    BAD_SIGHT(DistinctionType.GENERAL, 2),
    BEACON_OF_THE_UNHOLY(DistinctionType.GENERAL, 3),
    BEAST_IN_THE_MIRROR(DistinctionType.GENERAL, 2),
    BESTIAL_INSTINCT(DistinctionType.GENERAL, 2),
    BLOOD_ROT(DistinctionType.GENERAL, 5),
    BOUND_TO_THE_EARTH(DistinctionType.GENERAL, 2),
    BLUNTED_FANGS(DistinctionType.GENERAL, 2),
    CANNOT_CROSS_RUNNING_WATER(DistinctionType.GENERAL, 2),
    CARELESS(DistinctionType.GENERAL, 1),
    CHILDLIKE(DistinctionType.GENERAL, 4),
    CONSPICIOUS_CONSUMPTION(DistinctionType.GENERAL, 3),
    CURIOUSITY(DistinctionType.GENERAL, 2),
    CURSED(DistinctionType.GENERAL, 1),
    DARK_FATE(DistinctionType.GENERAL, 5),
    DARK_SECRET(DistinctionType.GENERAL, 1),
    DEATH_SIGHT(DistinctionType.GENERAL, 2),
    DEEP_SLEEPER(DistinctionType.GENERAL, 2),
    DISEASE_CARRIER(DistinctionType.GENERAL, 4),
    DULL(DistinctionType.GENERAL, 2),
    EERIE_PRESENCE(DistinctionType.GENERAL, 1),
    FLESH_OF_THE_CORPSE(DistinctionType.GENERAL, 5),
    FRAGILE_BONES(DistinctionType.GENERAL, 4),
    GEHENNA_PROPHET(DistinctionType.GENERAL, 2),
    GRIP_OF_THE_DAMNED(DistinctionType.GENERAL, 2),
    HARD_OF_HEARING(DistinctionType.GENERAL, 2),
    HAUNTED(DistinctionType.GENERAL, 1),
    HUNTED(DistinctionType.GENERAL, 4),
    INFAMOUS_BROOD(DistinctionType.GENERAL, 3),
    ILLITERATE(DistinctionType.GENERAL, 1),
    IMPATIENT(DistinctionType.GENERAL, 2),
    INEPT(DistinctionType.GENERAL, 3),
    INTOLERANCE(DistinctionType.GENERAL, 1),
    KNOWN_TO_BE_DEAD(DistinctionType.GENERAL, 1),
    LESSER_GENERATION_1(DistinctionType.GENERAL, 1),
    LESSER_GENERATION_2(DistinctionType.GENERAL, 2),
    LOW_PAIN_THRESHOLD(DistinctionType.GENERAL, 3),
    MAGIC_SUSCEPTIBILITY(DistinctionType.GENERAL, 2),
    METHUSELAHS_THIRST(DistinctionType.GENERAL, 4),
    NIGHTMARES(DistinctionType.GENERAL, 1),
    NECROPHILE(DistinctionType.GENERAL, 1),
    NOTORIETY(DistinctionType.GENERAL, 2),
    OBVIOUS_PREDATOR(DistinctionType.GENERAL, 2),
    ONE_EYE(DistinctionType.GENERAL, 3),
    OVERCONFIDENT(DistinctionType.GENERAL, 2),
    PERMANENT_WOUND(DistinctionType.GENERAL, 4),
    POSEIDONS_CALL(DistinctionType.GENERAL, 3),
    PIED_PIPER(DistinctionType.GENERAL, 1),
    PREY_EXCLUSION(DistinctionType.GENERAL, 1),
    REPELLED_BY_RELIGION(DistinctionType.GENERAL, 3),
    SHORT_ATTENTION_SPAN(DistinctionType.GENERAL, 4),
    SHORT_FUSE(DistinctionType.GENERAL, 2),
    SLOW_HEALING(DistinctionType.GENERAL, 3),
    SLOW_REACTIONS(DistinctionType.GENERAL, 3),
    STOLEN_POTENTIAL(DistinctionType.GENERAL, 3),
    THIN_BLOODED(DistinctionType.GENERAL, 3),
    THIRST_FOR_INNOCENCE(DistinctionType.GENERAL, 1),
    TROUBLE_MAGNET(DistinctionType.GENERAL, 1),
    VULERABLE_TO_SILVER(DistinctionType.GENERAL, 2),
    WEAK_WILLED(DistinctionType.GENERAL, 3),
    WEAK_STOMACH(DistinctionType.GENERAL, 3),
    
    ACCUSED_OF_HERESY(DistinctionType.SETTING, Setting.CAMARILLA, 2),
    FORGIVEN_DIABLERIE(DistinctionType.SETTING, Setting.CAMARILLA, 1),
    SECTARIAN(DistinctionType.SETTING, Setting.CAMARILLA, 2),
    TAINTED_EMBRACE(DistinctionType.SETTING, Setting.CAMARILLA, 2),
    UNTRAINED_COMBATANT(DistinctionType.SETTING, Setting.CAMARILLA, 3),
    
    CRISIS_OF_FAITH(DistinctionType.SETTING, Setting.SABBAT, 2),
    MISTRUSTED(DistinctionType.SETTING, Setting.SABBAT, 1),
    SOUL_SHARD(DistinctionType.SETTING, Setting.SABBAT, 3),
    TENUOUS_LOYALTY(DistinctionType.SETTING, Setting.SABBAT, 3),
    
    BASTARD_CHILDE(DistinctionType.SETTING, Setting.ANARCH, 2),
    BLACK_SHEEP(DistinctionType.SETTING, Setting.ANARCH, 2),
    DUBIOUS_LOYALTIES(DistinctionType.SETTING, Setting.ANARCH, 2),
    ESCAPED_SHOVELHEAD(DistinctionType.SETTING, Setting.ANARCH, 2),
    ONCE_ENSLAVED(DistinctionType.SETTING, Setting.ANARCH, 2);
	
    private final int points;
	private final DistinctionType type;
	private final Setting setting;
	
    private Flaw(DistinctionType distinctionType, int points) {
    	this(distinctionType, points, null);
    }
    
    private Flaw(DistinctionType distinctionType, Setting setting, int points) {
    	this(distinctionType, points, setting);
    }
	
    private Flaw(DistinctionType type, int points, Setting setting) {
        this.points = points;
		this.type = type;
		this.setting = setting;
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
    
    public TraitChange set(TraitChangeStatus status, String specialization) {
    	return new SetFlaw(status, this.ordinal(), specialization);
    }
	
}
