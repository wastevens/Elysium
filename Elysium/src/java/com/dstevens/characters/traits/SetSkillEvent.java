package com.dstevens.characters.traits;

import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.skills.*;
import com.dstevens.suppliers.IdSupplier;

@Entity
@DiscriminatorValue("Skill")
public class SetSkillEvent extends TraitChangeEvent {

    //Hibernate only
    @Deprecated
    private SetSkillEvent() {
        super(null, null, null, 0, 0, null, null);
    }
    
    public SetSkillEvent(String id, TraitChangeStatus status, CharacterSkill skill) {
        super(id, skill.getCharacterId(), status, skill.getSkill().ordinal(), skill.getRating(), skill.getSpecialization(), skill.getFocuses());
    }

    @Override
    public PlayerCharacter apply(PlayerCharacter character) {
        CharacterSkillFactory factory = new CharacterSkillFactory(new IdSupplier());
        return character.withSkill(factory.skillFor(character, Skill.values()[getOrdinal()], getRating(), getSpecialization(), getFocuses()));
    }

}
