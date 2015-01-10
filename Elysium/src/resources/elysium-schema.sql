alter table Backgrounds drop foreign key PlayerCharacter_Backgrounds_FK;
alter table Backgrounds_Focuses drop foreign key CharacterBackground_focuses_FK;
alter table Disciplines drop foreign key PlayerCharacter_Disciplines_FK;
alter table Flaws drop foreign key PlayerCharacter_Flaws_FK;
alter table Merits drop foreign key PlayerCharacter_Merits_FK;
alter table PlayerCharacter_AppliedTraitChanges drop foreign key AppliedTraitChanges_PlayerCharacter_FK;
alter table PlayerCharacter_AppliedTraitChanges drop foreign key PlayerCharacter_AppliedTraitChanges_FK;
alter table PlayerCharacter_ExperienceAward drop foreign key ExperienceAwards_PlayerCharacter_FK;
alter table PlayerCharacter_ExperienceAward drop foreign key PlayerCharacter_ExperienceAwards_FK;
alter table PlayerCharacter_RequestedTraitChanges drop foreign key RequestedTraitChanges_PlayerCharacter_FK;
alter table PlayerCharacter_RequestedTraitChanges drop foreign key PlayerCharacter_RequestedTraitChanges_FK;
alter table PlayerCharacter_elderPowers drop foreign key PlayerCharacter_ElderPowers_FK;
alter table PlayerCharacter_inClanDisciplines drop foreign key PlayerCharacter_InClanDisciplines_FK;
alter table PlayerCharacter_mentalAttrbuteFocuses drop foreign key PlayerCharacter_MentalAttributeFocuses_FK;
alter table PlayerCharacter_necromanticRituals drop foreign key PlayerCharacter_CharacterNecromanticRituals_FK;
alter table PlayerCharacter_physicalAttributeFocuses drop foreign key PlayerCharacter_PhysicalAttributeFocuses_FK;
alter table PlayerCharacter_playerStatusChanges drop foreign key PlayerCharacter_PlayerStatusChanges_FK;
alter table PlayerCharacter_socialAttributeFocuses drop foreign key PlayerCharacter_SocialAttributeFocuses_FK;
alter table PlayerCharacter_techniques drop foreign key PlayerCharacter_Techniques_FK;
alter table PlayerCharacter_thaumaturgicalRituals drop foreign key PlayerCharacter_CharacterThaumaturgicalRituals_FK;
alter table Skills drop foreign key PlayerCharacter_Skills_FK;
alter table Skills_Focuses drop foreign key CharacterSkill_focuses_FK;
alter table Status drop foreign key PlayerCharacter_Status_FK;
alter table TraitChange_focuses drop foreign key TraitChanges_focuses_FK;
alter table TraitChanges drop foreign key TraitChange_ChildTraitChange_FK;
drop table if exists AttributeValue;
drop table if exists Backgrounds;
drop table if exists Backgrounds_Focuses;
drop table if exists Disciplines;
drop table if exists ExperienceAward;
drop table if exists Flaws;
drop table if exists Merits;
drop table if exists PlayerCharacter;
drop table if exists PlayerCharacter_AppliedTraitChanges;
drop table if exists PlayerCharacter_ExperienceAward;
drop table if exists PlayerCharacter_RequestedTraitChanges;
drop table if exists PlayerCharacter_elderPowers;
drop table if exists PlayerCharacter_inClanDisciplines;
drop table if exists PlayerCharacter_mentalAttrbuteFocuses;
drop table if exists PlayerCharacter_necromanticRituals;
drop table if exists PlayerCharacter_physicalAttributeFocuses;
drop table if exists PlayerCharacter_playerStatusChanges;
drop table if exists PlayerCharacter_socialAttributeFocuses;
drop table if exists PlayerCharacter_techniques;
drop table if exists PlayerCharacter_thaumaturgicalRituals;
drop table if exists Skills;
drop table if exists Skills_Focuses;
drop table if exists Status;
drop table if exists TraitChange_focuses;
drop table if exists TraitChanges;
create table AttributeValue (attribute_type varchar(31) not null, id varchar(255) not null, value integer, primary key (id));
create table Backgrounds (id varchar(255) not null, rating integer, specialization varchar(255), background integer, character_id varchar(255), primary key (id));
create table Backgrounds_Focuses (CharacterBackground_id varchar(255) not null, focuses varchar(255));
create table Disciplines (id varchar(255) not null, rating integer, trait integer not null, character_id varchar(255), primary key (id));
create table ExperienceAward (id varchar(255) not null, awardedFor varchar(255), awardedOn datetime, experience integer, primary key (id));
create table Flaws (id varchar(255) not null, specialization varchar(255), trait integer not null, character_id varchar(255), primary key (id));
create table Merits (id varchar(255) not null, specialization varchar(255), trait integer not null, character_id varchar(255), primary key (id));
create table PlayerCharacter (id varchar(255) not null, approvalStatus integer, awardedXp integer, baseXp integer, bloodline integer, clan integer, groundXp integer, mental_attribute integer, name varchar(255), physical_attribute integer, primary_necromantic_path integer, primary_thaumaturgical_path integer, setting integer, social_attribute integer, appliedSpentXp integer, pendingSpentXp integer, primary key (id));
create table PlayerCharacter_AppliedTraitChanges (PlayerCharacter_id varchar(255) not null, appliedTraitChanges_id varchar(255) not null, order_by integer not null, primary key (PlayerCharacter_id, order_by));
create table PlayerCharacter_ExperienceAward (PlayerCharacter_id varchar(255) not null, experienceAwards_id varchar(255) not null);
create table PlayerCharacter_RequestedTraitChanges (PlayerCharacter_id varchar(255) not null, requesedTraitChanges_id varchar(255) not null, order_by integer not null, primary key (PlayerCharacter_id, order_by));
create table PlayerCharacter_elderPowers (PlayerCharacter_id varchar(255) not null, elderPowers integer);
create table PlayerCharacter_inClanDisciplines (PlayerCharacter_id varchar(255) not null, inClanDisciplines integer);
create table PlayerCharacter_mentalAttrbuteFocuses (PlayerCharacter_id varchar(255) not null, mentalAttrbuteFocuses integer);
create table PlayerCharacter_necromanticRituals (PlayerCharacter_id varchar(255) not null, necromanticRituals integer);
create table PlayerCharacter_physicalAttributeFocuses (PlayerCharacter_id varchar(255) not null, physicalAttributeFocuses integer);
create table PlayerCharacter_playerStatusChanges (PlayerCharacter_id varchar(255) not null, changedOn datetime, status integer, order_by integer not null, primary key (PlayerCharacter_id, order_by));
create table PlayerCharacter_socialAttributeFocuses (PlayerCharacter_id varchar(255) not null, socialAttributeFocuses integer);
create table PlayerCharacter_techniques (PlayerCharacter_id varchar(255) not null, techniques integer);
create table PlayerCharacter_thaumaturgicalRituals (PlayerCharacter_id varchar(255) not null, thaumaturgicalRituals integer);
create table Skills (id varchar(255) not null, rating integer, specialization varchar(255), skill integer, character_id varchar(255), primary key (id));
create table Skills_Focuses (CharacterSkill_id varchar(255) not null, focuses varchar(255));
create table Status (id varchar(255) not null, specialization varchar(255), trait integer not null, character_id varchar(255), primary key (id));
create table TraitChange_focuses (TraitChange_id varchar(255) not null, focuses varchar(255));
create table TraitChanges (trait_change_type varchar(31) not null, id varchar(255) not null, cost integer, ordinal integer, rating integer, specialization varchar(255), child_id varchar(255), primary key (id));
alter table PlayerCharacter_AppliedTraitChanges add constraint PlayerCharacter_AppliedTraitChanges_UC  unique (appliedTraitChanges_id);
alter table PlayerCharacter_ExperienceAward add constraint PlayerCharacter_ExperienceAwards_UC  unique (experienceAwards_id);
alter table PlayerCharacter_RequestedTraitChanges add constraint PlayerCharacter_RequestedTraitChanges_UC  unique (requesedTraitChanges_id);
alter table Backgrounds add constraint PlayerCharacter_Backgrounds_FK foreign key (character_id) references PlayerCharacter (id);
alter table Backgrounds_Focuses add constraint CharacterBackground_focuses_FK foreign key (CharacterBackground_id) references Backgrounds (id);
alter table Disciplines add constraint PlayerCharacter_Disciplines_FK foreign key (character_id) references PlayerCharacter (id);
alter table Flaws add constraint PlayerCharacter_Flaws_FK foreign key (character_id) references PlayerCharacter (id);
alter table Merits add constraint PlayerCharacter_Merits_FK foreign key (character_id) references PlayerCharacter (id);
alter table PlayerCharacter_AppliedTraitChanges add constraint AppliedTraitChanges_PlayerCharacter_FK foreign key (appliedTraitChanges_id) references TraitChanges (id);
alter table PlayerCharacter_AppliedTraitChanges add constraint PlayerCharacter_AppliedTraitChanges_FK foreign key (PlayerCharacter_id) references PlayerCharacter (id);
alter table PlayerCharacter_ExperienceAward add constraint ExperienceAwards_PlayerCharacter_FK foreign key (experienceAwards_id) references ExperienceAward (id);
alter table PlayerCharacter_ExperienceAward add constraint PlayerCharacter_ExperienceAwards_FK foreign key (PlayerCharacter_id) references PlayerCharacter (id);
alter table PlayerCharacter_RequestedTraitChanges add constraint RequestedTraitChanges_PlayerCharacter_FK foreign key (requesedTraitChanges_id) references TraitChanges (id);
alter table PlayerCharacter_RequestedTraitChanges add constraint PlayerCharacter_RequestedTraitChanges_FK foreign key (PlayerCharacter_id) references PlayerCharacter (id);
alter table PlayerCharacter_elderPowers add constraint PlayerCharacter_ElderPowers_FK foreign key (PlayerCharacter_id) references PlayerCharacter (id);
alter table PlayerCharacter_inClanDisciplines add constraint PlayerCharacter_InClanDisciplines_FK foreign key (PlayerCharacter_id) references PlayerCharacter (id);
alter table PlayerCharacter_mentalAttrbuteFocuses add constraint PlayerCharacter_MentalAttributeFocuses_FK foreign key (PlayerCharacter_id) references PlayerCharacter (id);
alter table PlayerCharacter_necromanticRituals add constraint PlayerCharacter_CharacterNecromanticRituals_FK foreign key (PlayerCharacter_id) references PlayerCharacter (id);
alter table PlayerCharacter_physicalAttributeFocuses add constraint PlayerCharacter_PhysicalAttributeFocuses_FK foreign key (PlayerCharacter_id) references PlayerCharacter (id);
alter table PlayerCharacter_playerStatusChanges add constraint PlayerCharacter_PlayerStatusChanges_FK foreign key (PlayerCharacter_id) references PlayerCharacter (id);
alter table PlayerCharacter_socialAttributeFocuses add constraint PlayerCharacter_SocialAttributeFocuses_FK foreign key (PlayerCharacter_id) references PlayerCharacter (id);
alter table PlayerCharacter_techniques add constraint PlayerCharacter_Techniques_FK foreign key (PlayerCharacter_id) references PlayerCharacter (id);
alter table PlayerCharacter_thaumaturgicalRituals add constraint PlayerCharacter_CharacterThaumaturgicalRituals_FK foreign key (PlayerCharacter_id) references PlayerCharacter (id);
alter table Skills add constraint PlayerCharacter_Skills_FK foreign key (character_id) references PlayerCharacter (id);
alter table Skills_Focuses add constraint CharacterSkill_focuses_FK foreign key (CharacterSkill_id) references Skills (id);
alter table Status add constraint PlayerCharacter_Status_FK foreign key (character_id) references PlayerCharacter (id);
alter table TraitChange_focuses add constraint TraitChanges_focuses_FK foreign key (TraitChange_id) references TraitChanges (id);
alter table TraitChanges add constraint TraitChange_ChildTraitChange_FK foreign key (child_id) references TraitChanges (id);
