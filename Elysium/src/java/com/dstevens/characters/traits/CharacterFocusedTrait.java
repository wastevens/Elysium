package com.dstevens.characters.traits;

import java.util.Comparator;
import java.util.Set;

public interface CharacterFocusedTrait<T extends Enum<?>> extends EnumeratedTrait<T> {

	Set<String> getFocuses();
	
	default Comparator<? super CharacterFocusedTrait<T>> byFocuses() {
        return new Comparator<CharacterFocusedTrait<T>>() {
            @Override
            public int compare(CharacterFocusedTrait<T> o1, CharacterFocusedTrait<T> o2) {
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
