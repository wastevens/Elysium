package com.dstevens.character.trait.power.discipline;

import java.util.Set;

import com.dstevens.character.PlayerCharacter;
import com.dstevens.character.trait.ApplicableTrait;
import com.dstevens.character.trait.Trait;
import com.dstevens.character.trait.TraitQualities;
import com.dstevens.collections.Sets;

import static com.dstevens.character.trait.power.discipline.TechniqueRequirement.required;

public enum Technique implements Trait, ApplicableTrait {

    AN_DA_SHEALLADH(0, required(Discipline.DEMENTATION, 3), required(Discipline.AUSPEX, 2)),
    ANIMAL_SUCCULENCE(1, required(Discipline.ANIMALISM, 2), required(Discipline.FORTITUDE, 1)),
    ANIMAL_SWARM(2, required(Discipline.PROTEAN, 4), required(Discipline.CELERITY, 2)),
    ARMOR_OF_DARKNESS(3, required(Discipline.OBTENEBRATION, 3), required(Discipline.FORTITUDE, 2)),
    BANSHEES_WAIL(4, required(Discipline.MELPOMINEE, 3), required(Discipline.DEMENTATION, 4)),
    BLIND_THE_SUN(5, required(Discipline.TEMPORSIS, 2), required(Discipline.FORTITUDE, 2)),
    BULLS_EYE(6, required(Discipline.AUSPEX, 3), required(Discipline.CELERITY, 1)),
    CONTROL_THE_SAVAGE_BEAST(7, required(Discipline.ANIMALISM, 3), required(Discipline.DOMINATE, 2)),
    DEATHS_GRIP(8, required(Discipline.THANATOSIS, 2), required(Discipline.POTENCE, 2)),
    DENIAL_OF_APHRODITES_FAVOR(9, required(Discipline.PRESENCE, 2), required(Discipline.DOMINATE, 2)),
    DEVIOUS_FEINT(10, required(Discipline.AUSPEX, 3), required(Discipline.PRESENCE, 1)),
    ECHO_PSYCHOSIS(11, required(Discipline.DEMENTATION, 3), required(Discipline.PRESENCE, 1)),
    FEARFUL_BLOW(12, required(Discipline.POTENCE, 2), required(Discipline.PRESENCE, 2)),
    FEAST_OF_SHADOWS(13, required(Discipline.OBTENEBRATION, 2), required(Discipline.POTENCE, 3)),
    FENRIRS_BOON(14, required(Discipline.POTENCE, 3), required(Discipline.ANIMALISM, 1)),
    FIST_OF_STONE(15, required(Discipline.VISCERATIKA, 3), required(Discipline.POTENCE, 3)),
    FLAMES_BANE(16, required(Discipline.DAIMOINON, 2), required(Discipline.FORTITUDE, 2)),
    GUARDIAN_LION(17, required(Discipline.VISCERATIKA, 2), required(Discipline.ANIMALISM, 2)),
    INSTINCTIVE_COMMAND(18, required(Discipline.CELERITY, 3), required(Discipline.DOMINATE, 2)),
    LIGEIAS_LAMENT(19, required(Discipline.MELPOMINEE, 2), required(Discipline.DOMINATE, 2)),
    MERCURIAL_VITALITY(20, required(Discipline.VICISSITUDE, 2), required(Discipline.POTENCE, 1), required(Discipline.FORTITUDE, 1)),
    MONOLOGUE(21, required(Discipline.OBFUSCATE, 2), required(Discipline.PRESENCE, 1)),
    MISPLACED_AFFECTION(22, required(Discipline.PRESENCE, 3), required(Discipline.OBFUSCATE, 2)),
    MISLEADING_WOUNDS(23, required(Discipline.OBFUSCATE, 3), required(Discipline.FORTITUDE, 1)),
    NIGHTINGALES_SONG(24, required(Discipline.MYTHERCERIA, 2), required(Discipline.DOMINATE, 2)),
    QUICKENED_BLOOD(25, required(Discipline.CELERITY, 2), required(Discipline.FORTITUDE, 1)),
    RADIANT_GAZE(26, required(Discipline.SERPENTIS, 2), required(Discipline.DOMINATE, 3)),
    REFLECTION_OF_ENDURANCE(27, required(Discipline.OBEAH, 3), required(Discipline.FORTITUDE, 2)),
    REFLECTION_OF_MERCY(28, required(Discipline.VALEREN, 3), required(Discipline.AUSPEX, 2)),
    RELENTLESS_PURSUIT(29, required(Discipline.POTENCE, 2), required(Discipline.CELERITY, 2)),
    RETAIN_THE_QUICK_BLOOD(30, required(Discipline.QUIETUS, 2), required(Discipline.CELERITY, 2)),
    SECOND_WIND(31, required(Discipline.FORTITUDE, 3), required(Discipline.POTENCE, 1)),
    SYMPATHETIC_AGONY(32, required(Discipline.CHIMERSTRY, 2), required(Discipline.FORTITUDE, 3)),
    TELEPATHIC_DIRECTIVE(33, required(Discipline.AUSPEX, 4), required(Discipline.DOMINATE, 1)),
    UNNATURAL_GRACE(34, required(Discipline.CELERITY, 1), required(Discipline.FORTITUDE, 1), required(Discipline.PRESENCE, 1)),
    VISIONS_OF_THE_TRUE_FORM(35, required(Discipline.VICISSITUDE, 2), required(Discipline.AUSPEX, 3)),
    WILL_TO_SURVIVE(36, required(Discipline.FORTITUDE, 1), required(Discipline.PRESENCE, 1)),
    WOLFS_BLOOD(37, required(Discipline.ANIMALISM, 2), required(Discipline.PROTEAN, 1));
    
	private final int id;
	private final Set<TechniqueRequirement> requirements;

    private Technique(int id, TechniqueRequirement... requirements) {
        this.id = id;
		this.requirements = Sets.set(requirements);
    }
    
	@Override
	public Integer getId() {
		return id;
	}
    
    public Set<TechniqueRequirement> requirements() {
        return requirements;
    }

	@Override
	public PlayerCharacter applyTo(PlayerCharacter character) {
		return character.withTechnique(this);
	}

	@Override
	public PlayerCharacter removeFrom(PlayerCharacter character) {
		return character.withoutTechnique(this);
	}

	@Override
	public ApplicableTrait applyWith(TraitQualities qualities) {
		return this;
	}
}
