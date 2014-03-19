# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table game (
  id                        bigint not null,
  time                      varchar(255),
  date                      varchar(255),
  location                  varchar(255),
  type                      varchar(255),
  frequency                 varchar(255),
  avg_skl_lvl               varchar(255),
  players                   varchar(255),
  constraint pk_game primary key (id))
;

create sequence game_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists game;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists game_seq;

