package com.dstevens.characters.changes;

import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.powers.ElderPower;

@Entity
@DiscriminatorValue("ElderPower")
class SetElderPower extends SetEnumeratedTrait {

    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private SetElderPower() {
        this(null, null, 0);
    }
    
    protected SetElderPower(String id, TraitChangeStatus status, ElderPower power) {
        super(id, status, power);
    }
    
    protected SetElderPower(String id, TraitChangeStatus status, int ordinal) {
        super(id, status, ordinal);
    }

    @Override
    public PlayerCharacter apply(PlayerCharacter character, TraitChangeFactory traitChangeFactory) {
        return character.withElderPower(ElderPower.values()[ordinal()]);
    }

}
