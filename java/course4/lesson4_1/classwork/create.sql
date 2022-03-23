CREATE SEQUENCE table_name_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

SELECT nextval('table_name_id_seq'::regclass);

create table expenses
(
    id       integer default nextval('table_name_id_seq'::regclass) not null,
    name     text                                                   not null,
    date     date                                                   not null,
    category text,
    amount   integer                                                not null
);

INSERT INTO public.expenses (id, name, date, category, amount)
VALUES (1, 'Проезд в автобусе', '2021-01-30', 'Транспорт', 50);
INSERT INTO public.expenses (id, name, date, category, amount)
VALUES (2, 'Проезд в метро', '2021-01-30', 'Транспорт', 50);
INSERT INTO public.expenses (id, name, date, category, amount)
VALUES (3, 'Покупка книги', '2021-01-31', 'Прочие покупки', 300);
INSERT INTO public.expenses (id, name, date, category, amount)
VALUES (4, 'Покупка продуктов', '2021-01-31', 'Покупка продуктов', 450);
INSERT INTO public.expenses (id, name, date, category, amount)
VALUES (5, 'Поход в кино', '2021-02-01', 'Развлечения', 400);
INSERT INTO public.expenses (id, name, date, category, amount)
VALUES (6, 'Кофе', '2021-02-01', 'Еда вне дома', 150);
INSERT INTO public.expenses (id, name, date, category, amount)
VALUES (7, 'Покупка продуктов', '2021-02-02', 'Покупка продуктов', 600);
INSERT INTO public.expenses (id, name, date, category, amount)
VALUES (8, 'Поход в театр', '2021-02-14', 'Развлечения', 1000);
INSERT INTO public.expenses (id, name, date, category, amount)
VALUES (9, 'Цветы', '2021-02-14', null, 500);

select *
from expenses;

SELECT nextval('table_name_id_seq'::regclass)
FROM generate_series(1, 10);
