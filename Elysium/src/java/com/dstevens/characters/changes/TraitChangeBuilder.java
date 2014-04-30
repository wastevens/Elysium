package com.dstevens.characters.changes;

import static com.dstevens.collections.Sets.set;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.dstevens.characters.backgrounds.*;
import com.dstevens.characters.distinctions.*;
import com.dstevens.characters.powers.*;
import com.dstevens.characters.powers.magics.*;
import com.dstevens.characters.skills.*;

@Service
public class TraitChangeBuilder {

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
            return new SetCharacterDefinedTrait(TraitChangeStatus.PENDING, 
                              CharacterSkill.skillFor(trait, rating, specialization, focuses),
                              TraitFactory.SKILL);
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
            return new SetCharacterDefinedTrait(TraitChangeStatus.PENDING, 
                              CharacterBackground.backgroundFor(trait, rating, specialization, focuses),
                              TraitFactory.BACKGROUND);
        }
    }
    
    public class SetMeritBuilder {
        
        private Merit<?> merit;
        private String specialization;
        private SetTrait associatedTrait;

        private SetMeritBuilder(Merit<?> merit) {
            this(merit, null, null);
        }
        
        private SetMeritBuilder(Merit<?> merit, String specialization, SetTrait associatedTrait) {
            this.merit = merit;
            this.specialization = specialization;
            this.associatedTrait = associatedTrait;
        }
        
        public SetMeritBuilder withSpecialization(String specialization) {
            return new SetMeritBuilder(merit, specialization, associatedTrait);
        }
        
        public SetMeritBuilder withTrait(SetTrait associatedTrait) {
            return new SetMeritBuilder(merit, specialization, associatedTrait);
        }
        
        public final SetTrait getEvent() {
            return new SetDistinction(TraitChangeStatus.PENDING, merit, specialization, associatedTrait, DistinctionFactory.MERIT);
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
            return new SetDistinction(TraitChangeStatus.PENDING, flaw, specialization, DistinctionFactory.FLAW);
        }
    }

    public SetFlawBuilder setFlaw(Flaw<?> flaw) {
        return new SetFlawBuilder(flaw);
    }
    
    public SetTrait gainXp(int xp, SetTrait trait) {
        return new GainXp(TraitChangeStatus.PENDING, xp, trait);
    }

    public SetTrait spendXp(int xp, SetTrait trait) {
        return new SpendXp(TraitChangeStatus.PENDING, xp, trait);
    }

    public SetTrait setDiscipline(Discipline power, int rating) {
        return new SetRatedTrait(TraitChangeStatus.PENDING, power, rating, TraitFactory.DISCIPLINE);
    }

    public SetTrait setThaumaturgy(Thaumaturgy power, int rating) {
        return new SetRatedTrait(TraitChangeStatus.PENDING, power, rating, TraitFactory.THAUMATURGY);
    }
    
    public SetTrait setNecromancy(Necromancy power, int rating) {
        return new SetRatedTrait(TraitChangeStatus.PENDING, power, rating, TraitFactory.NECROMANCY);
    }

    public SetTrait setThaumaturgicalRitual(ThaumaturgicalRitual power) {
        return new SetEnumeratedTrait(TraitChangeStatus.PENDING, power, TraitFactory.THAUMATURGICAL_RITUAL);
    }

    public SetTrait setNecromanticRitual(NecromanticRitual power) {
        return new SetEnumeratedTrait(TraitChangeStatus.PENDING, power, TraitFactory.NECROMANTIC_RITUAL);
    }

    public SetTrait setElderPower(ElderPower power) {
        return new SetEnumeratedTrait(TraitChangeStatus.PENDING, power, TraitFactory.ELDER_POWER);
    }

    public SetTrait setTechnique(Technique power) {
        return new SetEnumeratedTrait(TraitChangeStatus.PENDING, power, TraitFactory.TECHNIQUE);
    }
}
