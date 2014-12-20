package com.dstevens.players;

import static com.dstevens.collections.Lists.list;
import static com.dstevens.collections.Lists.sort;
import static com.dstevens.testing.Assertions.assertListsEqual;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.dstevens.testing.EqualityTester;

public class TroupeTest {

    @Test
    public void testEquals() {
        String id = "id";
        String name = "name";
        Setting setting = Setting.CAMARILLA;
        Setting anotherSetting = Setting.values()[setting.ordinal()+1];
        
        EqualityTester.testing(new Troupe(id, name, setting)).
                 assertEqualTo(new Troupe(id, name, setting)).
                 assertEqualTo(new Troupe(id, "Another " + name, setting)).
                 assertEqualTo(new Troupe(id, name, anotherSetting)).
              assertNotEqualTo(new Troupe("Another " + id, name, setting)).
        assertNotEqualTo("Not a enclosing_type");
    }
    
    @Test
    public void testOrdersByName() {
        Troupe p1 = troupe("Alice");
        Troupe p2 = troupe("Betty");
        Troupe p3 = troupe("Chris");
        Troupe p4 = troupe("David");
        
        List<Troupe> initialList = list(p2, p4, p1, p3);
        List<Troupe> expectedList = list(p1, p2, p3, p4);
        assertEquals(expectedList, sort(initialList));
    }
    
    @Test
    public void testOrdersByDeletedTimestamp() {
        Troupe p1 = troupe("David").delete(new Date(1001L));
        Troupe p2 = troupe("Chris").delete(new Date(1002L));;
        Troupe p3 = troupe("Betty").delete(new Date(1003L));;
        Troupe p4 = troupe("Alice").delete(new Date(1004L));;
        
        List<Troupe> initialList = list(p2, p4, p1, p3);
        List<Troupe> expectedList = list(p1, p2, p3, p4);
        assertEquals(expectedList, sort(initialList));
    }
    
    @Test
    public void testOrdersByNameWhenDeletedTimestampsEqual() {
        Troupe p1 = troupe("Alice").delete(new Date(1000L));
        Troupe p2 = troupe("Betty").delete(new Date(1000L));;
        Troupe p3 = troupe("Chris").delete(new Date(1000L));;
        Troupe p4 = troupe("David").delete(new Date(1000L));;
        
        List<Troupe> initialList = list(p2, p4, p1, p3);
        List<Troupe> expectedList = list(p1, p2, p3, p4);
        assertEquals(expectedList, sort(initialList));
    }
    
    @Test
    public void testOrdersByTroupesWithDeletedTimestampsToTheBack() {
        Troupe p1 = troupe("Eugene");
        Troupe p2 = troupe("Fred");
        Troupe p3 = troupe("George");
        Troupe p4 = troupe("Harry");
        Troupe deletedP1 = troupe("Alice").delete(new Date(1001L));
        Troupe deletedP2 = troupe("Betty").delete(new Date(1002L));
        Troupe deletedP3 = troupe("Chris").delete(new Date(1003L));
        Troupe deletedP4 = troupe("David").delete(new Date(1004L));
        
        List<Troupe> initialList = list(deletedP2, deletedP4, deletedP1, deletedP3, p2, p4, p1, p3);
        List<Troupe> expectedList = list(p1, p2, p3, p4, deletedP1, deletedP2, deletedP3, deletedP4);
        assertListsEqual(expectedList, sort(initialList));
    }
    
    private Troupe troupe(String name) {
        return new Troupe(name, name, Setting.CAMARILLA);
    }
    
}
