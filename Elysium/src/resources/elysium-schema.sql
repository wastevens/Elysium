drop table if exists player;
drop table if exists troupe;
create table player (id bigint not null auto_increment, setting varchar(255), name varchar(255), primary key (id));
create table troupe (id bigint not null auto_increment, name varchar(255), setting integer, primary key (id));
