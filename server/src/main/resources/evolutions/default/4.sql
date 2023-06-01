-- !Ups
alter table users add column id_carrera uuid NOT NULL;
alter table users add column id_rol uuid NOT NULL;
alter table users add constraint usuario_id_carrera_fkey foreign key (id_carrera) references carrera(id_carrera);
alter table users add constraint usuario_id_rol_fkey foreign key (id_rol) references rol(id_rol);