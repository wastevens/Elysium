package com.dstevens.characters.traits.changes;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.status.ActivityStatus;
import com.dstevens.characters.traits.experience.ExperienceAward;

@Service
public class ExperienceAwardService {

	private static final int GROUND_XP_PER_MONTH = 4;
	private static final LocalDate CHRONICLE_START = LocalDate.of(2014, 2, 1);

	public PlayerCharacter setGroundExperienceOn(PlayerCharacter character, LocalDate now) {
		if(character.getCurrentStatus().status().equals(ActivityStatus.PRIMARY)) {
			return character.withGroundXp(monthsBetweenChronicleStartAndBecomingPrimary(character) * GROUND_XP_PER_MONTH);
		}
		return character.withGroundXp(0);
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
