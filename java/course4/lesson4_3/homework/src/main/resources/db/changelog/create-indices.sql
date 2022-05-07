--liquibase formatted sql

--changeset anton:create-student-name-index

create index student_name_idx on students(name)

--changeset anton:create-faculty-name-and-color-index

create index faculty_name_color_idx on faculties(name, color)