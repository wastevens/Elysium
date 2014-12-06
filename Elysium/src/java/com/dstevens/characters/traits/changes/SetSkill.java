package com.dstevens.characters.traits.changes;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.skills.CharacterSkill;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("Skill")
public class SetSkill extends SetTrait {

	@OneToOne(cascade={CascadeType.ALL}, optional=true)
	@JoinColumn(name="trait_id", referencedColumnName="id", foreignKey=@ForeignKey(name="none"))
    private CharacterSkill trait;

	//Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetSkill() {
        this(null, null);
    }
    
    public SetSkill(TraitChangeStatus status, CharacterSkill trait) {
    	super(status);
		this.trait = trait;
    }

    public PlayerCharacter apply(PlayerCharacter character) {
        return trait.applyTo(character);
    }
    
    @Override
	public PlayerCharacter remove(PlayerCharacter character) {
    	return trait.removeFrom(character);
	}
    
    @Override
    public String describe() {
        String displaySpecialization = (!StringUtils.isEmpty(specialization()) ? String.format(" (%1$s)", specialization()) : "");
        String displayFocuses = (!focuses().isEmpty() ? String.format(" %1$s", focuses()) : "");
        String nextTrait = (hasAssociatedTrait() ? String.format (" and %1$s", associatedTrait().describe()) : "");
        
        return String.format("(%1$s) Set %2$s%3$s to %4$s%5$s%6$s", status(), trait, displaySpecialization, rating(), displayFocuses, nextTrait);
    }

	private int rating() {
		return trait.rating();
	}

	private Set<String> focuses() {
		return trait.getFocuses();
	}

	private CharSequence specialization() {
		return trait.getSpecialization();
	}
}
