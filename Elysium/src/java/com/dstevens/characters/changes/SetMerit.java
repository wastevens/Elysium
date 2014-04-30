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
        this(null, null, 0, null, null, null);
    }
    
    protected SetMerit(String id, TraitChangeStatus status, Merit<?> merit, String details) {
        this(id, status, merit.ordinal(), merit.getType(), details, null);
    }
    
    protected SetMerit(String id, TraitChangeStatus status, Merit<?> merit, String details, SetTrait associatedTrait) {
        this(id, status, merit.ordinal(), merit.getType(), details, associatedTrait);
    }
    
    private SetMerit(String id, TraitChangeStatus status, int meritId, String meritType, String details, SetTrait associatedTrait) {
        super(id, status, associatedTrait);
        this.meritId = meritId;
        this.meritType = meritType;
        this.details = details;
    }
    
    @Override
    public PlayerCharacter apply(PlayerCharacter character) {
        return character.withMerit(new CharacterMerit(trait(), details));
    }
    
    @Override
    public String describe() {
        String specialization = (isPresent(details) ? String.format(" (%1$s)", details) : "");
        String nextTrait = (hasAssociatedTrait() ? String.format(" with %1$s", associatedTrait().describe()) : "");
        return String.format("(%1$s) Set %2$s%3$s%4$s", status(), trait(), specialization, nextTrait);
    }

    private boolean isPresent(String specialization) {
        return specialization != null && !specialization.isEmpty();
    }

    private Merit<?> trait() {
        return MeritTranslator.ofTypeWithId(meritType, meritId);
    }

}
