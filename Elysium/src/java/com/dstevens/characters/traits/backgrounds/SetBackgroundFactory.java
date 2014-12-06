package com.dstevens.characters.traits.backgrounds;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.dstevens.characters.traits.TraitChangeStatus;

@Service
public class SetBackgroundFactory {
   
    public SetBackground setBackgroundFor(Background background, int rating, String specialization, Set<String> focuses) {
    	return new SetBackground(TraitChangeStatus.PENDING, CharacterBackground.backgroundFor(background, rating, specialization, focuses));
    }
}
