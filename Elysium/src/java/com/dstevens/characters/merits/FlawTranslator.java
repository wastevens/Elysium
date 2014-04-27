package com.dstevens.characters.merits;

import java.util.Set;

import org.reflections.Reflections;

public class FlawTranslator {

    private FlawTranslator() {
    }
    
    public static final Flaw<?> ofTypeWithId(String type, int id) {
        Reflections reflections = new Reflections("com.dstevens.characters.merits");
        Set<Class<?>> flawClasses = reflections.getTypesAnnotatedWith(FlawAnnotation.class);
        for (Class<?> flawClass : flawClasses) {
            if (flawClass.getAnnotation(FlawAnnotation.class).value().equals(type)) {
                return getFlaw(id, flawClass);
            }
        }
        throw new IllegalStateException("Could not find a merit of type " + type + " with id " + id);
    }

    @SuppressWarnings({ "unchecked" })
    private static Flaw<?> getFlaw(int id, Class<?> flawClass) {
        return ((Class<? extends Flaw<?>>) flawClass).getEnumConstants()[id];
    }
    
    public static final String ofType(Enum<? extends Flaw<?>> flaw) {
        return flaw.getClass().getAnnotation(FlawAnnotation.class).value();
    }
    
    public static final int withId(Enum<? extends Flaw<?>> flaw) {
        return flaw.ordinal();
    }
    
}
