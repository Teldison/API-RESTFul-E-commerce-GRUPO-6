CREATE TABLE endereco (
    id SERIAL PRIMARY KEY,
    cep VARCHAR(10) NOT NULL,
    rua VARCHAR(255) NOT NULL,
    bairro VARCHAR(255) NOT NULL,
    cidade VARCHAR(255) NOT NULL,
    numero INT NOT NULL,
    complemento VARCHAR(255),
    uf VARCHAR(2) NOT NULL
)
