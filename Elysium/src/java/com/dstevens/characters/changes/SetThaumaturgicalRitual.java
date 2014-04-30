package com.dstevens.characters.changes;

import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.powers.magics.ThaumaturgicalRitual;

@Entity
@DiscriminatorValue("ThaumaturgicalRitual")
@TraitType(type=ThaumaturgicalRitual.class)
class SetThaumaturgicalRitual extends SetEnumeratedTrait {

    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private SetThaumaturgicalRitual() {
        this(null, null, 0);
    }
    
    protected SetThaumaturgicalRitual(String id, TraitChangeStatus status, ThaumaturgicalRitual power) {
        super(status, power);
    }
    
    protected SetThaumaturgicalRitual(String id, TraitChangeStatus status, int ordinal) {
        super(status, ordinal);
    }

    @Override
    public PlayerCharacter apply(PlayerCharacter character) {
        return character.withThaumaturgicalRitual(trait());
    }
}
