create sequence if not exists experience_id_seq
    as integer;

alter sequence experience_id_seq owner to postgres;

create table if not exists roles
(
    id   serial
        constraint roles_pk
            primary key,
    name varchar(50) not null
);

alter table roles
    owner to postgres;

create table if not exists accounts
(
    id            bigserial
        constraint accounts_pk
            primary key,
    login         varchar(50)                               not null,
    password      varchar(255)                              not null,
    email         varchar(255)                              not null,
    date_created  timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    date_modified timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    role_id       integer      default 1                    not null
        constraint accounts_roles_id_fk
            references roles
            on update cascade on delete cascade,
    is_locked     boolean      default false                not null
);

alter table accounts
    owner to postgres;

create unique index if not exists accounts_email_uindex
    on accounts (email);

create unique index if not exists accounts_id_uindex
    on accounts (id);

create unique index if not exists accounts_username_uindex
    on accounts (login);

create unique index if not exists roles_id_uindex
    on roles (id);

create unique index if not exists roles_name_uindex
    on roles (name);

create table if not exists instruments
(
    id   serial
        constraint instruments_pk
            primary key,
    name varchar(50) not null
);

alter table instruments
    owner to postgres;

create unique index if not exists instruments_id_uindex
    on instruments (id);

create unique index if not exists instruments_name_uindex
    on instruments (name);

create table if not exists experience
(
    id   integer     default nextval('experience_id_seq'::regclass) not null
        constraint experience_pk
            primary key,
    name varchar(50) default 'beginner'::character varying                    not null
);

alter table experience
    owner to postgres;

alter sequence experience_id_seq owned by experience.id;

create unique index if not exists experience_id_uindex
    on experience (id);

create unique index if not exists experience_name_uindex
    on experience (name);

create table if not exists media
(
    id        bigserial
        constraint user_profile_photo_pk
            primary key,
    photo_url varchar(255),
    demo_url  varchar(255)
);

alter table media
    owner to postgres;

create table if not exists user_profiles
(
    id                bigserial
        constraint user_profiles_pk
            primary key,
    account_id        bigint                                    not null,
    displayed_name    varchar(50)  default 'name'::character varying,
    location          geography(Point, 4326)                    not null,
    cell_phone_number varchar(12)                               not null,
    instrument_id     integer                                   not null
        constraint user_profiles_instruments_id_fk
            references instruments,
    experience_id     integer                                   not null
        constraint user_profiles_skills_level_id_fk
            references experience (id),
    media_id          bigint                                    not null
        constraint user_profiles_user_profile_photo_id_fk
            references media
            on update cascade on delete cascade,
    description       text,
    is_visible        boolean      default true                 not null,
    date_created      timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    date_modified     timestamp(6) default CURRENT_TIMESTAMP(6)
);

alter table user_profiles
    owner to postgres;

create unique index if not exists user_profiles_id_uindex
    on user_profiles (id);

create unique index if not exists user_profiles_cell_phone_number_uindex
    on user_profiles (cell_phone_number);

create unique index if not exists user_profiles_profile_photo_id_uindex
    on user_profiles (media_id);

create unique index if not exists user_profiles_account_id_uindex
    on user_profiles (account_id);

create unique index if not exists user_profile_photo_id_uindex
    on media (id);

create table if not exists reactions
(
    from_profile bigint                                    not null
        constraint reactions_user_profiles_id_fk
            references user_profiles
            on update cascade on delete cascade,
    to_profile   bigint                                    not null
        constraint reactions_user_profiles_id_fk_2
            references user_profiles
            on update cascade on delete cascade,
    id           bigserial
        constraint reactions_pk_2
            primary key,
    date_created timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    constraint reactions_from_to_pk
        unique (from_profile, to_profile)
);

alter table reactions
    owner to postgres;

create unique index if not exists reactions_id_uindex
    on reactions (id);

create table if not exists bands
(
    id       serial
        constraint bands_pk
            primary key,
    member_1 bigint
        constraint bands_user_profiles_id_fk
            references user_profiles,
    member_2 bigint
        constraint bands_user_profiles_id_fk_2
            references user_profiles,
    member_3 bigint
        constraint bands_user_profiles_id_fk_3
            references user_profiles,
    member_4 bigint
        constraint bands_user_profiles_id_fk_4
            references user_profiles
);

alter table bands
    owner to postgres;

create unique index if not exists bands_id_uindex
    on bands (id);

create unique index if not exists bands_member_1_uindex
    on bands (member_1);

create unique index if not exists bands_member_2_uindex
    on bands (member_2);

create unique index if not exists bands_member_3_uindex
    on bands (member_3);

create unique index if not exists bands_member_4_uindex
    on bands (member_4);

alter table media
    add if not exists date_created timestamp(6) default CURRENT_TIMESTAMP(6) not null;

alter table media
    add if not exists date_modified timestamp(6) default CURRENT_TIMESTAMP(6) not null;
