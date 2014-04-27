package com.dstevens.players;

import static com.dstevens.collections.Lists.*;
import static com.dstevens.collections.Sets.set;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.*;

import org.junit.Test;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.collections.Sets;
import com.dstevens.testing.EqualityTester;

public class PlayerTest {

    @Test
    public void testEquals() {
        PlayerCharacter character1 = mock(PlayerCharacter.class);
        PlayerCharacter character2 = mock(PlayerCharacter.class);
        PlayerCharacter character3 = mock(PlayerCharacter.class);
        PlayerCharacter character4 = mock(PlayerCharacter.class);
        String id = "id";
        String name = "name";
        String email = "email";
        
        EqualityTester.testing(new Player(id, name, email, set(character1, character2, character3))).
                 assertEqualTo(new Player(id, "another " + name, email, set(character1, character2, character3))).
                 assertEqualTo(new Player(id, name, "another " + email, set(character1, character2, character3))).
                 assertEqualTo(new Player(id, name, email, set(character1, character2, character3))).
                 assertEqualTo(new Player(id, name, email, set(character1, character2, character3, character4))).
                 assertEqualTo(new Player(id, name, email, set(character1, character2))).
              assertNotEqualTo(new Player("another " + id, name, email, set(character1, character2, character3))).
        assertNotEqualTo("Not a enclosing_type");
    }
    
    @Test
    public void testOrdersByName() {
        Player p1 = player("Alice");
        Player p2 = player("Betty");
        Player p3 = player("Chris");
        Player p4 = player("David");
        
        List<Player> initialList = list(p2, p4, p1, p3);
        List<Player> expectedList = list(p1, p2, p3, p4);
        assertEquals(expectedList, sort(initialList));
    }
    
    @Test
    public void testOrdersByDeletedTimestamp() {
        Player p1 = player("David").delete(new Date(1001L));
        Player p2 = player("Chris").delete(new Date(1002L));
        Player p3 = player("Betty").delete(new Date(1003L));
        Player p4 = player("Alice").delete(new Date(1004L));
        
        List<Player> initialList = list(p2, p4, p1, p3);
        List<Player> expectedList = list(p1, p2, p3, p4);
        assertEquals(expectedList, sort(initialList));
    }
    
    @Test
    public void testOrdersByNameWhenDeletedTimestampsEqual() {
        Player p1 = player("Alice").delete(new Date(1000L));
        Player p2 = player("Betty").delete(new Date(1000L));
        Player p3 = player("Chris").delete(new Date(1000L));
        Player p4 = player("David").delete(new Date(1000L));
        
        List<Player> initialList = list(p2, p4, p1, p3);
        List<Player> expectedList = list(p1, p2, p3, p4);
        assertEquals(expectedList, sort(initialList));
    }
    
    @Test
    public void testOrdersByDeletedTimestampPresenceThenByName() {
        Player p1 = player("Eugene");
        Player p2 = player("Fred");
        Player p3 = player("George");
        Player p4 = player("Harry");
        Player deletedP1 = player("David").delete(new Date(1001L));
        Player deletedP2 = player("Chris").delete(new Date(1002L));
        Player deletedP3 = player("Betty").delete(new Date(1003L));
        Player deletedP4 = player("Alice").delete(new Date(1004L));
        
        List<Player> initialList = list(deletedP2, deletedP4, deletedP1, deletedP3, p2, p4, p1, p3);
        List<Player> expectedList = list(p1, p2, p3, p4, deletedP1, deletedP2, deletedP3, deletedP4);
        List<Player> sort = sort(initialList);
        assertEquals(expectedList, sort);
    }
    
    private Player player(String name) {
        return new Player(name, name, "", Sets.<PlayerCharacter>set());
    }
    
}
