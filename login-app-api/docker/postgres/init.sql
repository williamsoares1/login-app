CREATE TABLE USUARIO (
    usuario_id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR NOT NULL,
    cargo VARCHAR NOT NULL
);