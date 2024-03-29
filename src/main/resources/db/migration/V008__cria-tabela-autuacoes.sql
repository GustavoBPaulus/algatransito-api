-- Table: public.autuacao

-- DROP TABLE IF EXISTS public.autuacao;

CREATE TABLE IF NOT EXISTS public.autuacao
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    veiculo_id bigint NOT NULL,
    descricao real NOT NULL,
    data_ocorrencia timestamp with time zone NOT NULL,
                                  CONSTRAINT id_autuacao_pk PRIMARY KEY (id),
    CONSTRAINT veiculo_id_fk FOREIGN KEY (veiculo_id)
    REFERENCES public.veiculo (id) MATCH SIMPLE
                              ON UPDATE NO ACTION
                              ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.autuacao
    OWNER to postgres;