package com.dstevens.characters.changes;

import static com.dstevens.collections.Sets.set;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.characters.backgrounds.*;
import com.dstevens.characters.skills.*;
import com.dstevens.suppliers.IdSupplier;

@Service
public class TraitChangeBuilder {

    private IdSupplier idSupplier;
    private CharacterSkillFactory skillFactory;
    private CharacterBackgroundFactory backgroundFactory;

    @Autowired
    public TraitChangeBuilder(IdSupplier idSupplier, CharacterSkillFactory skillFactory, CharacterBackgroundFactory backgroundFactory) {
        this.idSupplier = idSupplier;
        this.skillFactory = skillFactory;
        this.backgroundFactory = backgroundFactory;
    }
    
    public abstract class SetCharacterDefinedTraitBuilder<Trait extends Enum<?>> {
        
        protected Trait trait = null;
        protected int rating = 0;
        protected String specialization = null;
        protected Set<String> focuses = set();

        public SetCharacterDefinedTraitBuilder(Trait trait, int rating) {
            this(trait, rating, null, set());
        }
        
        private SetCharacterDefinedTraitBuilder(Trait trait, int rating, String specialization, Set<String> focuses) {
            this.trait = trait;
            this.rating = rating;
            this.specialization = specialization;
            this.focuses = focuses;
        }
        
        public final SetCharacterDefinedTraitBuilder<Trait> withSpecialization(String specialization) {
            this.specialization = specialization;
            return this;
        }
        
        public final SetCharacterDefinedTraitBuilder<Trait> withFocuses(Set<String> focuses) {
            this.focuses.addAll(focuses);
            return this;
        }
        
        public abstract SetTrait getEvent();
        
    }
    
    public class SetSkillBuilder extends SetCharacterDefinedTraitBuilder<Skill> {
        
        private SetSkillBuilder(Skill skill, int rating) {
            super(skill, rating);
        }
        
        public final SetTrait getEvent() {
            return new SetSkill(idSupplier.get(), TraitChangeStatus.PENDING, 
                                skillFactory.skillFor(trait, rating, specialization, focuses));
        }
    }
    
    
    public class SetBackgroundBuilder extends SetCharacterDefinedTraitBuilder<Background> {
        
        private SetBackgroundBuilder(Background background, int rating) {
            super(background, rating);
        }
        
        public final SetTrait getEvent() {
            return new SetBackground(idSupplier.get(), TraitChangeStatus.PENDING, 
                                     backgroundFactory.backgroundFor(trait, rating, specialization, focuses));
        }
    }
    
    public SetSkillBuilder setSkill(Skill skill, int rating) {
        return new SetSkillBuilder(skill, rating);
    }
    
    public SetBackgroundBuilder setBackground(Background background, int rating) {
        return new SetBackgroundBuilder(background, rating);
    }

    public SetTrait gainXp(int xp) {
        return changeXp(-1 * xp);
    }

    public SetTrait spendXp(int xp) {
        return changeXp(xp);
    }
    
    private SetTrait changeXp(int xp) {
        return new SetXp(idSupplier.get(), TraitChangeStatus.PENDING, xp);
    }

}
