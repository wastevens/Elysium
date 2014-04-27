package com.dstevens.characters;

import com.dstevens.characters.backgrounds.CharacterBackground;
import com.dstevens.characters.skills.CharacterSkill;

public class PlayerCharacterDisplayer {

    public String display(PlayerCharacter character) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Character:\t%1$s\n", character.getName())).
                append(String.format("Clan:\t\t %1$s\n", character.getClan().name())).
                append(String.format("Bloodline:\t%1$s\n", character.getBloodline().name())).
                append("Attributes\n").
                append(String.format("Physical:\t %1$s %2$s\n", character.getPhysicalAttribute().getRating(), character.getPhysicalAttribute().getFocuses())).
                append(String.format("Social:\t\t %1$s %2$s\n", character.getSocialAttribute().getRating(), character.getSocialAttribute().getFocuses())).
                append(String.format("Mental:\t\t %1$s %2$s\n", character.getMentalAttribute().getRating(), character.getMentalAttribute().getFocuses()));
        
        builder.append("Skills\n");
        character.getSkills().stream().sorted().map(((CharacterSkill s) -> display(s))).forEach((String s) -> builder.append(s));
        builder.append("Backgrounds\n");
        character.getBackgrounds().stream().sorted().map(((CharacterBackground bg) -> display(bg))).forEach((String s) -> builder.append(s));
        
        return builder.toString();
    }

    private String display(CharacterBackground bg) {
        if (isPresent(bg.getSpecialization())) {
            return String.format("%1$s (%2$s):\t%3$s\n", bg.trait(), bg.getSpecialization(), bg.rating());
        }
        if (!bg.getFocuses().isEmpty()) {
            return String.format("%1$s:\t%2$s %3$s\n", bg.trait(), bg.rating(), bg.getFocuses());
        }
        return String.format("%1$s:\t%2$s\n", bg.trait(), bg.rating());
    }

    private String display(CharacterSkill s) {
        if (isPresent(s.getSpecialization())) {
            return String.format("%1$s (%2$s):\t %3$s\n", s.trait(), s.getSpecialization(), s.rating());
        }
        if (!s.getFocuses().isEmpty()) {
            return String.format("%1$s:\t%2$s %3$s\n", s.trait(), s.rating(), s.getFocuses());
        }
        return String.format("%1$s:\t%2$s\n", s.trait(), s.rating());
    }

    private boolean isPresent(String specialization) {
        return specialization != null && !specialization.isEmpty();
    }

    
}
