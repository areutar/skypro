--liquibase formatted sql

--changeset anton:insert-faculties

INSERT INTO public.faculties (color, name)
VALUES ('red', 'фак1');
INSERT INTO public.faculties (color, name)
VALUES ('blue', 'фак2');
INSERT INTO public.faculties (color, name)
VALUES ('yellow', 'фак3');