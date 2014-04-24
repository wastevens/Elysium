package com.dstevens.characters.changes;

import javax.persistence.Column;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.merits.*;

class SetMerit extends SetTrait {

    @Column(name="ordinal")
    private final int meritId;
    
    @Column(name="typeIdentifier")
    private final String meritType;
    
    @Column(name="specialization")
    private final String details;
    
    protected SetMerit(String id, TraitChangeStatus status, CharacterMerit merit) {
        this(id, status, merit.getMerit().ordinal(), merit.getMerit().getType(), merit.getDetails());
    }
    
    protected SetMerit(String id, TraitChangeStatus status, int meritId, String meritType, String details) {
        super(id, status);
        this.meritId = meritId;
        this.meritType = meritType;
        this.details = details;
    }

    @Override
    public PlayerCharacter apply(PlayerCharacter character, TraitChangeFactory traitChangeFactory) {
        return character.withMerit(new CharacterMerit(MeritTranslator.ofTypeWithId(meritType, meritId), details));
    }

}
