package com.dstevens.characters;

import static com.dstevens.collections.Lists.list;

import static com.dstevens.collections.Sets.set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Test;

import com.dstevens.characters.traits.backgrounds.Background;
import com.dstevens.characters.traits.backgrounds.CharacterBackground;
import com.dstevens.characters.traits.changes.TraitChange;
import com.dstevens.characters.traits.skills.CharacterSkill;
import com.dstevens.characters.traits.skills.Skill;
import com.dstevens.players.Setting;
import com.dstevens.testing.EqualityTester;

public class PlayerCharacterTest {

    private static final String ID = "some id";
    private static final String NAME = "some name";
    private static final Setting SETTING = Setting.ANARCH;
    
    @Test
    public void testIdentityEquality() {
        EqualityTester.testing(new PlayerCharacter(ID, NAME, SETTING)).
                 assertEqualTo(new PlayerCharacter(ID, NAME, SETTING)).
                 assertEqualTo(new PlayerCharacter(ID, NAME, Setting.values()[SETTING.ordinal()+1])).
                 assertEqualTo(new PlayerCharacter(ID, "another " + NAME, SETTING)).
              assertNotEqualTo(new PlayerCharacter("another " + ID, NAME, SETTING));
    }
    
    @Test
    public void testWithSkill() {
    	PlayerCharacter characterWithSkills = new PlayerCharacter(ID, NAME, SETTING).
    			withSkill(new CharacterSkill(Skill.ANIMAL_KEN, 2, null, set())).
    			withSkill(new CharacterSkill(Skill.ACADEMICS, 3, null, set("foo", "bar"))).
    			withSkill(new CharacterSkill(Skill.CRAFTS, 4, "Poetry", set()));
    	
    	assertEquals(set(new CharacterSkill(Skill.ANIMAL_KEN, 2, null, set()), 
    			         new CharacterSkill(Skill.ACADEMICS, 3, null, set("foo", "bar")), 
    			         new CharacterSkill(Skill.CRAFTS, 4, "Poetry", set())) , 
    			     characterWithSkills.getSkills());
    }
    
    @Test
    public void testWithSkillWillReplaceExistingSkills() {
    	PlayerCharacter characterWithSkills = new PlayerCharacter(ID, NAME, SETTING).
    			withSkill(new CharacterSkill(Skill.ANIMAL_KEN, 2, null, set())).
    			withSkill(new CharacterSkill(Skill.ACADEMICS, 3, null, set("foo", "bar"))).
    			withSkill(new CharacterSkill(Skill.CRAFTS, 4, "Poetry", set()));
    	
    	PlayerCharacter characterWithReplacedSkills = characterWithSkills.
    			withSkill(new CharacterSkill(Skill.ANIMAL_KEN, 5, null, set())).
    			withSkill(new CharacterSkill(Skill.ACADEMICS, 1, null, set("baz", "baq"))).
    			withSkill(new CharacterSkill(Skill.CRAFTS, 4, "Painting", set()));
    	
    	assertEquals(set(new CharacterSkill(Skill.ANIMAL_KEN, 5, null, set()), 
    			         new CharacterSkill(Skill.ACADEMICS, 1, null, set("baz", "baq")), 
    			         new CharacterSkill(Skill.CRAFTS, 4, "Poetry", set()),
    			         new CharacterSkill(Skill.CRAFTS, 4, "Painting", set())), 
    			         characterWithReplacedSkills.getSkills());
    }
    
    @Test
    public void testWithoutSkill() {
    	PlayerCharacter characterWithSkills = new PlayerCharacter(ID, NAME, SETTING).
    			withSkill(new CharacterSkill(Skill.ANIMAL_KEN, 2, null, set())).
    			withSkill(new CharacterSkill(Skill.ACADEMICS, 3, null, set("foo", "bar"))).
    			withSkill(new CharacterSkill(Skill.CRAFTS, 4, "Poetry", set()));
    	
    	PlayerCharacter characterWithoutAnimalKen = characterWithSkills.withoutSkill(new CharacterSkill(Skill.ANIMAL_KEN, 2, null, set()));
    	
    	assertEquals(set(new CharacterSkill(Skill.ACADEMICS, 3, null, set("foo", "bar")),
    			         new CharacterSkill(Skill.CRAFTS, 4, "Poetry", set())), 
    			         characterWithoutAnimalKen.getSkills());
    }
    
    @Test
    public void testWithoutSkillWhenSkillHasSpeciality() {
    	PlayerCharacter characterWithSkills = new PlayerCharacter(ID, NAME, SETTING).
    			withSkill(new CharacterSkill(Skill.CRAFTS, 4, "Poetry", set())).
    			withSkill(new CharacterSkill(Skill.CRAFTS, 4, "Painting", set()));
    	
    	PlayerCharacter characterWithoutAnimalKen = characterWithSkills.withoutSkill(new CharacterSkill(Skill.CRAFTS, 4, "Poetry", set()));
    	
    	assertEquals(set(new CharacterSkill(Skill.CRAFTS, 4, "Painting", set())), 
    			     characterWithoutAnimalKen.getSkills());
    }
    
