package com.dstevens.characters.changes;

import java.util.Set;
import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.skills.*;

@Entity
@DiscriminatorValue("Skill")
@TraitType(type=Skill.class)
class SetSkill extends SetCharacterDefinedTrait {

    //Hibernate only
    @Deprecated
    private SetSkill() {
        super(null, 0, 0, null, null);
    }
    
    protected SetSkill(TraitChangeStatus status, CharacterSkill skill) {
        super(status, skill);
    }
    
    protected SetSkill(TraitChangeStatus status, int ordinal, int rating, String specialization, Set<String> focuses) {
        super(status, ordinal, rating, specialization, focuses);
    }

    @Override
    public final PlayerCharacter apply(PlayerCharacter character) {
        return character.withSkill(CharacterSkill.skillFor(trait(), rating(), specialization(), focuses()));
    }
}
