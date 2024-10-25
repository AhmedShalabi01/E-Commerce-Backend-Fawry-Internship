BEGIN;

CREATE TABLE IF NOT EXISTS users
(
    username character varying NOT NULL,
    password character varying NOT NULL,
    enabled smallint NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS authorities
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    username character varying NOT NULL,
    authority character varying NOT NULL,
    CONSTRAINT authorities_pkey PRIMARY KEY (id),
    CONSTRAINT "unique" UNIQUE (username, authority),
    CONSTRAINT "authority_FK" FOREIGN KEY (username)
        REFERENCES users (username) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

END;