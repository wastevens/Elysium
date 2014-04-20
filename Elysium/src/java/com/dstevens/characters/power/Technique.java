package com.dstevens.characters.power;

import static com.dstevens.characters.power.TechniqueRequirement.required;
import static com.dstevens.collections.Sets.set;

import java.util.Set;

public enum Technique {

    AN_DA_SHEALLADH(required(Power.DEMENTATION, 3), required(Power.AUSPEX, 2)),
    ANIMAL_SUCCULENCE(required(Power.ANIMALISM, 2), required(Power.FORTITUDE, 1)),
    ANIMAL_SWARM(required(Power.PROTEAN, 4), required(Power.CELERITY, 2)),
    ARMOR_OF_DARKNESS(required(Power.OBTENEBRATION, 3), required(Power.FORTITUDE, 2)),
    BANSHEES_WAIL(required(Power.MELPOMINEE, 3), required(Power.DEMENTATION, 4)),
    BLIND_THE_SUN(required(Power.TEMPORSIS, 2), required(Power.FORTITUDE, 2)),
    BULLS_EYE(required(Power.AUSPEX, 3), required(Power.CELERITY, 1)),
    CONTROL_THE_SAVAGE_BEAST(required(Power.ANIMALISM, 3), required(Power.DOMINATE, 2)),
    DEATHS_GRIP(required(Power.THANATOSIS, 2), required(Power.POTENCE, 2)),
    DENIAL_OF_APHRODITES_FAVOR(required(Power.PRESENCE, 2), required(Power.DOMINATE, 2)),
    DEVIOUS_FEINT(required(Power.AUSPEX, 3), required(Power.PRESENCE, 1)),
    ECHO_PSYCHOSIS(required(Power.DEMENTATION, 3), required(Power.PRESENCE, 1)),
    FEARFUL_BLOW(required(Power.POTENCE, 2), required(Power.PRESENCE, 2)),
    FEAST_OF_SHADOWS(required(Power.OBTENEBRATION, 2), required(Power.POTENCE, 3)),
    FENRIRS_BOON(required(Power.POTENCE, 3), required(Power.ANIMALISM, 1)),
    FIST_OF_STONE(required(Power.VISCERATIKA, 3), required(Power.POTENCE, 3)),
    FLAMES_BANE(required(Power.DAIMOINON, 2), required(Power.FORTITUDE, 2)),
    GUARDIAN_LION(required(Power.VISCERATIKA, 2), required(Power.ANIMALISM, 2)),
    INSTINCTIVE_COMMAND(required(Power.CELERITY, 3), required(Power.DOMINATE, 2)),
    LIGEIAS_LAMENT(required(Power.MELPOMINEE, 2), required(Power.DOMINATE, 2)),
    MERCURIAL_VITALITY(required(Power.VICISSITUDE, 2), required(Power.POTENCE, 1), required(Power.FORTITUDE, 1)),
    MONOLOGUE(required(Power.OBFUSCATE, 2), required(Power.PRESENCE, 1)),
    MISPLACED_AFFECTION(required(Power.PRESENCE, 3), required(Power.OBFUSCATE, 2)),
    MISLEADING_WOUNDS(required(Power.OBFUSCATE, 3), required(Power.FORTITUDE, 1)),
    NIGHTINGALES_SONG(required(Power.MYTHERCERIA, 2), required(Power.DOMINATE, 2)),
    QUICKENED_BLOOD(required(Power.CELERITY, 2), required(Power.FORTITUDE, 1)),
    RADIANT_GAZE(required(Power.SERPENTIS, 2), required(Power.DOMINATE, 3)),
    REFLECTION_OF_ENDURANCE(required(Power.OBEAH, 3), required(Power.FORTITUDE, 2)),
    REFLECTION_OF_MERCY(required(Power.VALEREN, 3), required(Power.AUSPEX, 2)),
    RELENTLESS_PURSUIT(required(Power.POTENCE, 2), required(Power.CELERITY, 2)),
    RETAIN_THE_QUICK_BLOOD(required(Power.QUIETUS, 2), required(Power.CELERITY, 2)),
    SECOND_WIND(required(Power.FORTITUDE, 3), required(Power.POTENCE, 1)),
    SYMPATHETIC_AGONY(required(Power.CHIMERSTRY, 2), required(Power.FORTITUDE, 3)),
    TELEPATHIC_DIRECTIVE(required(Power.AUSPEX, 4), required(Power.DOMINATE, 1)),
    UNNATURAL_GRACE(required(Power.CELERITY, 1), required(Power.FORTITUDE, 1), required(Power.PRESENCE, 1)),
    VISIONS_OF_THE_TRUE_FORM(required(Power.VICISSITUDE, 2), required(Power.AUSPEX, 3)),
    WILL_TO_SURVIVE(required(Power.FORTITUDE, 1), required(Power.PRESENCE, 1)),
    WOLFS_BLOOD(required(Power.ANIMALISM, 2), required(Power.PROTEAN, 1));
    
    private Set<TechniqueRequirement> requirements;

    private Technique(TechniqueRequirement... requirements) {
        this.requirements = set(requirements);
    }
    
    public Set<TechniqueRequirement> requirements() {
        return requirements;
    }
    
}
