package com.dstevens.characters;

import org.junit.Test;

import com.dstevens.testing.EqualityTester;

public class PlayerCharacterTest {

    private static final String ID = "some id";
    private static final String NAME = "some name";

    @Test
    public void testIdentityEquality() {
        EqualityTester.testing(new PlayerCharacter(ID, NAME)).
                 assertEqualTo(new PlayerCharacter(ID, "another " + NAME)).
              assertNotEqualTo(new PlayerCharacter("another " + ID, NAME))
                 ;
    }
    
}
