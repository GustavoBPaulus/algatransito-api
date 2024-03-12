-- Table: public.veiculo

-- DROP TABLE IF EXISTS public.veiculo;

CREATE TABLE IF NOT EXISTS public.veiculo
(
    id bigint NOT NULL,
    proprietario_id bigint NOT NULL,
    marca character varying(20)[] COLLATE pg_catalog."default" NOT NULL,
    modelo character varying(20)[] COLLATE pg_catalog."default" NOT NULL,
    placa character varying(7)[] COLLATE pg_catalog."default" NOT NULL,
    status character varying(20) COLLATE pg_catalog."default" NOT NULL,
    data_cadastro timestamp without time zone NOT NULL,
    data_apreensao timestamp without time zone,
    CONSTRAINT veiculo_pkey PRIMARY KEY (id),
    CONSTRAINT uk_veiculo UNIQUE (placa),
    CONSTRAINT proprietario_fk FOREIGN KEY (proprietario_id)
    REFERENCES public.proprietario (id) MATCH SIMPLE
                            ON UPDATE NO ACTION
                            ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.veiculo
    OWNER to postgres;