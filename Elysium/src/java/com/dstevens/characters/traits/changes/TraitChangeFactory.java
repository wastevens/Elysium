package com.dstevens.characters.traits.changes;

import java.util.Set;

import com.dstevens.characters.clans.Bloodline;
import com.dstevens.characters.clans.Clan;
import com.dstevens.characters.traits.attributes.Attribute;
import com.dstevens.characters.traits.attributes.focuses.AttributeFocus;
import com.dstevens.characters.traits.backgrounds.Background;
import com.dstevens.characters.traits.distinctions.flaws.Flaw;
import com.dstevens.characters.traits.distinctions.merits.Merit;
import com.dstevens.characters.traits.powers.disciplines.Discipline;
import com.dstevens.characters.traits.powers.disciplines.ElderPower;
import com.dstevens.characters.traits.powers.disciplines.Technique;
import com.dstevens.characters.traits.powers.magic.Ritual;
import com.dstevens.characters.traits.skills.Skill;
import com.dstevens.characters.traits.status.Status;

public interface TraitChangeFactory {

	TraitChange<?> clan(Clan clan);
	TraitChange<?> bloodline(Bloodline bloodline);
	
	TraitChange<?> attribute(Attribute attribute, int rating);
	
	TraitChange<?> focus(AttributeFocus focus);

	TraitChange<?> skill(Skill skill, int rating, String specialization, Set<String> focuses);

	TraitChange<?> background(Background background, int rating, String specialization, Set<String> focuses);

	TraitChange<?> power(Discipline power, int rating);

	TraitChange<?> ritual(Ritual<?> ritual);

	TraitChange<?> technique(Technique technique);

	TraitChange<?> elderPower(ElderPower power);

	TraitChange<?> inClanPower(Discipline power);
	
	TraitChange<?> merit(Merit merit, String specialization, TraitChange<?> associatedTrait);
	
	TraitChange<?> flaw(Flaw flaw, String specialization, TraitChange<?> associatedTrait);

	TraitChange<?> status(Status awesome, String string);

}