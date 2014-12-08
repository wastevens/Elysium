package com.dstevens.characters.traits;

public enum ExperienceMovement {

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
