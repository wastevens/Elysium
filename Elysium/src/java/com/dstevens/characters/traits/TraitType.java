package com.dstevens.characters.traits;

import com.dstevens.characters.clans.Bloodline;
import com.dstevens.characters.clans.Clan;
import com.dstevens.characters.traits.attributes.Attribute;
import com.dstevens.characters.traits.attributes.focuses.MentalAttributeFocus;
import com.dstevens.characters.traits.attributes.focuses.PhysicalAttributeFocus;
import com.dstevens.characters.traits.attributes.focuses.SocialAttributeFocus;
import com.dstevens.characters.traits.backgrounds.Background;
import com.dstevens.characters.traits.distinctions.flaws.Flaw;
import com.dstevens.characters.traits.distinctions.merits.Merit;
import com.dstevens.characters.traits.powers.disciplines.Discipline;
import com.dstevens.characters.traits.powers.disciplines.ElderPower;
import com.dstevens.characters.traits.powers.disciplines.Technique;
import com.dstevens.characters.traits.powers.magic.necromancy.NecromanticRitual;
import com.dstevens.characters.traits.powers.magic.thaumaturgy.ThaumaturgicalRitual;
import com.dstevens.characters.traits.skills.Skill;
import com.dstevens.characters.traits.status.Status;
import com.dstevens.utilities.Identified;

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
	
	@Override
	public Integer getId() {
		return id;
	}
}
