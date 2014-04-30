package com.dstevens.characters.changes;

import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.powers.magics.NecromanticRitual;

@Entity
@DiscriminatorValue("NecromanticRitual")
@TraitType(type=NecromanticRitual.class)
class SetNecromanticRitual extends SetEnumeratedTrait {

    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private SetNecromanticRitual() {
        this(null, null, 0);
    }
    
    protected SetNecromanticRitual(String id, TraitChangeStatus status, NecromanticRitual power) {
        super(status, power);
    }
    
    protected SetNecromanticRitual(String id, TraitChangeStatus status, int ordinal) {
        super(status, ordinal);
    }

    @Override
    public PlayerCharacter apply(PlayerCharacter character) {
        return character.withNecromanticRitual(trait());
    }
}
