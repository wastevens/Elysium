package com.dstevens.characters.merits;

import java.util.*;
import java.util.function.Function;

import javax.persistence.*;
import org.reflections.Reflections;

import com.dstevens.utilities.ObjectExtensions;

@Embeddable
public class CharacterMerit implements Comparable<CharacterMerit> {

    @Column(name="merit_id")
    private int meritId;
    
    @Column(name="merit_type")
    private String meritType;
    
    @Column(name="details")
    private String details;
    
    //Hibernate only
    @Deprecated
    @SuppressWarnings("unused")
    private CharacterMerit() {
        this(0, null, null);
    }
    
    public CharacterMerit(Enum<? extends Merit> merit) {
        this(MeritTranslator.withId(merit), MeritTranslator.ofType(merit), null);
    }
    
    public CharacterMerit(Enum<? extends Merit> merit, String details) {
        this(MeritTranslator.withId(merit), MeritTranslator.ofType(merit), details);
    }
    
    private CharacterMerit(int meritId, String meritType, String details) {
        this.meritId = meritId;
        this.meritType = meritType;
        this.details = details;
    }
    
    public Merit getMerit() {
        return MeritTranslator.ofTypeWithId(meritType, meritId);
    }
    
    public String getDetails() {
        return details;
    }
    
    @Override
    public boolean equals(Object that) {
        return ObjectExtensions.equals(this, that);
    }
    
    @Override
    public int hashCode() {
        return ObjectExtensions.hashCodeFor(this);
    }
    
    @Override
    public String toString() {
        return ObjectExtensions.toStringFor(this);
    }

    @Override
    public int compareTo(CharacterMerit that) {
        Function<CharacterMerit, String> byMeritType = ((CharacterMerit cm) -> cm.meritType);
        Function<CharacterMerit, Integer> byMeritId = ((CharacterMerit cm) -> cm.meritId);
        Function<CharacterMerit, String> byDetails = ((CharacterMerit cm) -> cm.details);
        return Comparator.comparing(byMeritType).thenComparing(byMeritId).thenComparing(byDetails).compare(this, that);
    }
    
    private static final class MeritTranslator {

        private MeritTranslator() {
        }
        
        public static final Merit ofTypeWithId(String type, int id) {
            Reflections reflections = new Reflections("com.dstevens.characters.merits");
            Set<Class<?>> meritClasses = reflections.getTypesAnnotatedWith(MeritAnnotation.class);
            for (Class<?> meritClass : meritClasses) {
                if (meritClass.getAnnotation(MeritAnnotation.class).value().equals(type)) {
                    return getMerit(id, meritClass);
                }
            }
            throw new IllegalStateException("Could not find a merit of type " + type + " with id " + id);
        }

        @SuppressWarnings("unchecked")
        private static Merit getMerit(int id, Class<?> meritClass) {
            return ((Class<? extends Merit>) meritClass).getEnumConstants()[id];
        }
        
        public static final String ofType(Enum<? extends Merit> merit) {
            return merit.getClass().getAnnotation(MeritAnnotation.class).value();
        }
        
        public static final int withId(Enum<? extends Merit> merit) {
            return merit.ordinal();
        }
        
    }

    
}
