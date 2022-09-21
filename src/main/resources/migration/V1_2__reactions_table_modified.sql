alter table reactions
    add if not exists date_modified timestamp(6)  default CURRENT_TIMESTAMP(6) not null;