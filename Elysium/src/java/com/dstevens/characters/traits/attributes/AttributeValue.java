package com.dstevens.characters.traits.attributes;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.ApplicableTrait;
import com.dstevens.suppliers.IdSupplier;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

@Entity
@Inheritance
@DiscriminatorColumn(name="attribute_type")
@Table(name="AttributeValue")
public abstract class AttributeValue implements ApplicableTrait {

    @Id
    private final String id;
    
	@Column(name="value")
    private final int value;

	//Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private AttributeValue() {
        this(0);
    }
	
	public AttributeValue(int value) {
		this.id = new IdSupplier().get();
		this.value = value;
	}
	
	@Override
	public abstract PlayerCharacter applyTo(PlayerCharacter character);

	@Override
	public final PlayerCharacter removeFrom(PlayerCharacter character) {
		throw new IllegalStateException("You cannot remove from an attribute, only set it to a previous value");
	}
	
	protected final int value() {
		return value;
	}
	
}
