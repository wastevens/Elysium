package com.dstevens.characters.changes;

import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.merits.*;

@Entity
@DiscriminatorValue("Merit")
class SetMerit extends SetTrait {

    @Column(name="ordinal")
    private final int meritId;
    
    @Column(name="typeIdentifier")
    private final String meritType;
    
    @Column(name="specialization")
    private final String details;
    
    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetMerit() {
        this(null, null, 0, null, null);
    }
    
    protected SetMerit(String id, TraitChangeStatus status, CharacterMerit merit) {
        this(id, status, merit.getMerit().ordinal(), merit.getMerit().getType(), merit.getDetails());
    }
    
    protected SetMerit(String id, TraitChangeStatus status, Merit<?> merit, String details) {
        this(id, status, merit.ordinal(), merit.getType(), details);
    }
    
    protected SetMerit(String id, TraitChangeStatus status, int meritId, String meritType, String details) {
        super(id, status);
        this.meritId = meritId;
        this.meritType = meritType;
        this.details = details;
    }

    @Override
    public PlayerCharacter apply(PlayerCharacter character) {
        return character.withMerit(new CharacterMerit(MeritTranslator.ofTypeWithId(meritType, meritId), details));
    }
    
    @Override
    public String describe() {
        if (isPresent(details)) {
            return String.format("(%1$s) Set %2$s (%3$s)", status(), MeritTranslator.ofTypeWithId(meritType, meritId), details);
        }
        return String.format("(%1$s) Set %1$s", status(), MeritTranslator.ofTypeWithId(meritType, meritId));
    }

    private boolean isPresent(String specialization) {
        return specialization != null && !specialization.isEmpty();
    }

}
