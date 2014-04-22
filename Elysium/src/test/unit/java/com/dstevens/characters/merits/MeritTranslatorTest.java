package com.dstevens.characters.merits;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MeritTranslatorTest {

    @Test
    public void testTranslation() {
        RarityMerit someMerit = RarityMerit.RARE;
        
        String type = MeritTranslator.ofType(someMerit);
        int id = MeritTranslator.withId(someMerit);
        
        assertEquals(someMerit, MeritTranslator.ofTypeWithId(type, id));
    }
}
