create table engine (
    id serial primary key,
    name text not null
);

create table cars (
    id serial primary key,
    name text not null,
    engine_id integer references engine(id)
);

create table drivers (
    id serial primary key,
    name text not null
);

create table history_owner (
    driver_id integer references drivers(id),
    car_id integer references cars(id)
);