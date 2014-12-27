package com.dstevens.characters.traits.changes;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.experience.ExperienceAward;

@Service
public class ExperienceAwardService {

	private static final int MAX_XP_EARNABLE_PER_MONTH = 10;
	
	public PlayerCharacter awardCharacter(PlayerCharacter character, int xp, LocalDate yearMonth, String awardFor) {
		return character.addExperienceAward(new ExperienceAward(xpToAward(character, xp, yearMonth), yearMonth, awardFor));
	}

	private int xpToAward(PlayerCharacter character, int xp, LocalDate yearMonth) {
		return Math.min(amountCharacterCanBeAwardedIn(character, yearMonth), xp);
	}
	
	private int amountCharacterCanBeAwardedIn(PlayerCharacter character, LocalDate yearMonth) {
		return MAX_XP_EARNABLE_PER_MONTH - experienceAwardedIn(character, yearMonth);
	}
	
	private int experienceAwardedIn(PlayerCharacter character, LocalDate yearMonth) {
		return character.getExperienceAwards().stream().
			filter((ExperienceAward t) -> yearMonth.getYear() == t.awardedOn().getYear() && yearMonth.getMonth().equals(t.awardedOn().getMonth())).
		    mapToInt((ExperienceAward t) -> t.experience()).
		    sum();
	}
}
