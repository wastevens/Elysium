package com.dstevens.characters.changes;

import java.util.Set;
import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.skills.*;

@Entity
@DiscriminatorValue("Skill")
class SetSkill extends SetCharacterDefinedTrait {

    //Hibernate only
    @Deprecated
    private SetSkill() {
        super(null, null, 0, 0, null, null);
    }
    
    protected SetSkill(String id, TraitChangeStatus status, CharacterSkill skill) {
        super(id, status, skill);
    }
    
    protected SetSkill(String id, TraitChangeStatus status, int ordinal, int rating, String specialization, Set<String> focuses) {
        super(id, status, ordinal, rating, specialization, focuses);
    }

    @Override
    public final PlayerCharacter apply(PlayerCharacter character, TraitChangeFactory traitChangeFactory) {
        CharacterSkillFactory factory = traitChangeFactory.characterSkillFactory();
        return character.withSkill(factory.skillFor(Skill.values()[ordinal()], rating(), specialization(), focuses()));
    }
    
    @Override
    public String describe() {
        if (isPresent(specialization())) {
            return String.format("(%1$s) Set %2$s (%3$s) to %4$s", status(), Skill.values()[ordinal()], specialization(), rating());
        }
        if (!focuses().isEmpty()) {
            return String.format("(%1$s) Set %2$s %3$s to %4$s", status(), Skill.values()[ordinal()], focuses(), rating());
        }
        return String.format("(%1$s) Set %2$s to %3$s", status(), Skill.values()[ordinal()], rating());
    }

    private boolean isPresent(String specialization) {
        return specialization != null && !specialization.isEmpty();
    }
}
