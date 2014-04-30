package com.dstevens.characters.changes;

import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.powers.*;

@Entity
@DiscriminatorValue("Discipline")
@TraitType(type=Discipline.class)
class SetDiscipline extends SetRatedTrait {

    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private SetDiscipline() {
        this(null, null, 0, 0);
    }
    
    protected SetDiscipline(String id, TraitChangeStatus status, CharacterDiscipline power) {
        super(status, power);
    }
    
    protected SetDiscipline(String id, TraitChangeStatus status, Discipline power, int rating) {
        super(status, power.ordinal(), rating);
    }
    
    protected SetDiscipline(String id, TraitChangeStatus status, int ordinal, int rating) {
        super(status, ordinal, rating);
    }

    @Override
    public PlayerCharacter apply(PlayerCharacter character) {
        return character.withDiscipline(new CharacterDiscipline(trait(), rating()));
    }
}
