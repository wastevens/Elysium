package com.dstevens.characters.traits.powers.disciplines;

import java.util.Set;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.characters.traits.Trait;
import com.dstevens.characters.traits.TraitQualities;
import com.dstevens.collections.Sets;

import static com.dstevens.characters.traits.powers.disciplines.TechniqueRequirement.required;

public enum Technique implements Trait, ApplicableTrait {

    AN_DA_SHEALLADH(required(Discipline.DEMENTATION, 3), required(Discipline.AUSPEX, 2)),
    ANIMAL_SUCCULENCE(required(Discipline.ANIMALISM, 2), required(Discipline.FORTITUDE, 1)),
    ANIMAL_SWARM(required(Discipline.PROTEAN, 4), required(Discipline.CELERITY, 2)),
    ARMOR_OF_DARKNESS(required(Discipline.OBTENEBRATION, 3), required(Discipline.FORTITUDE, 2)),
    BANSHEES_WAIL(required(Discipline.MELPOMINEE, 3), required(Discipline.DEMENTATION, 4)),
    BLIND_THE_SUN(required(Discipline.TEMPORSIS, 2), required(Discipline.FORTITUDE, 2)),
    BULLS_EYE(required(Discipline.AUSPEX, 3), required(Discipline.CELERITY, 1)),
    CONTROL_THE_SAVAGE_BEAST(required(Discipline.ANIMALISM, 3), required(Discipline.DOMINATE, 2)),
    DEATHS_GRIP(required(Discipline.THANATOSIS, 2), required(Discipline.POTENCE, 2)),
    DENIAL_OF_APHRODITES_FAVOR(required(Discipline.PRESENCE, 2), required(Discipline.DOMINATE, 2)),
    DEVIOUS_FEINT(required(Discipline.AUSPEX, 3), required(Discipline.PRESENCE, 1)),
    ECHO_PSYCHOSIS(required(Discipline.DEMENTATION, 3), required(Discipline.PRESENCE, 1)),
    FEARFUL_BLOW(required(Discipline.POTENCE, 2), required(Discipline.PRESENCE, 2)),
    FEAST_OF_SHADOWS(required(Discipline.OBTENEBRATION, 2), required(Discipline.POTENCE, 3)),
    FENRIRS_BOON(required(Discipline.POTENCE, 3), required(Discipline.ANIMALISM, 1)),
    FIST_OF_STONE(required(Discipline.VISCERATIKA, 3), required(Discipline.POTENCE, 3)),
    FLAMES_BANE(required(Discipline.DAIMOINON, 2), required(Discipline.FORTITUDE, 2)),
    GUARDIAN_LION(required(Discipline.VISCERATIKA, 2), required(Discipline.ANIMALISM, 2)),
    INSTINCTIVE_COMMAND(required(Discipline.CELERITY, 3), required(Discipline.DOMINATE, 2)),
    LIGEIAS_LAMENT(required(Discipline.MELPOMINEE, 2), required(Discipline.DOMINATE, 2)),
    MERCURIAL_VITALITY(required(Discipline.VICISSITUDE, 2), required(Discipline.POTENCE, 1), required(Discipline.FORTITUDE, 1)),
    MONOLOGUE(required(Discipline.OBFUSCATE, 2), required(Discipline.PRESENCE, 1)),
    MISPLACED_AFFECTION(required(Discipline.PRESENCE, 3), required(Discipline.OBFUSCATE, 2)),
    MISLEADING_WOUNDS(required(Discipline.OBFUSCATE, 3), required(Discipline.FORTITUDE, 1)),
    NIGHTINGALES_SONG(required(Discipline.MYTHERCERIA, 2), required(Discipline.DOMINATE, 2)),
    QUICKENED_BLOOD(required(Discipline.CELERITY, 2), required(Discipline.FORTITUDE, 1)),
    RADIANT_GAZE(required(Discipline.SERPENTIS, 2), required(Discipline.DOMINATE, 3)),
    REFLECTION_OF_ENDURANCE(required(Discipline.OBEAH, 3), required(Discipline.FORTITUDE, 2)),
    REFLECTION_OF_MERCY(required(Discipline.VALEREN, 3), required(Discipline.AUSPEX, 2)),
    RELENTLESS_PURSUIT(required(Discipline.POTENCE, 2), required(Discipline.CELERITY, 2)),
    RETAIN_THE_QUICK_BLOOD(required(Discipline.QUIETUS, 2), required(Discipline.CELERITY, 2)),
    SECOND_WIND(required(Discipline.FORTITUDE, 3), required(Discipline.POTENCE, 1)),
    SYMPATHETIC_AGONY(required(Discipline.CHIMERSTRY, 2), required(Discipline.FORTITUDE, 3)),
    TELEPATHIC_DIRECTIVE(required(Discipline.AUSPEX, 4), required(Discipline.DOMINATE, 1)),
    UNNATURAL_GRACE(required(Discipline.CELERITY, 1), required(Discipline.FORTITUDE, 1), required(Discipline.PRESENCE, 1)),
    VISIONS_OF_THE_TRUE_FORM(required(Discipline.VICISSITUDE, 2), required(Discipline.AUSPEX, 3)),
    WILL_TO_SURVIVE(required(Discipline.FORTITUDE, 1), required(Discipline.PRESENCE, 1)),
    WOLFS_BLOOD(required(Discipline.ANIMALISM, 2), required(Discipline.PROTEAN, 1));
    
    private Set<TechniqueRequirement> requirements;

    private Technique(TechniqueRequirement... requirements) {
        this.requirements = Sets.set(requirements);
    }
    
	@Override
	public int id() {
		return 0;
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
