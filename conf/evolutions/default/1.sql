# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table court (
  name                      varchar(255) not null,
  description               clob,
  constraint pk_court primary key (name))
;

create table user (
  email                     varchar(255) not null,
  first                     varchar(255),
  last                      varchar(255),
  password                  varchar(255),
  admin                     boolean,
  constraint pk_user primary key (email))
;

create sequence court_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists court;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists court_seq;

drop sequence if exists user_seq;

