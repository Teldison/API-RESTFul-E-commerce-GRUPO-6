CREATE TABLE livro (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    editora VARCHAR(255),
    qnt_estoque INT NOT NULL,
    data_cadastro DATE NOT NULL,
    sinopse TEXT,
    ano_lancamento INT,
    valor_unitario NUMERIC(8, 2),
    id_categoria INT,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id)
)
