package com.dstevens.characters.changes;

import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.powers.magics.NecromanticRitual;

@Entity
@DiscriminatorValue("NecromanticRitual")
class SetNecromanticRitual extends SetEnumeratedTrait {

    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private SetNecromanticRitual() {
        this(null, null, 0);
    }
    
    protected SetNecromanticRitual(String id, TraitChangeStatus status, NecromanticRitual power) {
        super(id, status, power);
    }
    
    protected SetNecromanticRitual(String id, TraitChangeStatus status, int ordinal) {
        super(id, status, ordinal);
    }

    @Override
    public PlayerCharacter apply(PlayerCharacter character, TraitChangeFactory traitChangeFactory) {
        return character.withNecromanticRitual(NecromanticRitual.values()[ordinal()]);
    }

    @Override
    public String describe() {
        return String.format("Set %1$s", NecromanticRitual.values()[ordinal()]);
    }
}
