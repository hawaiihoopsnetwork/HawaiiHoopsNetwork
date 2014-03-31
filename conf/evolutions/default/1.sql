# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table comment (
  id                        bigint not null,
  author                    varchar(255),
  comment                   varchar(255),
  date                      varchar(255),
  team_team_name            varchar(255),
  constraint pk_comment primary key (id))
;

create table teams (
  team_name                 varchar(255) not null,
  location                  varchar(255),
  team_type                 varchar(255),
  skill_level               varchar(255),
  roster                    varchar(255),
  description               varchar(255),
  constraint pk_teams primary key (team_name))
;

create sequence comment_seq;

create sequence teams_seq;

alter table comment add constraint fk_comment_team_1 foreign key (team_team_name) references teams (team_name) on delete restrict on update restrict;
create index ix_comment_team_1 on comment (team_team_name);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists comment;

drop table if exists teams;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists comment_seq;

drop sequence if exists teams_seq;

