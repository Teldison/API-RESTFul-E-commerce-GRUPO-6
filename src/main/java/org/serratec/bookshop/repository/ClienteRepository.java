package org.serratec.bookshop.repository;

import java.util.List;

import org.serratec.bookshop.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	@Query("SELECT c FROM Cliente c JOIN c.pedidos p WHERE p.id = :id")
	List<Cliente> buscarClientePorIdPedido(Long id);
	
}
