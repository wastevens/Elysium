package com.dstevens.characters.changes;

import static com.dstevens.collections.Sets.set;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.characters.backgrounds.*;
import com.dstevens.characters.merits.*;
import com.dstevens.characters.powers.*;
import com.dstevens.characters.powers.magics.*;
import com.dstevens.characters.skills.*;
import com.dstevens.suppliers.IdSupplier;

@Service
public class TraitChangeBuilder {

    private IdSupplier idSupplier;

    @Autowired
    public TraitChangeBuilder(IdSupplier idSupplier) {
        this.idSupplier = idSupplier;
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
    
    public SetSkillBuilder setSkill(Skill skill, int rating) {
        return new SetSkillBuilder(skill, rating);
    }
    
    public class SetSkillBuilder extends SetCharacterDefinedTraitBuilder<Skill> {
        
        private SetSkillBuilder(Skill skill, int rating) {
            super(skill, rating);
        }
        
        public final SetTrait getEvent() {
            return new SetSkill(idSupplier.get(), TraitChangeStatus.PENDING, 
                                CharacterSkill.skillFor(trait, rating, specialization, focuses));
        }
    }
    
    public SetBackgroundBuilder setBackground(Background background, int rating) {
        return new SetBackgroundBuilder(background, rating);
    }
    
    public class SetBackgroundBuilder extends SetCharacterDefinedTraitBuilder<Background> {
        
        private SetBackgroundBuilder(Background background, int rating) {
            super(background, rating);
        }
        
        public final SetTrait getEvent() {
            return new SetBackground(idSupplier.get(), TraitChangeStatus.PENDING, 
                                     CharacterBackground.backgroundFor(trait, rating, specialization, focuses));
        }
    }
    
    public class SetMeritBuilder {
        
        private Merit<?> merit;
        private String specialization;

        private SetMeritBuilder(Merit<?> merit) {
            this(merit, null);
        }
        
        private SetMeritBuilder(Merit<?> merit, String specialization) {
            this.merit = merit;
            this.specialization = specialization;
        }
        
        public SetMeritBuilder withSpecialization(String specialization) {
            return new SetMeritBuilder(merit, specialization);
        }
        
        public final SetTrait getEvent() {
            return new SetMerit(idSupplier.get(), TraitChangeStatus.PENDING, merit, specialization);
        }
    }
    
    public SetMeritBuilder setMerit(Merit<?> merit) {
        return new SetMeritBuilder(merit);
    }
    
    public class SetFlawBuilder {
        
        private Flaw<?> flaw;
        private String specialization;

        private SetFlawBuilder(Flaw<?> flaw) {
            this(flaw, null);
        }
        
        private SetFlawBuilder(Flaw<?> flaw, String specialization) {
            this.flaw = flaw;
            this.specialization = specialization;
        }
        
        public SetFlawBuilder withSpecialization(String specialization) {
            return new SetFlawBuilder(flaw, specialization);
        }
        
        public final SetTrait getEvent() {
            return new SetFlaw(idSupplier.get(), TraitChangeStatus.PENDING, flaw, specialization);
        }
    }

    public SetFlawBuilder setFlaw(Flaw<?> flaw) {
        return new SetFlawBuilder(flaw);
    }
    
    public SetTrait gainXp(int xp, SetTrait trait) {
        return changeXp(-1 * xp, trait);
    }

    public SetTrait spendXp(int xp, SetTrait trait) {
        return changeXp(xp, trait);
    }
    
    private SetTrait changeXp(int xp, SetTrait trait) {
        return new SetXp(idSupplier.get(), TraitChangeStatus.PENDING, xp, trait);
    }

    public SetTrait setDiscipline(Discipline power, int rating) {
        return new SetDiscipline(idSupplier.get(), TraitChangeStatus.PENDING, power, rating);
    }

    public SetTrait setThaumaturgy(Thaumaturgy power, int rating) {
        return new SetThaumaturgy(idSupplier.get(), TraitChangeStatus.PENDING, power, rating);
    }
    
    public SetTrait setNecromancy(Necromancy power, int rating) {
        return new SetNecromancy(idSupplier.get(), TraitChangeStatus.PENDING, power, rating);
    }

    public SetTrait setThaumaturgicalRitual(ThaumaturgicalRitual power) {
        return new SetThaumaturgicalRitual(idSupplier.get(), TraitChangeStatus.PENDING, power);
    }

    public SetTrait setNecromanticRitual(NecromanticRitual power) {
        return new SetNecromanticRitual(idSupplier.get(), TraitChangeStatus.PENDING, power);
    }

    public SetTrait setElderPower(ElderPower power) {
        return new SetElderPower(idSupplier.get(), TraitChangeStatus.PENDING, power);
    }

    public SetTrait setTechnique(Technique power) {
        return new SetTechnique(idSupplier.get(), TraitChangeStatus.PENDING, power);
    }
}
