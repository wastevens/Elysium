package com.dstevens.characters;

import static org.mockito.Mockito.mock;
import org.junit.Test;

import com.dstevens.players.*;
import com.dstevens.testing.EqualityTester;

public class PlayerCharacterTest {

    private static final String ID = "some id";
    private static final String NAME = "some name";

    @Test
    public void testIdentityEquality() {
        Troupe troupe = mock(Troupe.class);
        Troupe anotherTroupe = mock(Troupe.class);
        Player player = mock(Player.class);
        Player anotherPlayer = mock(Player.class);
        
        EqualityTester.testing(new PlayerCharacter(ID, NAME)).
                 assertEqualTo(new PlayerCharacter(ID, NAME)).
                 assertEqualTo(new PlayerCharacter(ID, NAME)).
                 assertEqualTo(new PlayerCharacter(ID, NAME)).
                 assertEqualTo(new PlayerCharacter(ID, "another " + NAME));
    }
    
}
