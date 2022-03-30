drop table if exists car cascade;
create table if not exists car
(
    id    bigint not null
        primary key,
    brand varchar(255),
    model varchar(255),
    price money
);

drop table if exists human;
create table if not exists human
(
    id             bigint not null
        primary key,
    name           varchar(255),
    age            integer,
    driver_license bool,
    car_id         bigint,
    foreign key (car_id) references car
);

