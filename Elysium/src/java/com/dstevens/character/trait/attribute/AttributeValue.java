package com.dstevens.character.trait.attribute;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.dstevens.character.PlayerCharacter;
import com.dstevens.character.trait.ApplicableTrait;

@Entity
@Inheritance
@DiscriminatorColumn(name="attribute_type")
@Table(name="AttributeValue")
public abstract class AttributeValue implements ApplicableTrait {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tableGen")
	@TableGenerator(name = "tableGen", pkColumnValue = "characterAttribute", table="ID_Sequences", allocationSize=1 )
	@Column(name="id", nullable=false, unique=true)
    private final Integer id;
    
	@Column(name="value")
    private final int value;

	//Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private AttributeValue() {
        this(0);
    }
	
	public AttributeValue(int value) {
		this.id = null;
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
