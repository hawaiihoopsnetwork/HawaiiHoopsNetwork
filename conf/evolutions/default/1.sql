# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table court_address (
  id                        bigint auto_increment not null,
  street                    varchar(255),
  city                      varchar(255),
  state                     varchar(255),
  zip                       varchar(255),
  country                   varchar(255),
  lat                       float,
  lng                       float,
  constraint pk_court_address primary key (id))
;

create table comment (
  id                        bigint auto_increment not null,
  author_id                 bigint,
  comment                   varchar(255),
  date                      varchar(255),
  team_id                   bigint,
  constraint pk_comment primary key (id))
;

create table courts (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  image                     varchar(255),
  website                   varchar(255),
  phone                     varchar(255),
  email                     varchar(255),
  fax                       varchar(255),
  description               longtext,
  type                      varchar(255),
  indoor                    varchar(255),
  num_courts                bigint,
  court_size                varchar(255),
  court_surface             varchar(255),
  court_quality             varchar(255),
  lighted                   tinyint(1) default 0,
  address_id                bigint,
  constraint pk_courts primary key (id))
;

create table game (
  name                      varchar(255) not null,
  game_time                 datetime,
  month                     varchar(255),
  day                       varchar(255),
  year                      varchar(255),
  hour                      varchar(255),
  minute                    varchar(255),
  am_pm_time                varchar(255),
  location                  varchar(255),
  type                      varchar(255),
  frequency                 varchar(255),
  avg_skl_lvl               varchar(255),
  players                   varchar(255),
  date_created              varchar(255),
  date_edit                 varchar(255),
  update_count              integer,
  creator_id                bigint,
  constraint pk_game primary key (name))
;

create table court_hours (
  id                        bigint auto_increment not null,
  start_day                 integer,
  start_time                datetime,
  end_time                  datetime,
  constraint pk_court_hours primary key (id))
;

create table league (
  id                        bigint auto_increment not null,
  league_name               varchar(255),
  num_teams                 integer,
  num_teams_in_league       integer,
  start_date                varchar(255),
  end_date                  varchar(255),
  description               varchar(255),
  pub_or_private            varchar(255),
  location                  varchar(255),
  reg_step                  integer,
  constraint pk_league primary key (id))
;

create table players (
  id                        bigint auto_increment not null,
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
  id                        bigint auto_increment not null,
  author_id                 bigint,
  review                    longtext,
  rating                    bigint,
  timestamp                 datetime,
  court_id                  bigint,
  constraint pk_court_review primary key (id))
;

create table teams (
  id                        bigint auto_increment not null,
  team_name                 varchar(255),
  location                  varchar(255),
  team_type                 varchar(255),
  skill_level               varchar(255),
  roster                    varchar(255),
  description               varchar(255),
  image_url                 varchar(255),
  record                    varchar(255),
  three_pt                  double,
  two_pt                    double,
  one_pt                    double,
  rebounds                  integer,
  steals                    integer,
  blocks                    integer,
  constraint pk_teams primary key (id))
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
  player_id                 bigint,
  constraint uq_users_email unique (email),
  constraint pk_users primary key (id))
;


create table courts_players (
  courts_id                      bigint not null,
  players_id                     bigint not null,
  constraint pk_courts_players primary key (courts_id, players_id))
;

create table court_hours_courts (
  court_hours_id                 bigint not null,
  courts_id                      bigint not null,
  constraint pk_court_hours_courts primary key (court_hours_id, courts_id))
;

create table teams_league (
  teams_id                       bigint not null,
  league_id                      bigint not null,
  constraint pk_teams_league primary key (teams_id, league_id))
;
alter table comment add constraint fk_comment_author_1 foreign key (author_id) references users (id) on delete restrict on update restrict;
create index ix_comment_author_1 on comment (author_id);
alter table comment add constraint fk_comment_team_2 foreign key (team_id) references teams (id) on delete restrict on update restrict;
create index ix_comment_team_2 on comment (team_id);
alter table courts add constraint fk_courts_address_3 foreign key (address_id) references court_address (id) on delete restrict on update restrict;
create index ix_courts_address_3 on courts (address_id);
alter table game add constraint fk_game_creator_4 foreign key (creator_id) references users (id) on delete restrict on update restrict;
create index ix_game_creator_4 on game (creator_id);
alter table players add constraint fk_players_homeCourt_5 foreign key (home_court_id) references courts (id) on delete restrict on update restrict;
create index ix_players_homeCourt_5 on players (home_court_id);
alter table court_review add constraint fk_court_review_author_6 foreign key (author_id) references users (id) on delete restrict on update restrict;
create index ix_court_review_author_6 on court_review (author_id);
alter table court_review add constraint fk_court_review_court_7 foreign key (court_id) references courts (id) on delete restrict on update restrict;
create index ix_court_review_court_7 on court_review (court_id);
alter table users add constraint fk_users_player_8 foreign key (player_id) references players (id) on delete restrict on update restrict;
create index ix_users_player_8 on users (player_id);



alter table courts_players add constraint fk_courts_players_courts_01 foreign key (courts_id) references courts (id) on delete restrict on update restrict;

alter table courts_players add constraint fk_courts_players_players_02 foreign key (players_id) references players (id) on delete restrict on update restrict;

alter table court_hours_courts add constraint fk_court_hours_courts_court_hours_01 foreign key (court_hours_id) references court_hours (id) on delete restrict on update restrict;

alter table court_hours_courts add constraint fk_court_hours_courts_courts_02 foreign key (courts_id) references courts (id) on delete restrict on update restrict;

alter table teams_league add constraint fk_teams_league_teams_01 foreign key (teams_id) references teams (id) on delete restrict on update restrict;

alter table teams_league add constraint fk_teams_league_league_02 foreign key (league_id) references league (id) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table court_address;

drop table comment;

drop table courts;

drop table courts_players;

drop table court_hours_courts;

drop table game;

drop table court_hours;

drop table league;

drop table teams_league;

drop table players;

drop table court_review;

drop table teams;

drop table users;

SET FOREIGN_KEY_CHECKS=1;

