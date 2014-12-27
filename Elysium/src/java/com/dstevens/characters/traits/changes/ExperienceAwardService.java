package com.dstevens.characters.traits.changes;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.characters.traits.experience.ChangeExperience;
import com.dstevens.characters.traits.experience.EarnExperience;
import com.dstevens.characters.traits.experience.Experience;

@Service
public class ExperienceAwardService {

	private static final int MAX_XP_EARNABLE_PER_MONTH = 10;
	
	private final TraitChangeStatusFactory traitChangeStatusFactory;

	@Autowired
	public ExperienceAwardService(TraitChangeStatusFactory traitChangeStatusFactory) {
		this.traitChangeStatusFactory = traitChangeStatusFactory;
	}
	
	private int experienceAwardedIn(PlayerCharacter character, LocalDateTime yearMonth) {
		return character.getTraitChanges().stream().
			filter((TraitChange<?> t) -> {
				LocalDateTime ldt = LocalDateTime.ofInstant(t.currentStatus().changed(), ZoneId.systemDefault());
				return yearMonth.getYear() == ldt.getYear() && yearMonth.getMonth().equals(ldt.getMonth());
			}).
		    filter((TraitChange<?> t) -> t.trait() instanceof EarnExperience).
		    mapToInt((TraitChange<?> t) -> EarnExperience.class.cast(t.trait()).amountGained()).
		    sum();
	}
	
	private int amountCharacterCanBeAwardedIn(PlayerCharacter character, LocalDateTime yearMonth) {
		return MAX_XP_EARNABLE_PER_MONTH - experienceAwardedIn(character, yearMonth);
	}
	
	public PlayerCharacter awardCharacter(PlayerCharacter character, int xp, Date date) {
		LocalDateTime yearMonth = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		int xpToAward = Math.min(amountCharacterCanBeAwardedIn(character, yearMonth), xp);
		TraitChange<Experience> experienceEarned = ChangeExperience.award(xpToAward).statusChanged(traitChangeStatusFactory.pendingAt(date));
		return character.withTraitChangeEvent(experienceEarned).approvePendingChange(experienceEarned, traitChangeStatusFactory.appliedOn(date));
	}
}
