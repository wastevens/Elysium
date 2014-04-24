package com.dstevens.characters.changes;

import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.powers.magics.ThaumaturgicalRitual;

@Entity
@DiscriminatorValue("ThaumaturgicalRitual")
class SetThaumaturgicalRitual extends SetEnumeratedTrait {

    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private SetThaumaturgicalRitual() {
        this(null, null, 0);
    }
    
    protected SetThaumaturgicalRitual(String id, TraitChangeStatus status, ThaumaturgicalRitual power) {
        super(id, status, power);
    }
    
    protected SetThaumaturgicalRitual(String id, TraitChangeStatus status, int ordinal) {
        super(id, status, ordinal);
    }

    @Override
    public PlayerCharacter apply(PlayerCharacter character, TraitChangeFactory traitChangeFactory) {
        return character.withThaumaturgicalRitual(ThaumaturgicalRitual.values()[ordinal()]);
    }

}
