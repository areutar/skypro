--liquibase formatted sql

--changeset anton:create-avatar

create table avatars
(
    id         bigint not null
        primary key references students,
    data       oid,
    file_path  varchar(255),
    file_size  bigint not null,
    media_type varchar(255)
);

alter table avatars
    owner to student;