package com.dstevens.characters.changes;

import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.powers.magics.*;

@Entity
@DiscriminatorValue("Necromancy")
@TraitType(type=Necromancy.class)
class SetNecromancy extends SetRatedTrait {

    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private SetNecromancy() {
        this(null, null, 0, 0);
    }
    
    protected SetNecromancy(String id, TraitChangeStatus status, CharacterNecromancy power) {
        super(id, status, power);
    }

    protected SetNecromancy(String id, TraitChangeStatus status, Necromancy power, int rating) {
        super(id, status, power.ordinal(), rating);
    }
    
    protected SetNecromancy(String id, TraitChangeStatus status, int ordinal, int rating) {
        super(id, status, ordinal, rating);
    }

    @Override
    public PlayerCharacter apply(PlayerCharacter character) {
        return character.withNecromanticPath(new CharacterNecromancy(trait(), rating()));
    }

}
