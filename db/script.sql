create table if not exists users
(
    id       serial primary key,
    username text not null,
    email    text not null,
    password text not null
);

create table car_brand
(
    id   serial primary key,
    name text not null
);

create table car_body
(
    id   serial primary key,
    name text not null
);

create table car_model
(
    id       serial primary key,
    name     text not null,
    brand_id integer references car_brand (id),
    body_id  integer references car_body (id)
);

create table advertisement
(
    id         serial primary key,
    created    timestamp,
    model_id   integer references car_model (id),
    user_id    integer references users (id),
    status     boolean,
    photo_link text
);