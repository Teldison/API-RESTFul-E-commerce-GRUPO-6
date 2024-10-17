package org.serratec.bookshop.repository;

import org.serratec.bookshop.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
