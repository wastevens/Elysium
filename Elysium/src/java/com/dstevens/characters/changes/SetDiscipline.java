package com.dstevens.characters.changes;

import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.powers.*;

@Entity
@DiscriminatorValue("Discipline")
class SetDiscipline extends SetRatedTrait {

    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private SetDiscipline() {
        this(null, null, 0, 0);
    }
    
    protected SetDiscipline(String id, TraitChangeStatus status, CharacterDiscipline power) {
        super(id, status, power);
    }
    
    protected SetDiscipline(String id, TraitChangeStatus status, Discipline power, int rating) {
        super(id, status, power.ordinal(), rating);
    }
    
    protected SetDiscipline(String id, TraitChangeStatus status, int ordinal, int rating) {
        super(id, status, ordinal, rating);
    }

    @Override
    public PlayerCharacter apply(PlayerCharacter character, TraitChangeFactory traitChangeFactory) {
        return character.withDiscipline(new CharacterDiscipline(Discipline.values()[ordinal()], rating()));
    }

}
