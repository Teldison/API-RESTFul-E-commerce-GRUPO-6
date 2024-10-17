CREATE TABLE pedidoItem (
    id SERIAL PRIMARY KEY,
    quantidade INT NOT NULL,
    preco_venda NUMERIC(8, 2) NOT NULL,
    percentual_desconto NUMERIC(5, 2),
    valor_bruto NUMERIC(8, 2),
    valor_liquido NUMERIC(8, 2),
    id_pedido INT,
    id_livro INT,
    FOREIGN KEY (id_pedido) REFERENCES pedido(id),
    FOREIGN KEY (id_livro) REFERENCES livro(id)
)
