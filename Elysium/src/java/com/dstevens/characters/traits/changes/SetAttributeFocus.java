package com.dstevens.characters.traits.changes;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.attributes.AttributeFocus;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("AttributeFocus")
public class SetAttributeFocus extends SetTrait {

    @Column(name="ordinal")
    private int attributeFocusOrdinal;

    @Column(name="factory")
    private AttributeFactory factory;

    
    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetAttributeFocus() {
        this(null, 0, null);
    }
    
    public SetAttributeFocus(TraitChangeStatus status, AttributeFocus focus, AttributeFactory factory) {
        this(status, focus.ordinal(), factory);
    }
    
    private SetAttributeFocus(TraitChangeStatus status, int attributeFocusOrdinal, AttributeFactory factory) {
        super(status);
        this.attributeFocusOrdinal = attributeFocusOrdinal;
        this.factory = factory;
    }
    
    @Override
    public PlayerCharacter apply(PlayerCharacter character) {
        return factory.applyFocusTo(attributeFocusOrdinal, character);
    }
    
    @Override
    public PlayerCharacter remove(PlayerCharacter character) {
    	return factory.applyFocusTo(attributeFocusOrdinal, character);
    }
    
    @Override
    public String describe() {
        String nextTrait = (hasAssociatedTrait() ? String.format(" with %1$s", associatedTrait().describe()) : "");
        return String.format("(%1$s) Add Focus %2$s to %3$s%4$s", status(), factory.focusFor(attributeFocusOrdinal), factory.attributeName(), nextTrait);
    }
}
