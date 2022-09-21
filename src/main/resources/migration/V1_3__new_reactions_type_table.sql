create table if not exists reaction_type
(
    id   serial
        constraint reaction_type_pk
            primary key,
    name varchar(20) default 'NOT_SELECTED' not null
);

create unique index if not exists reaction_type_id_uindex
    on reaction_type (id);

create unique index if not exists reaction_type_name_uindex
    on reaction_type (name);