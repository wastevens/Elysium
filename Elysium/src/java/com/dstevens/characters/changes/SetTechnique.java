package com.dstevens.characters.changes;

import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.powers.Technique;

@Entity
@DiscriminatorValue("Technique")
class SetTechnique extends SetEnumeratedTrait {

    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private SetTechnique() {
        this(null, null, 0);
    }
    
    protected SetTechnique(String id, TraitChangeStatus status, Technique power) {
        super(id, status, power);
    }
    
    protected SetTechnique(String id, TraitChangeStatus status, int ordinal) {
        super(id, status, ordinal);
    }

    @Override
    public PlayerCharacter apply(PlayerCharacter character) {
        return character.withTechnique(Technique.values()[ordinal()]);
    }

    @Override
    public String describe() {
        return String.format("Set %1$s", Technique.values()[ordinal()]);
    }
}
