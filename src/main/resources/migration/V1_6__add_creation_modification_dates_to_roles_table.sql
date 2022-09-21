alter table roles
    add if not exists date_created timestamp(6) default CURRENT_TIMESTAMP(6) not null;

alter table roles
    add if not exists date_modified timestamp(6) default CURRENT_TIMESTAMP(6) not null;