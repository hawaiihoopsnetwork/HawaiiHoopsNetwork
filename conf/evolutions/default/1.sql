# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table comment (
  id                        bigint auto_increment not null,
  author                    varchar(255),
  comment                   varchar(255),
  date                      varchar(255),
  team_team_name            varchar(255),
  constraint pk_comment primary key (id))
;

create table courts (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  type                      varchar(255),
  image                     varchar(255),
  website                   varchar(255),
  description               longtext,
  address                   varchar(255),
  lat                       float,
  lng                       float,
  num_courts                bigint,
  court_size                varchar(255),
  court_surface             varchar(255),
  court_quality             varchar(255),
  lighted                   tinyint(1) default 0,
  constraint pk_courts primary key (id))
;

create table game (
  name                      varchar(255) not null,
  time                      varchar(255),
  date                      varchar(255),
  location                  varchar(255),
  type                      varchar(255),
  frequency                 varchar(255),
  avg_skl_lvl               varchar(255),
  players                   varchar(255),
  date_created              varchar(255),
  date_edit                 varchar(255),
  update_count              integer,
  user_creator              varchar(255),
  constraint pk_game primary key (name))
;

create table league (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  constraint pk_league primary key (id))
;

create table players (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  nickname                  varchar(255),
  home_court                varchar(255),
  skill                     varchar(255),
  position                  varchar(255),
  rating                    bigint,
  votes                     bigint,
  height                    varchar(255),
  weight                    varchar(255),
  bio                       varchar(255),
  looking_for               varchar(255),
  pic_url                   varchar(255),
  constraint pk_players primary key (id))
;

create table teams (
  team_name                 varchar(255) not null,
  location                  varchar(255),
  team_type                 varchar(255),
  skill_level               varchar(255),
  roster                    varchar(255),
  description               varchar(255),
  image_url                 varchar(255),
  constraint pk_teams primary key (team_name))
;

create table users (
  id                        bigint auto_increment not null,
  email                     varchar(255),
  user_name                 varchar(255),
  name                      varchar(255),
  password                  varchar(255),
  admin                     tinyint(1) default 0,
  activation_key            varchar(255),
  timestamp                 datetime,
  players_id                bigint,
  constraint uq_users_email unique (email),
  constraint pk_users primary key (id))
;


create table courts_players (
  courts_id                      bigint not null,
  players_id                     bigint not null,
  constraint pk_courts_players primary key (courts_id, players_id))
;
alter table comment add constraint fk_comment_team_1 foreign key (team_team_name) references teams (team_name) on delete restrict on update restrict;
create index ix_comment_team_1 on comment (team_team_name);
alter table users add constraint fk_users_players_2 foreign key (players_id) references players (id) on delete restrict on update restrict;
create index ix_users_players_2 on users (players_id);



alter table courts_players add constraint fk_courts_players_courts_01 foreign key (courts_id) references courts (id) on delete restrict on update restrict;

alter table courts_players add constraint fk_courts_players_players_02 foreign key (players_id) references players (id) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table comment;

drop table courts;

drop table courts_players;

drop table game;

drop table league;

drop table players;

drop table teams;

drop table users;

SET FOREIGN_KEY_CHECKS=1;

