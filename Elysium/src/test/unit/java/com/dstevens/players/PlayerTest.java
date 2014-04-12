package com.dstevens.players;

import static com.dstevens.collections.Lists.*;
import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.Test;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.collections.Sets;

public class PlayerTest {

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
        Player p1 = player("Andrew").delete(new Date(1001L));
        Player p2 = player("Alice").delete(new Date(1001L));;
        Player p3 = player("Ace").delete(new Date(1001L));;
        Player p4 = player("Abby").delete(new Date(1001L));;
        
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
        Player deletedP1 = player("Alice").delete(new Date(1000L));
        Player deletedP2 = player("Betty").delete(new Date(1000L));
        Player deletedP3 = player("Chris").delete(new Date(1000L));
        Player deletedP4 = player("David").delete(new Date(1000L));
        
        List<Player> initialList = list(deletedP2, deletedP4, deletedP1, deletedP3, p2, p4, p1, p3);
        List<Player> expectedList = list(p1, p2, p3, p4, deletedP1, deletedP2, deletedP3, deletedP4);
        assertEquals(expectedList, sort(initialList));
    }
    
    private Player player(String name) {
        return new Player("", name, "", Sets.<Troupe>set(), Sets.<PlayerCharacter>set());
    }
    
}
