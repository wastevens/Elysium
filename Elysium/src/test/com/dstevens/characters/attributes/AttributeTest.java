package com.dstevens.characters.attributes;

import static com.dstevens.collections.Lists.*;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Test;

import com.dstevens.testing.EqualityTester;

public class AttributeTest {

    @Test
    public void testEquals() {
        AttributeFocus focus = mock(AttributeFocus.class);
        AttributeFocus anotherFocus = mock(AttributeFocus.class);
        AttributeFocus yetAnotherFocus = mock(AttributeFocus.class);
        List<AttributeFocus> focuses = list(focus, anotherFocus);
        List<AttributeFocus> anotherFocuses = listFrom(focuses);
        anotherFocuses.add(yetAnotherFocus);
        int rating = 5;
        int anotherRating = rating + 1;
        
        EqualityTester.testing(new Attribute(rating, focuses)).
                 assertEqualTo(new Attribute(rating, focuses)).
                 assertNotEqualTo(new Attribute(anotherRating, focuses)).
                 assertNotEqualTo(new Attribute(rating, anotherFocuses)).
                 assertNotEqualTo("Not a AttributeTest");
    }
    
}
