alter table PlayerPlayerCharacters drop foreign key FK_kbp7aw5ct1sphnq1ce7phgmog;
alter table PlayerPlayerCharacters drop foreign key FK_407xikxs2n16vm06n8nfna1hl;
alter table TroupePlayerCharacters drop foreign key FK_7fw6ag0gryhsw0pb7dbi370p0;
alter table TroupePlayerCharacters drop foreign key FK_4u5surlk2w3q1k6ly3juifp1e;
alter table TroupePlayers drop foreign key FK_jp8wcfbseex1hvlwwm8ig7y2i;
alter table TroupePlayers drop foreign key FK_hqwwgbic7k81k6tmmowmmsu6f;
drop table if exists AuditEvent;
drop table if exists Player;
drop table if exists PlayerCharacter;
drop table if exists PlayerPlayerCharacters;
drop table if exists Troupe;
drop table if exists TroupePlayerCharacters;
drop table if exists TroupePlayers;
create table AuditEvent (id varchar(255) not null, audit_message varchar(255), audited_type varchar(255), audited_id varchar(255), timestamp datetime, primary key (id));
create table Player (id varchar(255) not null, deleted_at datetime, email varchar(255), name varchar(255), primary key (id));
create table PlayerCharacter (id varchar(255) not null, deleted_at datetime, name varchar(255), primary key (id));
create table PlayerPlayerCharacters (player_id varchar(255), player_character_id varchar(255) not null, primary key (player_id, player_character_id));
create table Troupe (id varchar(255) not null, deleted_at datetime, name varchar(255), setting integer, primary key (id));
create table TroupePlayerCharacters (troupe_id varchar(255), player_character_id varchar(255) not null, primary key (troupe_id, player_character_id));
create table TroupePlayers (player_id varchar(255) not null, troupe_id varchar(255) not null, primary key (troupe_id, player_id));
alter table PlayerPlayerCharacters add constraint UK_407xikxs2n16vm06n8nfna1hl  unique (player_character_id);
alter table TroupePlayerCharacters add constraint UK_4u5surlk2w3q1k6ly3juifp1e  unique (player_character_id);
alter table PlayerPlayerCharacters add constraint FK_kbp7aw5ct1sphnq1ce7phgmog foreign key (player_id) references Player (id);
alter table PlayerPlayerCharacters add constraint FK_407xikxs2n16vm06n8nfna1hl foreign key (player_character_id) references PlayerCharacter (id);
alter table TroupePlayerCharacters add constraint FK_7fw6ag0gryhsw0pb7dbi370p0 foreign key (troupe_id) references Troupe (id);
alter table TroupePlayerCharacters add constraint FK_4u5surlk2w3q1k6ly3juifp1e foreign key (player_character_id) references PlayerCharacter (id);
alter table TroupePlayers add constraint FK_jp8wcfbseex1hvlwwm8ig7y2i foreign key (troupe_id) references Troupe (id);
alter table TroupePlayers add constraint FK_hqwwgbic7k81k6tmmowmmsu6f foreign key (player_id) references Player (id);
