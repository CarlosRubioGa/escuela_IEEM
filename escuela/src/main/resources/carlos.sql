drop schema carlos cascade;
create schema carlos;

CREATE TABLE carlos.tdivision
(
    id_division bigint NOT NULL,
    nombre_division character varying(255),
    CONSTRAINT tdivision_pkey PRIMARY KEY (id_division)
);

CREATE TABLE carlos.tescuela
(
    id_escuela bigint NOT NULL,
    nombre_escuela character varying(255), 
    direccion_escuela varchar(255), 
    CONSTRAINT tescuela_pkey PRIMARY KEY (id_escuela)
);

CREATE TABLE carlos.ttaller
(
    id_taller bigint NOT NULL,
    nombre_taller varchar(255),
    id_division bigint NOT NULL,
    CONSTRAINT ttaller_pkey PRIMARY KEY (id_taller),
    CONSTRAINT fk_taller_division FOREIGN KEY (id_division)
        REFERENCES carlos.tdivision (id_division)
);

CREATE TABLE IF NOT EXISTS carlos.tcurso
(
    id_curso bigint NOT NULL,
    nombre_curso character varying(255),
    id_division bigint NOT NULL,
    CONSTRAINT tcurso_pkey PRIMARY KEY (id_curso),
    CONSTRAINT fk_curso_division FOREIGN KEY (id_division)
        REFERENCES carlos.tdivision (id_division)
);

CREATE TABLE IF NOT EXISTS carlos.tmateria
(
    id_materia bigint NOT NULL,
    nombre_materia character varying(255),
    creditos_materia integer,
    CONSTRAINT tmateria_pkey PRIMARY KEY (id_materia)
);

CREATE TABLE IF NOT EXISTS carlos.tcarrera
(
    id_carrera bigint NOT NULL,
    id_escuela bigint NOT NULL,
    nombre_carrera character varying(255),
    CONSTRAINT tcarrera_pkey PRIMARY KEY (id_carrera, id_escuela),
    CONSTRAINT fk_escuela FOREIGN KEY (id_escuela)
        REFERENCES carlos.tescuela (id_escuela)
);

CREATE TABLE IF NOT EXISTS carlos.tdatos_personales
(
    id_datos_personales bigint NOT NULL,
    curp character varying(255), 
    nombre_persona character varying(255),  
    apellido_persona character varying(255),
    edad integer,
    grupo_sanguineo character varying(255),
    CONSTRAINT datos_personales_pkey PRIMARY KEY (id_datos_personales)
);

CREATE TABLE IF NOT EXISTS carlos.tmaestro
(
    id_maestro bigint NOT NULL,
    especialidad character varying(255),
    id_datos_personales bigint,
    CONSTRAINT tmaestro_pkey PRIMARY KEY (id_maestro),
    CONSTRAINT id_datos_personales_fk FOREIGN KEY (id_datos_personales)
        REFERENCES carlos.tdatos_personales (id_datos_personales)
);

CREATE TABLE IF NOT EXISTS carlos.tcurso_maestro
(
    id_curso bigint NOT NULL,
    id_maestro bigint NOT NULL,
    CONSTRAINT id_curso_fk FOREIGN KEY (id_curso)
        REFERENCES carlos.tcurso (id_curso),
    CONSTRAINT id_maestro_fk FOREIGN KEY (id_maestro)
        REFERENCES carlos.tmaestro (id_maestro)
);

CREATE TABLE IF NOT EXISTS carlos.talumno
(
    id_alumno bigint NOT NULL,
    matricula character varying(255),
    id_datos_personales bigint NOT NULL,
    CONSTRAINT talumno_pkey PRIMARY KEY (id_alumno),
    CONSTRAINT id_datos_personales_fk FOREIGN KEY (id_datos_personales)
        REFERENCES carlos.tdatos_personales (id_datos_personales)
);

CREATE TABLE IF NOT EXISTS carlos.ttaller_alumno
(
    id_taller bigint NOT NULL,
    id_alumno bigint NOT NULL,
    CONSTRAINT id_taller_fk FOREIGN KEY (id_taller)
        REFERENCES carlos.ttaller (id_taller),
    CONSTRAINT id_alumno_fk FOREIGN KEY (id_alumno)
        REFERENCES carlos.talumno (id_alumno) 
);

CREATE TABLE IF NOT EXISTS carlos.tsistemas_escolares (
    id_sistema_escolar BIGINT PRIMARY KEY,
    id_alumno          BIGINT NOT NULL,
    id_maestro         BIGINT NOT NULL,
    id_carrera         BIGINT NOT NULL,
    id_escuela         BIGINT NOT NULL,
    id_materia         BIGINT NOT NULL,
    calificacion       NUMERIC(4,1) NOT NULL,
    CONSTRAINT fk_sistema_alumno
        FOREIGN KEY (id_alumno) 
        REFERENCES carlos.talumno (id_alumno),
    CONSTRAINT fk_sistema_maestro
        FOREIGN KEY (id_maestro) 
        REFERENCES carlos.tmaestro (id_maestro),
    CONSTRAINT fk_sistema_carrera
        FOREIGN KEY (id_carrera, id_escuela)
        REFERENCES carlos.tcarrera (id_carrera, id_escuela),
    CONSTRAINT fk_sistema_materia
        FOREIGN KEY (id_materia) 
        REFERENCES carlos.tmateria (id_materia)
);