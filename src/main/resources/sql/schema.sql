drop table if exists users cascade;
drop table if exists content cascade;
drop table if exists tag cascade;
drop table if exists content_tag cascade;
drop table if exists favorites cascade;

create table if not exists users(
    id bigserial primary key,
    is_admin bool default false,
    first_name varchar(50),
    second_name varchar(50),
    email varchar(50) unique,
    hashed_password varchar(32)
);

create table if not exists tag(
    id bigserial primary key,
    name varchar(50)
);

create table if not exists content(
    id bigserial primary key,
    name varchar(50),
    is_nsfw boolean
);

create table if not exists content_tag(
    content_id bigint references content(id),
    tag_id bigint references tag(id)
);

create table if not exists favorites(
    content_id bigint references content(id),
    user_id bigint references users(id)
);
