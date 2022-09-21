create table if not exists l_account_roles
(
    id         bigserial
        constraint l_account_roles_pk
            primary key,
    account_id bigint not null
        constraint l_account_roles_accounts_id_fk
            references accounts
            on update cascade on delete cascade,
    role_id    int    not null
        constraint l_account_roles_roles_id_fk
            references roles
            on update cascade on delete cascade
);