    @Test
    public void testWithoutSkillDoesNotConsiderRating() {
    	int rating = 3;
    	PlayerCharacter characterWithSkills = new PlayerCharacter(ID, NAME, SETTING).
    			withSkill(new CharacterSkill(Skill.CRAFTS, rating, "Poetry", set())).
    			withSkill(new CharacterSkill(Skill.CRAFTS, rating, "Painting", set()));
    	
    	PlayerCharacter characterWithoutAnimalKen = characterWithSkills.withoutSkill(new CharacterSkill(Skill.CRAFTS, rating+1, "Poetry", set()));
    	
    	assertEquals(set(new CharacterSkill(Skill.CRAFTS, rating, "Painting", set())), 
    			characterWithoutAnimalKen.getSkills());
    }
    
    @Test
    public void testWithoutSkillDoesNotConsiderFocuses() {
    	PlayerCharacter characterWithSkills = new PlayerCharacter(ID, NAME, SETTING).
    			withSkill(new CharacterSkill(Skill.ACADEMICS, 3, null, set("foo", "bar")));
    	
    	PlayerCharacter characterWithoutAnimalKen = characterWithSkills.withoutSkill(new CharacterSkill(Skill.ACADEMICS, 3, null, set("baz")));
    	
    	assertEquals(set(), characterWithoutAnimalKen.getSkills());
    }
    
    @Test
    public void testWithoutSkillWhenNoMatchingSkillFound() {
    	PlayerCharacter characterWithSkills = new PlayerCharacter(ID, NAME, SETTING).
    			withSkill(new CharacterSkill(Skill.ANIMAL_KEN, 2, null, set())).
    			withSkill(new CharacterSkill(Skill.ACADEMICS, 3, null, set("foo", "bar"))).
    			withSkill(new CharacterSkill(Skill.CRAFTS, 4, "Poetry", set()));
    	
    	PlayerCharacter characterWithoutAnimalKen = characterWithSkills.withoutSkill(new CharacterSkill(Skill.DRIVE, 2, null, set()));
    	
    	assertEquals(set(new CharacterSkill(Skill.ANIMAL_KEN, 2, null, set()),
    			         new CharacterSkill(Skill.ACADEMICS, 3, null, set("foo", "bar")),
    			         new CharacterSkill(Skill.CRAFTS, 4, "Poetry", set())), 
    			     characterWithoutAnimalKen.getSkills());
    }
    
    @Test
    public void testWithBackground() {
    	PlayerCharacter characterWithBackgrounds = new PlayerCharacter(ID, NAME, SETTING).
    			withBackground(CharacterBackground.backgroundFor(Background.GENERATION, 2)).
    			withBackground(CharacterBackground.backgroundFor(Background.ALLIES, 3, set("foo", "bar"))).
    			withBackground(CharacterBackground.backgroundFor(Background.ALTERNATE_IDENTITY, 4, "Totally awesome guy"));
    	
    	assertEquals(set(CharacterBackground.backgroundFor(Background.GENERATION, 2), 
    			         CharacterBackground.backgroundFor(Background.ALLIES, 3, set("foo", "bar")), 
    			         CharacterBackground.backgroundFor(Background.ALTERNATE_IDENTITY, 4, "Totally awesome guy")) , 
    			     characterWithBackgrounds.getBackgrounds());
    }
    
    @Test
    public void testWithBackgroundWillReplaceExistingSkills() {
    	PlayerCharacter characterWithBackgrounds = new PlayerCharacter(ID, NAME, SETTING).
    			withBackground(CharacterBackground.backgroundFor(Background.GENERATION, 2)).
    			withBackground(CharacterBackground.backgroundFor(Background.ALLIES, 3, set("foo", "bar"))).
    			withBackground(CharacterBackground.backgroundFor(Background.ALTERNATE_IDENTITY, 4, "Totally awesome guy"));
    	
    	PlayerCharacter characterWithReplacedBackgrounds = characterWithBackgrounds.
    			withBackground(CharacterBackground.backgroundFor(Background.GENERATION, 3)).
    			withBackground(CharacterBackground.backgroundFor(Background.ALLIES, 1, set("baz"))).
    			withBackground(CharacterBackground.backgroundFor(Background.ALTERNATE_IDENTITY, 5, "Another totally awesome guy"));
    	
    	assertEquals(set(CharacterBackground.backgroundFor(Background.GENERATION, 3), 
    			         CharacterBackground.backgroundFor(Background.ALLIES, 1, set("baz")), 
		                 CharacterBackground.backgroundFor(Background.ALTERNATE_IDENTITY, 4, "Totally awesome guy"),
		                 CharacterBackground.backgroundFor(Background.ALTERNATE_IDENTITY, 5, "Another totally awesome guy")), 
		         characterWithReplacedBackgrounds.getBackgrounds());
    }
    
