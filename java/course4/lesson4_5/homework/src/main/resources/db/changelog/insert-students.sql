--liquibase formatted sql

--changeset anton:insert-students

INSERT INTO public.students (age, name, faculty_id)
VALUES (20, 'Таня', 3);
INSERT INTO public.students (age, name, faculty_id)
VALUES (20, 'Ivan', 3);
INSERT INTO public.students (age, name, faculty_id)
VALUES (20, 'Kail', 2);
INSERT INTO public.students (age, name, faculty_id)
VALUES (20, 'Deada', 1);
INSERT INTO public.students (age, name, faculty_id)
VALUES (22, 'Петя', 1);
