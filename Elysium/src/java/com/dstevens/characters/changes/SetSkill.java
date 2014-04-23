package com.dstevens.characters.changes;

import java.util.Set;
import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.skills.*;

@Entity
@DiscriminatorValue("Skill")
public class SetSkill extends SetCharacterDefinedTrait {

    //Hibernate only
    @Deprecated
    private SetSkill() {
        super(null, null, 0, 0, null, null);
    }
    
    public SetSkill(String id, TraitChangeStatus status, CharacterSkill skill) {
        super(id, status, skill);
    }
    
    public SetSkill(String id, TraitChangeStatus status, int ordinal, int rating, String specialization, Set<String> focuses) {
        super(id, status, ordinal, rating, specialization, focuses);
    }

    @Override
    public PlayerCharacter apply(PlayerCharacter character, TraitChangeFactory traitChangeFactory) {
        CharacterSkillFactory factory = traitChangeFactory.characterSkillFactory();
        return character.withSkill(factory.skillFor(character, Skill.values()[ordinal()], rating(), specialization(), focuses()));
    }
}
