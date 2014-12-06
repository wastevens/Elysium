package com.dstevens.characters.traits;

import java.util.Comparator;
import java.util.Set;

public interface CharacterFocusedTrait {

	Set<String> getFocuses();
	
	default Comparator<CharacterFocusedTrait> byFocuses() {
        return new Comparator<CharacterFocusedTrait>() {
            @Override
            public int compare(CharacterFocusedTrait o1, CharacterFocusedTrait o2) {
                String[] array1 = (String[]) o1.getFocuses().stream().sorted().toArray();
                String[] array2 = (String[]) o1.getFocuses().stream().sorted().toArray();
                if (array1.length != array2.length) return array1.length - array2.length;
                for(int i=0;i<array1.length;i++) {
                    if (array1[i].compareTo(array2[i]) != 0) {
                        return array1[i].compareTo(array2[i]);
                    }
                }
                return 0;
            }
        };
    }
	
}
