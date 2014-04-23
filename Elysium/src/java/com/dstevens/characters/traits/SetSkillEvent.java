package com.dstevens.characters.traits;

import java.util.Set;

import javax.persistence.*;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.skills.*;
import com.dstevens.suppliers.IdSupplier;

@Entity
@DiscriminatorValue("Skill")
public class SetSkillEvent extends TraitChangeEvent {

    @Column(name="ordinal")
    private int ordinal;
    
    @Column(name="rating")
    private int rating;
    
    @Column(name="specialization")
    private String specialization;
    
    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name="TraitChangeFocuses")
    @Column(name="focus")
    private Set<String> focuses;

    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private SetSkillEvent() {
        this(null, null, null, 0, 0, null, null);
    }
    
    public SetSkillEvent(String id, String characterId, TraitChangeStatus status,
                         int ordinal, int rating, String specialization, Set<String> focuses) {
        super(id, characterId, status);
        this.ordinal = ordinal;
        this.rating = rating;
        this.specialization = specialization;
        this.focuses = focuses;
    }

    @Override
    public PlayerCharacter apply(PlayerCharacter character) {
        CharacterSkillFactory factory = new CharacterSkillFactory(new IdSupplier());
        return character.withSkill(factory.skillFor(character, Skill.values()[ordinal], rating, specialization, focuses));
    }

}
