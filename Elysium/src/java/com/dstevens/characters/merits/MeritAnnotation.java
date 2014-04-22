package com.dstevens.characters.merits;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MeritAnnotation {

    String value();
    
}
