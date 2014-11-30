Notes on anticipated requests / responses with the webservice element, and how they inform the domain

The general POST message for trait changes:
{
	trait_type: <trait_type_id>, //ENUM: Skill, Merit, Flaw, etc
	trait: <trait_id>, //Per appropriate trait type enum
	action: <action_id>, //ENUM: Set, Buy (Remove? Sell?)
	[rating: <rating_text>],
	[specialization: <specialization text>],
	[focus: [<focus_text_array>]]
}

