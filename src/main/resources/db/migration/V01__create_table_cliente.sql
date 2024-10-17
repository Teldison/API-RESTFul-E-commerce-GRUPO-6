CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    nome_completo VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    telefone VARCHAR(20),
    data_nascimento DATE,
    id_endereco INT,
    FOREIGN KEY (id_endereco) REFERENCES endereco(id)
)