CREATE SEQUENCE public.proprietario_id_seq
    INCREMENT 1;

ALTER SEQUENCE public.proprietario_id_seq
    OWNER TO postgres;
-- Table: public.proprietario

-- DROP TABLE IF EXISTS public.proprietario;

CREATE TABLE IF NOT EXISTS public.proprietario
(
    id bigint NOT NULL DEFAULT nextval('proprietario_id_seq'::regclass),
    nome character varying(60) COLLATE pg_catalog."default" NOT NULL,
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    telefone character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT proprietario_pkey PRIMARY KEY (id),
    CONSTRAINT uk_proprietario UNIQUE (email)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.proprietario
    OWNER to postgres;