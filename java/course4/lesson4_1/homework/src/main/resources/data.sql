INSERT INTO public.faculty (id, color, name)
VALUES (1, 'red', 'фак1');
INSERT INTO public.faculty (id, color, name)
VALUES (2, 'blue', 'фак2');
INSERT INTO public.faculty (id, color, name)
VALUES (3, 'yellow', 'фак3');

INSERT INTO public.avatar (id, data, file_path, file_size, media_type)
VALUES (1, 16478, 'avatars\Student{id=9, name=''Ivan'', age=20}.JPG', 36379, 'image/jpeg');
INSERT INTO public.avatar (id, data, file_path, file_size, media_type)
VALUES (2, 16479, 'avatars\Student{id=5, name=''Таня'', age=20}.JPG', 23293, 'image/jpeg');
INSERT INTO public.avatar (id, data, file_path, file_size, media_type)
VALUES (3, 16485, 'avatars\Student{id=6, name=''Deada'', age=20}.jpeg', 158882, 'image/jpeg');
INSERT INTO public.avatar (id, data, file_path, file_size, media_type)
VALUES (4, 16487, 'avatars\Student{id=2, name=''Петя'', age=22}.jpg', 49246, 'image/jpeg');

INSERT INTO public.student (id, age, name, faculty_id, avatar_id)
VALUES (1, 20, 'Таня', 3, 1);
INSERT INTO public.student (id, age, name, faculty_id, avatar_id)
VALUES (2, 20, 'Ivan', 3, 2);
INSERT INTO public.student (id, age, name, faculty_id, avatar_id)
VALUES (3, 20, 'Kail', 3, 3);
INSERT INTO public.student (id, age, name, faculty_id, avatar_id)
VALUES (4, 20, 'Deada', 1, 4);
INSERT INTO public.student (id, age, name, faculty_id, avatar_id)
VALUES (5, 22, 'Петя', 1, null);
INSERT INTO public.student (id, age, name, faculty_id, avatar_id)
VALUES (6, 22, 'tttt', 1, null);
INSERT INTO public.student (id, age, name, faculty_id, avatar_id)
VALUES (7, 20, 'Ugo', 2, null);
INSERT INTO public.student (id, age, name, faculty_id, avatar_id)
VALUES (8, 19, 'Татьяна', 3, null);
INSERT INTO public.student (id, age, name, faculty_id, avatar_id)
VALUES (9, 5, 'Adams', 2, null);
INSERT INTO public.student (id, age, name, faculty_id, avatar_id)
VALUES (10, 66, 'string', 2, null);
INSERT INTO public.student (id, age, name, faculty_id, avatar_id)
VALUES (11, 0, 'string', 1, null);
INSERT INTO public.student (id, age, name, faculty_id, avatar_id)
VALUES (12, 18, 'Adam', 1, null);
INSERT INTO public.student (id, age, name, faculty_id, avatar_id)
VALUES (13, 8, 'NNN', 3, null);
INSERT INTO public.student (id, age, name, faculty_id, avatar_id)
VALUES (14, 17, 'Машук', 2, null);

