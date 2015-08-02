package com.dstevens.character.trait;

import com.dstevens.character.clan.Bloodline;
import com.dstevens.character.clan.Clan;
import com.dstevens.character.trait.attribute.Attribute;
import com.dstevens.character.trait.attribute.focus.MentalAttributeFocus;
import com.dstevens.character.trait.attribute.focus.PhysicalAttributeFocus;
import com.dstevens.character.trait.attribute.focus.SocialAttributeFocus;
import com.dstevens.character.trait.background.Background;
import com.dstevens.character.trait.distinction.flaw.Flaw;
import com.dstevens.character.trait.distinction.merit.Merit;
import com.dstevens.character.trait.power.discipline.Discipline;
import com.dstevens.character.trait.power.discipline.ElderPower;
import com.dstevens.character.trait.power.discipline.Technique;
import com.dstevens.character.trait.power.magic.necromancy.NecromanticRitual;
import com.dstevens.character.trait.power.magic.thaumaturgy.ThaumaturgicalRitual;
import com.dstevens.character.trait.skill.Skill;
import com.dstevens.character.trait.status.Status;
import com.dstevens.utilities.Identified;
import com.dstevens.utilities.IdentityUtilities;

public enum TraitType implements Identified<Integer> {

	CLAN(0, Clan.values()),
	BLOODLINE(1, Bloodline.values()),
	ATTRIBUTE(2, Attribute.values()),
	MENTAL_FOCUS(3, MentalAttributeFocus.values()),
	PHYSICAL_FOCUS(4, PhysicalAttributeFocus.values()),
	SOCIAL_FOCUS(5, SocialAttributeFocus.values()),
	BACKGROUND(6, Background.values()),
	FLAW(7, Flaw.values()),
	MERIT(8, Merit.values()),
	DISCIPLINE(9, Discipline.values()),
	ELDER_POWER(10, ElderPower.values()),
	TECHNIQUE(11, Technique.values()),
	NECROMANTIC_RITUAL(12, NecromanticRitual.values()),
	THAUMATURGICAL_RITUAL(13, ThaumaturgicalRitual.values()),
	SKILL(14, Skill.values()),
	STATUS(15, Status.values()),
	;
	
	private final int id;
	public final Trait[] traits;
	
	private TraitType(int id, Trait[] traits) {
		this.id = id;
		this.traits = traits;
	}
	
	public static TraitType from(int id) {
		return IdentityUtilities.withId(id, TraitType.values());
	}
	
	@Override
	public Integer getId() {
		return id;
	}
}
