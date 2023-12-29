CREATE TABLE IF NOT EXISTS public.delivery
(
    delivery_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    courier_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    created_timestamp timestamp(6) without time zone NOT NULL,
    last_changed_timestamp timestamp(6) without time zone,
    state integer,
    value numeric(38,2) NOT NULL,
    CONSTRAINT delivery_pkey PRIMARY KEY (delivery_id)
);