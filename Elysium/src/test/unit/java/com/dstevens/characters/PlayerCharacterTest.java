package com.dstevens.characters;

import static com.dstevens.collections.Sets.set;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.dstevens.characters.skills.CharacterSkill;
import com.dstevens.characters.skills.Skill;
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
}
