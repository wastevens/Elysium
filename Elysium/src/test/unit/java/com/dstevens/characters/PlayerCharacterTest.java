package com.dstevens.characters;

import static com.dstevens.collections.Lists.list;

import static com.dstevens.collections.Sets.set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.dstevens.characters.traits.SetTrait;
import com.dstevens.characters.traits.backgrounds.Background;
import com.dstevens.characters.traits.backgrounds.CharacterBackground;
import com.dstevens.characters.traits.skills.CharacterSkill;
import com.dstevens.characters.traits.skills.Skill;
import com.dstevens.testing.EqualityTester;

public class PlayerCharacterTest {

    private static final String ID = "some id";
    private static final String NAME = "some name";

    @Test
    public void testIdentityEquality() {
        EqualityTester.testing(new PlayerCharacter(ID, NAME)).
                 assertEqualTo(new PlayerCharacter(ID, "another " + NAME)).
              assertNotEqualTo(new PlayerCharacter("another " + ID, NAME));
    }
    
    @Test
    public void testWithSkill() {
    	PlayerCharacter characterWithSkills = new PlayerCharacter(ID, NAME).
    			withSkill(CharacterSkill.skillFor(Skill.ANIMAL_KEN, 2)).
    			withSkill(CharacterSkill.skillFor(Skill.ACADEMICS, 3, set("foo", "bar"))).
    			withSkill(CharacterSkill.skillFor(Skill.CRAFTS, 4, "Poetry"));
    	
    	assertEquals(set(CharacterSkill.skillFor(Skill.ANIMAL_KEN, 2), 
    			         CharacterSkill.skillFor(Skill.ACADEMICS, 3, set("foo", "bar")), 
    			         CharacterSkill.skillFor(Skill.CRAFTS, 4, "Poetry")) , 
    			     characterWithSkills.getSkills());
    }
    
    @Test
    public void testWithSkillWillReplaceExistingSkills() {
    	PlayerCharacter characterWithSkills = new PlayerCharacter(ID, NAME).
    			withSkill(CharacterSkill.skillFor(Skill.ANIMAL_KEN, 2)).
    			withSkill(CharacterSkill.skillFor(Skill.ACADEMICS, 3, set("foo", "bar"))).
    			withSkill(CharacterSkill.skillFor(Skill.CRAFTS, 4, "Poetry"));
    	
    	PlayerCharacter characterWithReplacedSkills = characterWithSkills.
    			withSkill(CharacterSkill.skillFor(Skill.ANIMAL_KEN, 5)).
    			withSkill(CharacterSkill.skillFor(Skill.ACADEMICS, 1, set("baz", "baq"))).
    			withSkill(CharacterSkill.skillFor(Skill.CRAFTS, 4, "Painting"));
    	
    	assertEquals(set(CharacterSkill.skillFor(Skill.ANIMAL_KEN, 5), 
    			         CharacterSkill.skillFor(Skill.ACADEMICS, 1, set("baz", "baq")), 
    			         CharacterSkill.skillFor(Skill.CRAFTS, 4, "Poetry"),
    			         CharacterSkill.skillFor(Skill.CRAFTS, 4, "Painting")), 
    			         characterWithReplacedSkills.getSkills());
    }
    
    @Test
    public void testWithoutSkill() {
    	PlayerCharacter characterWithSkills = new PlayerCharacter(ID, NAME).
    			withSkill(CharacterSkill.skillFor(Skill.ANIMAL_KEN, 2)).
    			withSkill(CharacterSkill.skillFor(Skill.ACADEMICS, 3, set("foo", "bar"))).
    			withSkill(CharacterSkill.skillFor(Skill.CRAFTS, 4, "Poetry"));
    	
    	PlayerCharacter characterWithoutAnimalKen = characterWithSkills.withoutSkill(CharacterSkill.skillFor(Skill.ANIMAL_KEN, 2));
    	
    	assertEquals(set(CharacterSkill.skillFor(Skill.ACADEMICS, 3, set("foo", "bar")),
    			         CharacterSkill.skillFor(Skill.CRAFTS, 4, "Poetry")), 
    			         characterWithoutAnimalKen.getSkills());
    }
    
    @Test
    public void testWithoutSkillWhenSkillHasSpeciality() {
    	PlayerCharacter characterWithSkills = new PlayerCharacter(ID, NAME).
    			withSkill(CharacterSkill.skillFor(Skill.CRAFTS, 4, "Poetry")).
    			withSkill(CharacterSkill.skillFor(Skill.CRAFTS, 4, "Painting"));
    	
    	PlayerCharacter characterWithoutAnimalKen = characterWithSkills.withoutSkill(CharacterSkill.skillFor(Skill.CRAFTS, 4, "Poetry"));
    	
    	assertEquals(set(CharacterSkill.skillFor(Skill.CRAFTS, 4, "Painting")), 
    			     characterWithoutAnimalKen.getSkills());
    }
    
    @Test
    public void testWithoutSkillDoesNotConsiderRating() {
    	int rating = 3;
    	PlayerCharacter characterWithSkills = new PlayerCharacter(ID, NAME).
    			withSkill(CharacterSkill.skillFor(Skill.CRAFTS, rating, "Poetry")).
    			withSkill(CharacterSkill.skillFor(Skill.CRAFTS, rating, "Painting"));
    	
    	PlayerCharacter characterWithoutAnimalKen = characterWithSkills.withoutSkill(CharacterSkill.skillFor(Skill.CRAFTS, rating+1, "Poetry"));
    	
    	assertEquals(set(CharacterSkill.skillFor(Skill.CRAFTS, rating, "Painting")), 
    			characterWithoutAnimalKen.getSkills());
    }
    
