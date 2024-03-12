DROP SEQUENCE IF EXISTS public.veiculo_id_seq;
-- SEQUENCE: public.veiculo_seq

-- DROP SEQUENCE IF EXISTS public.veiculo_seq;

CREATE SEQUENCE IF NOT EXISTS public.veiculo_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1
    OWNED BY veiculo.id;

ALTER SEQUENCE public.veiculo_seq
    OWNER TO postgres;