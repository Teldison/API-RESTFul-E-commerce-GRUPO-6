CREATE TABLE pedido (
    id SERIAL PRIMARY KEY,
    data_pedido DATE NOT NULL,
    data_entrega DATE,
    data_envio DATE,
    status VARCHAR(50) NOT NULL,
    valor_total NUMERIC(8, 2) NOT NULL,
    id_cliente INT,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id)
)
