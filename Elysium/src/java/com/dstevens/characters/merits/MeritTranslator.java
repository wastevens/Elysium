package com.dstevens.characters.merits;

import java.util.Set;

import org.reflections.Reflections;

public class MeritTranslator {

    private MeritTranslator() {
    }
    
    public static final Merit<?> ofTypeWithId(String type, int id) {
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
    private static Merit<?> getMerit(int id, Class<?> meritClass) {
        return ((Class<? extends Merit<?>>) meritClass).getEnumConstants()[id];
    }
    
    public static final String ofType(Merit<?> merit) {
        return merit.getType();
    }
    
    public static final int withId(Merit<?> merit) {
        return merit.ordinal();
    }
    
}
