package com.dstevens.characters.traits;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.suppliers.IdSupplier;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

@Entity
@Inheritance
@DiscriminatorColumn(name="experience_type")
@Table(name="Experience")
public abstract class Experience implements ApplicableTrait {

    @Id
    private final String id;
    
	@Column(name="xp")
    private final int xp;

	//Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
    private Experience() {
        this(0);
    }
	
	public Experience(int xp) {
		this.id = new IdSupplier().get();
		this.xp = xp;
	}
	
	@Override
	public abstract PlayerCharacter applyTo(PlayerCharacter character);

	@Override
	public abstract PlayerCharacter removeFrom(PlayerCharacter character);
	
	protected final int xp() {
		return xp;
	}
	
}
