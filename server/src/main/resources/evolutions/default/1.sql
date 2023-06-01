-- !Ups


-- The users table has the minimum necessary data

CREATE TABLE users
(
    user_id     UUID        NOT NULL,
    name        TEXT        NOT NULL,
    last_name   TEXT        NULL,
    email       CITEXT      NOT NULL,
    password    TEXT        NOT NULL,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    verified_on TIMESTAMPTZ NULL,
    CONSTRAINT users_user_id_pk PRIMARY KEY (user_id),
    CONSTRAINT users_email_unique UNIQUE (email)
);

create table rol
(
    id_rol UUID not null,
    tipo   TEXT not null,
    CONSTRAINT rol_pk PRIMARY KEY (id_rol)
);

create table carrera
(
    id_carrera UUID not null,
    nombre     TEXT not null,
    CONSTRAINT carrera_pk PRIMARY KEY (id_carrera)
);

create table materia
(
    id_materia UUID not null,
    nombre     TEXT not null,
    CONSTRAINT materia_pk PRIMARY KEY (id_materia)
);

create table estatus
(
    id_estatus  UUID        not null,
    descripcion varchar(50) not null,
    CONSTRAINT estatus_pk PRIMARY KEY (id_estatus)
);

create table instituto
(
    id_instituto UUID        not null,
    nombre       varchar(50) not null,
    domicilio    varchar(50) not null,
    telefono     char(10)    not null,
    CONSTRAINT instituto_pk PRIMARY KEY (id_instituto)
);

create table alumno
(
    id_alumno       UUID  not null,
    semestre        int   not null,
    num_movilidades int   not null,
    deuda           money not null,
    user_id         UUID  not null,
    CONSTRAINT alumno_pk PRIMARY KEY (id_alumno),
    CONSTRAINT alumno_usuario_fk FOREIGN KEY (user_id) REFERENCES users (user_id)
);

create table solicitud_movilidad
(
    id_solicitud UUID        not null,
    id_alumno    UUID        not null,
    fecha        TIMESTAMPTZ not null,
    descripcion  varchar(40) not null,
    id_instituto UUID        not null,
    id_estatus   UUID        not null,
    CONSTRAINT solicitud_movilidad_pk PRIMARY KEY (id_solicitud),
    CONSTRAINT solicitud_movilidad_alumno_fk FOREIGN KEY (id_alumno) REFERENCES alumno (id_alumno),
    CONSTRAINT solicitud_movilidad_instituto_fk FOREIGN KEY (id_instituto) REFERENCES instituto (id_instituto),
    CONSTRAINT solicitud_movilidad_estatus_fk FOREIGN KEY (id_estatus) REFERENCES estatus (id_estatus)
);

create table materia_carrera
(
    id_carrera UUID not null,
    id_materia UUID not null,
    CONSTRAINT materia_carrera_pk PRIMARY KEY (id_carrera, id_materia),
    CONSTRAINT materia_carrera_carrera_fk FOREIGN KEY (id_carrera) REFERENCES carrera (id_carrera),
    CONSTRAINT materia_carrera_materia_fk FOREIGN KEY (id_materia) REFERENCES materia (id_materia)
);

create table materia_movilidad
(

    id_materia   UUID           not null,
    calificacion numeric(12, 2) null,
    CONSTRAINT materia_movilidad_pk PRIMARY KEY (id_materia),
    CONSTRAINT materia_movilidad_materia_fk FOREIGN KEY (id_materia) REFERENCES materia (id_materia)
);

create table carrera_instituto
(
    id_carrera   UUID not null,
    id_instituto UUID not null,
    CONSTRAINT carrera_instituto_pk PRIMARY KEY (id_carrera, id_instituto),
    CONSTRAINT carrera_instituto_carrera_fk FOREIGN KEY (id_carrera) REFERENCES carrera (id_carrera),
    CONSTRAINT carrera_instituto_instituto_fk FOREIGN KEY (id_instituto) REFERENCES instituto (id_instituto)
);

create table movimientos
(
    id_solicitud_movilidad UUID        not null,
    user_id                UUID        not null,
    fecha_movimiento       TIMESTAMPTZ not null,
    id_estatus             UUID        not null,
    CONSTRAINT movimientos_pk PRIMARY KEY (id_solicitud_movilidad, user_id, fecha_movimiento),
    CONSTRAINT movimientos_solicitud_movilidad_fk FOREIGN KEY (id_solicitud_movilidad) REFERENCES solicitud_movilidad (id_solicitud),
    CONSTRAINT movimientos_usuario_fk FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT movimientos_estatus_fk FOREIGN KEY (id_estatus) REFERENCES estatus (id_estatus)
);

CREATE INDEX users_email_index ON users USING BTREE (email);

-- create the table to store the user logs
CREATE TABLE user_logs
(
    user_log_id UUID        NOT NULL,
    user_id     UUID        NOT NULL,
    message     TEXT        NOT NULL,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT user_logs_pk PRIMARY KEY (user_log_id),
    CONSTRAINT user_logs_users_fk FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE INDEX user_logs_user_id_index ON user_logs USING BTREE (user_id);

CREATE TABLE user_tokens
(
    user_token_id UUID        NOT NULL,
    token         TEXT        NOT NULL,
    token_type    TEXT        NOT NULL,
    created_at    TIMESTAMPTZ NOT NULL,
    expires_at    TIMESTAMPTZ NOT NULL,
    user_id       UUID        NOT NULL,
    CONSTRAINT user_tokens_id_pk PRIMARY KEY (user_token_id),
    CONSTRAINT user_tokens_user_id_fk FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE INDEX user_tokens_user_id_index ON user_tokens USING BTREE (user_id);

CREATE TABLE background_jobs
(
    background_job_id UUID        NOT NULL,
    type              TEXT        NOT NULL,
    payload           JSONB       NOT NULL,
    status            TEXT        NOT NULL, -- pending/success/failed,
    status_details    TEXT        NULL,     -- if failed, what was the reason
    error_count       INT                  DEFAULT 0,
    execute_at        TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    created_at        TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at        TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT background_jobs_id_pk PRIMARY KEY (background_job_id)
);

CREATE INDEX background_jobs_execute_at_index ON background_jobs USING BTREE (execute_at);
