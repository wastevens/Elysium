package com.dstevens.characters.changes;

import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.powers.magics.*;

@Entity
@DiscriminatorValue("Thaumaturgy")
public class SetThaumaturgy extends SetRatedTrait {

    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private SetThaumaturgy() {
        this(null, null, 0, 0);
    }
    
    protected SetThaumaturgy(String id, TraitChangeStatus status, CharacterThaumaturgy power) {
        super(id, status, power);
    }
    
    protected SetThaumaturgy(String id, TraitChangeStatus status, int ordinal, int rating) {
        super(id, status, ordinal, rating);
    }

    @Override
    public PlayerCharacter apply(PlayerCharacter character, TraitChangeFactory traitChangeFactory) {
        return character.withThaumaturgicalPath(new CharacterThaumaturgy(Thaumaturgy.values()[ordinal()], rating()));
    }

}
