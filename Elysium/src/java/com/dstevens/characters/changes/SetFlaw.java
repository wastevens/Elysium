package com.dstevens.characters.changes;

import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.merits.*;

@Entity
@DiscriminatorValue("Flaw")
class SetFlaw extends SetTrait {

    @Column(name="ordinal")
    private final int ordinal;
    
    @Column(name="typeIdentifier")
    private final String typeIdentifier;
    
    @Column(name="specialization")
    private final String specialization;
    
    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetFlaw() {
        this(null, null, 0, null, null);
    }
    
    protected SetFlaw(String id, TraitChangeStatus status, CharacterFlaw flaw) {
        this(id, status, flaw.getFlaw().ordinal(), flaw.getFlaw().getType(), flaw.getDetails());
    }
    
    protected SetFlaw(String id, TraitChangeStatus status, Flaw<?> flaw, String details) {
        this(id, status, flaw.ordinal(), flaw.getType(), details);
    }
    
    protected SetFlaw(String id, TraitChangeStatus status, int ordinal, String typeIdentifier, String specialization) {
        super(id, status);
        this.ordinal = ordinal;
        this.typeIdentifier = typeIdentifier;
        this.specialization = specialization;
    }

    @Override
    public PlayerCharacter apply(PlayerCharacter character, TraitChangeFactory traitChangeFactory) {
        return character.withFlaw(new CharacterFlaw(FlawTranslator.ofTypeWithId(typeIdentifier, ordinal), specialization));
    }
    
    @Override
    public String describe() {
        if (isPresent(specialization)) {
            return String.format("(%1$s) Set %2$s (%3$s)", status(),FlawTranslator.ofTypeWithId(typeIdentifier, ordinal), specialization);
        }
        return String.format("(%1$s) Set %2$s", status(),FlawTranslator.ofTypeWithId(typeIdentifier, ordinal));
    }

    private boolean isPresent(String specialization) {
        return specialization != null && !specialization.isEmpty();
    }

}
