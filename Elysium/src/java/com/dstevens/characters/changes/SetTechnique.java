package com.dstevens.characters.changes;

import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.powers.Technique;

@Entity
@DiscriminatorValue("Technique")
@TraitType(type=Technique.class)
class SetTechnique extends SetEnumeratedTrait {

    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private SetTechnique() {
        this(null, null, 0);
    }
    
    protected SetTechnique(String id, TraitChangeStatus status, Technique power) {
        super(status, power);
    }
    
    protected SetTechnique(String id, TraitChangeStatus status, int ordinal) {
        super(status, ordinal);
    }

    @Override
    public PlayerCharacter apply(PlayerCharacter character) {
        return character.withTechnique(trait());
    }
}
