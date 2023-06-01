-- !Ups
alter table materia_movilidad add column id_solicitud uuid;
alter table materia_movilidad add constraint materia_movilidad_id_solicitud_fkey foreign key (id_solicitud) references solicitud_movilidad(id_solicitud);