--liquibase formatted sql

--changeset anton:insert-avatars

INSERT INTO public.avatars (id, data, file_path, file_size, media_type)
VALUES (1, 16479, 'avatars\Student{id=1, name=''Таня''}.JPG', 23293, 'image/jpeg');
