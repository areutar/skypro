--liquibase formatted sql

--changeset dlatyshev:1
create table users (
id serial,
email text
)

--changeset dlatyshev:2
alter table users
add column name text;

--changeset dlatyshev:3
create index users_name_index on users(name)