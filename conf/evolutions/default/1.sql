# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table courts (
  id                        bigint not null,
  name                      varchar(255),
  type                      varchar(255),
  address                   varchar(255),
  lat                       float,
  lng                       float,
  description               clob,
  constraint pk_courts primary key (id))
;

create table players (
  id                        bigint not null,
  username                  varchar(255),
  name                      varchar(255),
  home_court                varchar(255),
  skill                     varchar(255),
  position                  varchar(255),
  rating                    bigint,
  constraint pk_players primary key (id))
;

create table users (
  id                        bigint not null,
  email                     varchar(255),
  name                      varchar(255),
  real_name                 varchar(255),
  password                  varchar(255),
  admin                     boolean,
  constraint uq_users_email unique (email),
  constraint pk_users primary key (id))
;


create table courts_players (
  courts_id                      bigint not null,
  players_id                     bigint not null,
  constraint pk_courts_players primary key (courts_id, players_id))
;
create sequence courts_seq;

create sequence players_seq;

create sequence users_seq;




alter table courts_players add constraint fk_courts_players_courts_01 foreign key (courts_id) references courts (id) on delete restrict on update restrict;

alter table courts_players add constraint fk_courts_players_players_02 foreign key (players_id) references players (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists courts;

drop table if exists courts_players;

drop table if exists players;

drop table if exists users;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists courts_seq;

drop sequence if exists players_seq;

drop sequence if exists users_seq;

