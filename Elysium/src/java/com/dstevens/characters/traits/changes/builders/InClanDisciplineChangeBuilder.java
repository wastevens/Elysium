package com.dstevens.characters.traits.changes.builders;

import com.dstevens.characters.traits.changes.SetInClanDiscipline;
import com.dstevens.characters.traits.changes.SetInClanNecromancy;
import com.dstevens.characters.traits.changes.SetInClanThaumaturgy;
import com.dstevens.characters.traits.changes.SetTrait;
import com.dstevens.characters.traits.changes.TraitChangeStatus;
import com.dstevens.characters.traits.powers.Discipline;
import com.dstevens.characters.traits.powers.Necromancy;
import com.dstevens.characters.traits.powers.Power;
import com.dstevens.characters.traits.powers.Thaumaturgy;
import com.dstevens.game.TraitChangeBuilder;

public class InClanDisciplineChangeBuilder implements TraitChangeBuilder {

	private Power<?> power;

	public InClanDisciplineChangeBuilder(Power<?> power) {
		this.power = power;
	}

	@Override
	public SetTrait buy() {
		throw new IllegalStateException("You cannot buy a new in clan discipline, only add them as part of buying or adding something else.");
	}


	@Override
	public SetTrait sell() {
		throw new IllegalStateException("You cannot sell an in clan discipline, only remove them as part of buying or adding something else.");
	}
	
	@Override
	public SetTrait add() {
		if(power instanceof Discipline) {
			return new SetInClanDiscipline(TraitChangeStatus.PENDING, (Discipline) power);
		}
		if(power instanceof Thaumaturgy) {
			return new SetInClanThaumaturgy(TraitChangeStatus.PENDING, (Thaumaturgy) power);
		}
		if(power instanceof Necromancy) {
			return new SetInClanNecromancy(TraitChangeStatus.PENDING, (Necromancy) power);
		}
		throw new IllegalArgumentException("Could not create InClanDisciplineChangeBuilder for " + power);
	}

	@Override
	public SetTrait remove() {
		throw new IllegalStateException("not yet implemented");
	}

}
