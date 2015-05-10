package com.dstevens.characters.traits;

import org.junit.Test;

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

import static org.junit.Assert.assertEquals;

public class TraitTest {

	@Test
	public void testThatNoIdsWereMissed() {
		assertNoIdsMissedFor(Attribute.class);
		assertNoIdsMissedFor(Background.class);
		assertNoIdsMissedFor(Bloodline.class);
		assertNoIdsMissedFor(Clan.class);
		assertNoIdsMissedFor(Discipline.class);
		assertNoIdsMissedFor(ElderPower.class);
		assertNoIdsMissedFor(Flaw.class);
		assertNoIdsMissedFor(MentalAttributeFocus.class);
		assertNoIdsMissedFor(Merit.class);
		assertNoIdsMissedFor(NecromanticRitual.class);
		assertNoIdsMissedFor(PhysicalAttributeFocus.class);
		assertNoIdsMissedFor(Skill.class);
		assertNoIdsMissedFor(SocialAttributeFocus.class);
		assertNoIdsMissedFor(Status.class);
		assertNoIdsMissedFor(Technique.class);
		assertNoIdsMissedFor(ThaumaturgicalRitual.class);
	}
	
	private <E extends Enum<E>> void assertNoIdsMissedFor(Class<E> klazz) {
		assertEquals(true, klazz.isEnum());
		E[] values = klazz.getEnumConstants();
		for (E value : values) {
			assertEquals(true, Trait.class.isAssignableFrom(value.getClass()));
			assertEquals(Trait.class.cast(value).id(), value.ordinal());
		}
	}
	
}
