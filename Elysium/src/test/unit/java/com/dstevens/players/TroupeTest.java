package com.dstevens.players;

import static com.dstevens.collections.Sets.set;
import static org.mockito.Mockito.mock;
import org.junit.Test;

import com.dstevens.testing.EqualityTester;

public class TroupeTest {

    @Test
    public void testEquals() {
        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);
        Player player3 = mock(Player.class);
        Player player4 = mock(Player.class);
        String id = "id";
        String name = "name";
        Setting setting = Setting.CAMARILLA;
        Setting anotherSetting = Setting.values()[setting.ordinal()+1];
        
        EqualityTester.testing(new Troupe(id, name, setting, set(player1, player2, player3))).
                 assertEqualTo(new Troupe(id, name, setting, set(player1, player2, player3))).
                 assertEqualTo(new Troupe(id, "Another " + name, setting, set(player1, player2, player3))).
                 assertEqualTo(new Troupe(id, name, anotherSetting, set(player1, player2, player3))).
                 assertEqualTo(new Troupe(id, name, setting, set(player1, player2))).
                 assertEqualTo(new Troupe(id, name, setting, set(player1, player2, player3, player4))).
              assertNotEqualTo(new Troupe("Another " + id, name, setting, set(player1, player2, player3))).
        assertNotEqualTo("Not a enclosing_type");
    }
    
}
