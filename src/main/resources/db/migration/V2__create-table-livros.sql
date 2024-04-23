CREATE TABLE livros (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(45) NOT NULL,
    autor VARCHAR(45) NOT NULL,
    data_lancamento DATE NOT NULL
);