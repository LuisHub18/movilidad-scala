-- !Ups
alter table users add column id_instituto UUID NULL;
alter table users add constraint fk_users_institutos foreign key (id_instituto) references instituto(id_instituto);