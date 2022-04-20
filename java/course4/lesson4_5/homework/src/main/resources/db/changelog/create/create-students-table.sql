--liquibase formatted sql

--changeset anton:create-student

create table students
(
    id         bigint generated by default as identity
        primary key,
    age        integer not null,
    constraint students_age_check
                check ((age >= 9) AND (age <= 65)),
    name       varchar(255),
    faculty_id bigint,
    foreign key (faculty_id) references faculties
);

alter table students
    owner to student;