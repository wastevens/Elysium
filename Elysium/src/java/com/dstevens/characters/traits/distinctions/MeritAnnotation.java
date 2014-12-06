package com.dstevens.characters.traits.distinctions;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MeritAnnotation {

    String value();
    
}
