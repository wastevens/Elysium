package com.dstevens.character.trait.change;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;

import com.dstevens.character.PlayerCharacter;
import com.dstevens.character.trait.experience.ExperienceAward;

@Service
public class ExperienceAwardService {

	private static final LocalDate CHRONICLE_START = LocalDate.of(2014, 2, 1);

	public PlayerCharacter setGroundExperienceOn(PlayerCharacter character, LocalDate now) {
		return character.withGroundXp(monthsBetweenChronicleStartAndBecomingPrimary(character) * character.getCurrentStatus().status().groundXpPerMonth());
	}

	private int monthsBetweenChronicleStartAndBecomingPrimary(PlayerCharacter character) {
		return Period.between(CHRONICLE_START, character.getCurrentStatus().changedOn()).getMonths();
	}
	
	public PlayerCharacter awardCharacter(PlayerCharacter character, int xp, LocalDate yearMonth, String awardFor) {
		return character.addExperienceAward(new ExperienceAward(xpToAward(character, xp, yearMonth), yearMonth, awardFor));
	}

	private int xpToAward(PlayerCharacter character, int xp, LocalDate yearMonth) {
		return Math.min(amountCharacterCanBeAwardedIn(character, yearMonth), xp);
	}
	
	private int amountCharacterCanBeAwardedIn(PlayerCharacter character, LocalDate yearMonth) {
		return character.getCurrentStatus().status().maxXpPerMonth() - experienceAwardedIn(character, yearMonth);
	}
	
	private int experienceAwardedIn(PlayerCharacter character, LocalDate yearMonth) {
		return character.getExperienceAwards().stream().
			filter((ExperienceAward t) -> yearMonth.getYear() == t.awardedOn().getYear() && yearMonth.getMonth().equals(t.awardedOn().getMonth())).
		    mapToInt((ExperienceAward t) -> t.experience()).
		    sum();
	}
}
