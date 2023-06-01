-- !Ups
alter table solicitud_movilidad drop constraint solicitud_movilidad_estatus_fk;
alter table solicitud_movilidad drop column id_estatus;