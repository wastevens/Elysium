package com.dstevens.characters.changes;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface TraitType {

    Class<? extends Enum<?>> type();
    
}
