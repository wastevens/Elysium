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

public enum Traits {

	CLANS(Clan.values()),
	BLOODLINES(Bloodline.values()),
	ATTRIBUTE(Attribute.values()),
	MENTAL_FOCUS(MentalAttributeFocus.values()),
	PHYSICAL_FOCUS(PhysicalAttributeFocus.values()),
	SOCIAL_FOCUS(SocialAttributeFocus.values()),
	BACKGROUNDS(Background.values()),
	FLAW(Flaw.values()),
	MERIT(Merit.values()),
	DISCIPLINE(Discipline.values()),
	ELDER_POWER(ElderPower.values()),
	TECHNIQUE(Technique.values()),
	NECROMANTIC_RITUAL(NecromanticRitual.values()),
	THAUMATURGICAL_RITUAL(ThaumaturgicalRitual.values()),
	SKILLS(Skill.values()),
	STATUS(Status.values()),
	;
	
	public final Trait[] traits;

	private Traits(Trait[] traits) {
		this.traits = traits;
	}
}