    @Test
    public void testWithoutSkillDoesNotConsiderFocuses() {
    	PlayerCharacter characterWithSkills = new PlayerCharacter(ID, NAME).
    			withSkill(CharacterSkill.skillFor(Skill.ACADEMICS, 3, set("foo", "bar")));
    	
    	PlayerCharacter characterWithoutAnimalKen = characterWithSkills.withoutSkill(CharacterSkill.skillFor(Skill.ACADEMICS, 3, set("baz")));
    	
    	assertEquals(set(), characterWithoutAnimalKen.getSkills());
    }
    
    @Test
    public void testWithoutSkillWhenNoMatchingSkillFound() {
    	PlayerCharacter characterWithSkills = new PlayerCharacter(ID, NAME).
    			withSkill(CharacterSkill.skillFor(Skill.ANIMAL_KEN, 2)).
    			withSkill(CharacterSkill.skillFor(Skill.ACADEMICS, 3, set("foo", "bar"))).
    			withSkill(CharacterSkill.skillFor(Skill.CRAFTS, 4, "Poetry"));
    	
    	PlayerCharacter characterWithoutAnimalKen = characterWithSkills.withoutSkill(CharacterSkill.skillFor(Skill.DRIVE, 2));
    	
    	assertEquals(set(CharacterSkill.skillFor(Skill.ANIMAL_KEN, 2),
    			         CharacterSkill.skillFor(Skill.ACADEMICS, 3, set("foo", "bar")),
    			         CharacterSkill.skillFor(Skill.CRAFTS, 4, "Poetry")), 
    			     characterWithoutAnimalKen.getSkills());
    }
    
    @Test
    public void testWithBackground() {
    	PlayerCharacter characterWithBackgrounds = new PlayerCharacter(ID, NAME).
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
    	PlayerCharacter characterWithBackgrounds = new PlayerCharacter(ID, NAME).
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
    	PlayerCharacter characterWithBackgrounds = new PlayerCharacter(ID, NAME).
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
    	PlayerCharacter characterWithBackgrounds = new PlayerCharacter(ID, NAME).
    			withBackground(CharacterBackground.backgroundFor(Background.ALTERNATE_IDENTITY, 3, "Totally awesome guy")).
    			withBackground(CharacterBackground.backgroundFor(Background.ALTERNATE_IDENTITY, 4, "Another Totally awesome guy"));
    	
    	PlayerCharacter characterWithoutAlternateId = characterWithBackgrounds.withoutBackground(CharacterBackground.backgroundFor(Background.ALTERNATE_IDENTITY, 3, "Totally awesome guy"));
    	
    	assertEquals(set(CharacterBackground.backgroundFor(Background.ALTERNATE_IDENTITY, 4, "Another Totally awesome guy")), 
    			     characterWithoutAlternateId.getBackgrounds());
    }
    
    @Test
    public void testWithoutBackgroundDoesNotConsiderRating() {
    	int rating = 3;
    	PlayerCharacter characterWithBackgrounds = new PlayerCharacter(ID, NAME).
    			withBackground(CharacterBackground.backgroundFor(Background.ALTERNATE_IDENTITY, rating, "Totally awesome guy")).
    			withBackground(CharacterBackground.backgroundFor(Background.ALTERNATE_IDENTITY, rating, "Another Totally awesome guy"));
    	
    	PlayerCharacter characterWithoutAlternateId = characterWithBackgrounds.withoutBackground(CharacterBackground.backgroundFor(Background.ALTERNATE_IDENTITY, rating+1, "Another Totally awesome guy"));
    	
    	assertEquals(set(CharacterBackground.backgroundFor(Background.ALTERNATE_IDENTITY, rating, "Totally awesome guy")), 
    			     characterWithoutAlternateId.getBackgrounds());
    }
    
    @Test
    public void testWithoutBackgroundDoesNotConsiderFocuses() {
    	PlayerCharacter characterWithSkills = new PlayerCharacter(ID, NAME).
    			withBackground(CharacterBackground.backgroundFor(Background.ALLIES, 3, set("foo", "bar")));
    	
    	PlayerCharacter characterWithoutAnimalKen = characterWithSkills.withoutBackground(CharacterBackground.backgroundFor(Background.ALLIES, 3, set("baz")));
    	
    	assertEquals(set(), characterWithoutAnimalKen.getBackgrounds());
    }
    
    @Test
    public void testWithoutBackgroundWhenNoMatchingBackgroundFound() {
    	PlayerCharacter characterWithBackgrounds = new PlayerCharacter(ID, NAME).
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
    	SetTrait someTraitChangeEvent1 = mock(SetTrait.class);
    	SetTrait someTraitChangeEvent2 = mock(SetTrait.class);
		PlayerCharacter character = new PlayerCharacter(ID, NAME).withTraitChangeEvent(someTraitChangeEvent1).withTraitChangeEvent(someTraitChangeEvent2);
		
		assertEquals(character.getTraitChangeEvents(), list(someTraitChangeEvent1, someTraitChangeEvent2));
    }
}
