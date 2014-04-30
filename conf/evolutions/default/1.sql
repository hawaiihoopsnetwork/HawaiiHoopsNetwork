# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table address (
  id                        bigint not null,
  street                    varchar(255),
  city                      varchar(255),
  state                     varchar(255),
  zip                       varchar(255),
  country                   varchar(255),
  lat                       float,
  lng                       float,
  constraint pk_address primary key (id))
;

create table comment (
  id                        bigint not null,
  author                    varchar(255),
  comment                   varchar(255),
  date                      varchar(255),
  team_id                   bigint,
  constraint pk_comment primary key (id))
;

create table courts (
  id                        bigint not null,
  name                      varchar(255),
  image                     varchar(255),
  website                   varchar(255),
  phone                     varchar(255),
  email                     varchar(255),
  fax                       varchar(255),
  description               clob,
  type                      varchar(255),
  indoor                    varchar(255),
  num_courts                bigint,
  court_size                varchar(255),
  court_surface             varchar(255),
  court_quality             varchar(255),
  lighted                   boolean,
  address_id                bigint,
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
  id                        bigint not null,
  league_name               varchar(255),
  num_teams                 integer,
  start_date                varchar(255),
  end_date                  varchar(255),
  description               varchar(255),
  pub_or_private            varchar(255),
  location                  varchar(255),
  constraint pk_league primary key (id))
;

create table players (
  id                        bigint not null,
  nickname                  varchar(255),
  skill                     varchar(255),
  position                  varchar(255),
  rating                    bigint,
  votes                     bigint,
  height                    varchar(255),
  weight                    varchar(255),
  bio                       varchar(255),
  looking_for               varchar(255),
  pic_url                   varchar(255),
  home_court_id             bigint,
  constraint pk_players primary key (id))
;

create table court_review (
  id                        bigint not null,
  author_id                 bigint,
  review                    clob,
  rating                    bigint,
  timestamp                 timestamp,
  court_id                  bigint,
  constraint pk_court_review primary key (id))
;

create table teams (
  id                        bigint not null,
  team_name                 varchar(255),
  location                  varchar(255),
  team_type                 varchar(255),
  skill_level               varchar(255),
  roster                    varchar(255),
  description               varchar(255),
  image_url                 varchar(255),
  constraint pk_teams primary key (id))
;

create table users (
  id                        bigint not null,
  email                     varchar(255),
  user_name                 varchar(255),
  name                      varchar(255),
  password                  varchar(255),
  admin                     boolean,
  activation_key            varchar(255),
  timestamp                 timestamp,
  player_id                 bigint,
  constraint uq_users_email unique (email),
  constraint pk_users primary key (id))
;


create table courts_players (
  courts_id                      bigint not null,
  players_id                     bigint not null,
  constraint pk_courts_players primary key (courts_id, players_id))
;

create table teams_league (
  teams_id                       bigint not null,
  league_id                      bigint not null,
  constraint pk_teams_league primary key (teams_id, league_id))
;
create sequence address_seq;

create sequence comment_seq;

create sequence courts_seq;

create sequence game_seq;

create sequence league_seq;

create sequence players_seq;

create sequence court_review_seq;

create sequence teams_seq;

create sequence users_seq;

alter table comment add constraint fk_comment_team_1 foreign key (team_id) references teams (id) on delete restrict on update restrict;
create index ix_comment_team_1 on comment (team_id);
alter table courts add constraint fk_courts_address_2 foreign key (address_id) references address (id) on delete restrict on update restrict;
create index ix_courts_address_2 on courts (address_id);
alter table players add constraint fk_players_homeCourt_3 foreign key (home_court_id) references courts (id) on delete restrict on update restrict;
create index ix_players_homeCourt_3 on players (home_court_id);
alter table court_review add constraint fk_court_review_author_4 foreign key (author_id) references users (id) on delete restrict on update restrict;
create index ix_court_review_author_4 on court_review (author_id);
alter table court_review add constraint fk_court_review_court_5 foreign key (court_id) references courts (id) on delete restrict on update restrict;
create index ix_court_review_court_5 on court_review (court_id);
alter table users add constraint fk_users_player_6 foreign key (player_id) references players (id) on delete restrict on update restrict;
create index ix_users_player_6 on users (player_id);



alter table courts_players add constraint fk_courts_players_courts_01 foreign key (courts_id) references courts (id) on delete restrict on update restrict;

alter table courts_players add constraint fk_courts_players_players_02 foreign key (players_id) references players (id) on delete restrict on update restrict;

alter table teams_league add constraint fk_teams_league_teams_01 foreign key (teams_id) references teams (id) on delete restrict on update restrict;

alter table teams_league add constraint fk_teams_league_league_02 foreign key (league_id) references league (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists address;

drop table if exists comment;

drop table if exists courts;

drop table if exists courts_players;

drop table if exists game;

drop table if exists league;

drop table if exists teams_league;

drop table if exists players;

drop table if exists court_review;

drop table if exists teams;

drop table if exists users;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists address_seq;

drop sequence if exists comment_seq;

drop sequence if exists courts_seq;

drop sequence if exists game_seq;

drop sequence if exists league_seq;

drop sequence if exists players_seq;

drop sequence if exists court_review_seq;

drop sequence if exists teams_seq;

drop sequence if exists users_seq;

