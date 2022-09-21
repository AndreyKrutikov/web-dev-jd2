alter table reactions
    add if not exists reaction_type_id int not null;

alter table reactions
    add constraint reactions_reaction_type_id_fk
        foreign key (reaction_type_id) references reaction_type
            on update cascade on delete cascade;