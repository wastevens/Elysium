package com.dstevens.characters.traits.experience;

public enum ExperienceMovement {

	AWARD {
		@Override
		public Experience changeExperience(int xp) {
			return new EarnExperience(xp);
		}
	},
	GAIN {
		@Override
		public Experience changeExperience(int xp) {
			return new GainExperience(xp);
		}
	},
	SPEND {
		@Override
		public Experience changeExperience(int xp) {
			return new SpendExperience(xp);
		}
	};

	public abstract Experience changeExperience(int xp);
	
}
