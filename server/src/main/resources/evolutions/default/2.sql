-- !Ups
ALTER TABLE alumno DROP COLUMN deuda;
ALTER TABLE alumno ADD COLUMN deuda boolean NOT NULL DEFAULT false;