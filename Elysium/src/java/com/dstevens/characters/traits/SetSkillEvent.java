package com.dstevens.characters.traits;

import java.util.Set;
import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.skills.*;

@Entity
@DiscriminatorValue("Skill")
public class SetSkillEvent extends SetCharacterDefinedTrait {

    //Hibernate only
    @Deprecated
    private SetSkillEvent() {
        super(null, null, null, 0, 0, null, null);
    }
    
    public SetSkillEvent(String id, TraitChangeStatus status, CharacterSkill skill) {
        super(id, status, skill);
    }
    
    public SetSkillEvent(String id, String characterId, TraitChangeStatus status, int ordinal, int rating, String specialization, Set<String> focuses) {
        super(id, characterId, status, ordinal, rating, specialization, focuses);
    }

    @Override
    public PlayerCharacter apply(PlayerCharacter character, TraitChangeFactory traitChangeFactory) {
        CharacterSkillFactory factory = traitChangeFactory.characterSkillFactory();
        return character.withSkill(factory.skillFor(character, Skill.values()[ordinal()], rating(), specialization(), focuses()));
    }
}
