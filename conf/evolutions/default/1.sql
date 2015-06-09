# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table ELECTION (
  election_id               integer auto_increment not null,
  election_name             varchar(255),
  description               varchar(255),
  location                  varchar(255),
  election_type             varchar(255),
  start_date                timestamp,
  constraint pk_ELECTION primary key (election_id))
;

create table proposal (
  prop_id                   integer not null,
  prop_name                 varchar(255),
  prop_body                 varchar(255),
  prop_votes                integer,
  election_election_id      integer,
  constraint pk_proposal primary key (prop_id))
;

create table user (
  user_id                   integer not null,
  user_name                 varchar(255),
  constraint pk_user primary key (user_id))
;

create sequence proposal_seq;

create sequence user_seq;

alter table proposal add constraint fk_proposal_election_1 foreign key (election_election_id) references ELECTION (election_id) on delete restrict on update restrict;
create index ix_proposal_election_1 on proposal (election_election_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists ELECTION;

drop table if exists proposal;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists proposal_seq;

drop sequence if exists user_seq;