    @Test
    public void testWithoutBackground() {
    	PlayerCharacter characterWithBackgrounds = new PlayerCharacter(ID, NAME, SETTING).
    			withBackground(CharacterBackground.backgroundFor(Background.GENERATION, 2)).
    			withBackground(CharacterBackground.backgroundFor(Background.ALLIES, 3, set("foo", "bar"))).
    			withBackground(CharacterBackground.backgroundFor(Background.ALTERNATE_IDENTITY, 4, "Totally awesome guy"));
    	
    	PlayerCharacter characterWithoutGeneration = characterWithBackgrounds.withoutBackground(CharacterBackground.backgroundFor(Background.GENERATION, 2));
    	
    	assertEquals(set(CharacterBackground.backgroundFor(Background.ALLIES, 3, set("foo", "bar")),
    			         CharacterBackground.backgroundFor(Background.ALTERNATE_IDENTITY, 4, "Totally awesome guy")), 
    			     characterWithoutGeneration.getBackgrounds());
    }
    
    @Test
    public void testWithoutBackgroundWhenBackgroundHasSpeciality() {
    	PlayerCharacter characterWithBackgrounds = new PlayerCharacter(ID, NAME, SETTING).
    			withBackground(CharacterBackground.backgroundFor(Background.ALTERNATE_IDENTITY, 3, "Totally awesome guy")).
    			withBackground(CharacterBackground.backgroundFor(Background.ALTERNATE_IDENTITY, 4, "Another Totally awesome guy"));
    	
    	PlayerCharacter characterWithoutAlternateId = characterWithBackgrounds.withoutBackground(CharacterBackground.backgroundFor(Background.ALTERNATE_IDENTITY, 3, "Totally awesome guy"));
    	
    	assertEquals(set(CharacterBackground.backgroundFor(Background.ALTERNATE_IDENTITY, 4, "Another Totally awesome guy")), 
    			     characterWithoutAlternateId.getBackgrounds());
    }
    
    @Test
    public void testWithoutBackgroundDoesNotConsiderRating() {
    	int rating = 3;
    	PlayerCharacter characterWithBackgrounds = new PlayerCharacter(ID, NAME, SETTING).
    			withBackground(CharacterBackground.backgroundFor(Background.ALTERNATE_IDENTITY, rating, "Totally awesome guy")).
    			withBackground(CharacterBackground.backgroundFor(Background.ALTERNATE_IDENTITY, rating, "Another Totally awesome guy"));
    	
    	PlayerCharacter characterWithoutAlternateId = characterWithBackgrounds.withoutBackground(CharacterBackground.backgroundFor(Background.ALTERNATE_IDENTITY, rating+1, "Another Totally awesome guy"));
    	
    	assertEquals(set(CharacterBackground.backgroundFor(Background.ALTERNATE_IDENTITY, rating, "Totally awesome guy")), 
    			     characterWithoutAlternateId.getBackgrounds());
    }
    
    @Test
    public void testWithoutBackgroundDoesNotConsiderFocuses() {
    	PlayerCharacter characterWithSkills = new PlayerCharacter(ID, NAME, SETTING).
    			withBackground(CharacterBackground.backgroundFor(Background.ALLIES, 3, set("foo", "bar")));
    	
    	PlayerCharacter characterWithoutAnimalKen = characterWithSkills.withoutBackground(CharacterBackground.backgroundFor(Background.ALLIES, 3, set("baz")));
    	
    	assertEquals(set(), characterWithoutAnimalKen.getBackgrounds());
    }
    
    @Test
    public void testWithoutBackgroundWhenNoMatchingBackgroundFound() {
    	PlayerCharacter characterWithBackgrounds = new PlayerCharacter(ID, NAME, SETTING).
    			withBackground(CharacterBackground.backgroundFor(Background.GENERATION, 2)).
    			withBackground(CharacterBackground.backgroundFor(Background.ALLIES, 3, set("foo", "bar"))).
    			withBackground(CharacterBackground.backgroundFor(Background.ALTERNATE_IDENTITY, 4, "Totally awesome guy"));
    	
    	PlayerCharacter characterStillWithBackgrounds = characterWithBackgrounds.withoutBackground(CharacterBackground.backgroundFor(Background.ALTERNATE_IDENTITY, 2, "Who?"));
    	
    	assertEquals(set(CharacterBackground.backgroundFor(Background.GENERATION, 2),
    			         CharacterBackground.backgroundFor(Background.ALLIES, 3, set("foo", "bar")),
    			         CharacterBackground.backgroundFor(Background.ALTERNATE_IDENTITY, 4, "Totally awesome guy")), 
    			         characterStillWithBackgrounds.getBackgrounds());
    }
    
    @Test
    public void testWithTraitChangeEvent() {
    	TraitChange<?> someTraitChangeEvent1 = mock(TraitChange.class);
    	TraitChange<?> someTraitChangeEvent2 = mock(TraitChange.class);
		PlayerCharacter character = new PlayerCharacter(ID, NAME, SETTING).request(someTraitChangeEvent1).request(someTraitChangeEvent2);
		
		List<TraitChange<?>> list = list(someTraitChangeEvent1, someTraitChangeEvent2);
		assertEquals(character.getRequestedTraitChanges(), list);
    }
}
