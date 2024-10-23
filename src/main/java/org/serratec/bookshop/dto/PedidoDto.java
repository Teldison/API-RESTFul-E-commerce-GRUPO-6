package org.serratec.bookshop.dto;

import java.time.LocalDate;

import org.serratec.bookshop.model.Pedido;

public record PedidoDto(
		Long id,
		LocalDate dataPedido,
		LocalDate dataEntrega,
		LocalDate dataEnvio,
		String status,
		Double valorTotal,
		ClienteDto cliente,
		LivroDto livro
		) {
	
	public Pedido toEntity() {
		Pedido pedido = new Pedido();
		pedido.setId(this.id);
		pedido.setDataPedido(dataPedido);
		pedido.setDataEntrega(dataEntrega);
		pedido.setDataEnvio(dataEnvio);
		pedido.setStatus(this.status);
		pedido.setValorTotal(this.valorTotal);
		pedido.setLivro(livro.toEntity());
		if (cliente != null) {
			pedido.setCliente(cliente.toEntity());
		}
		return pedido;
	}
	
	public static PedidoDto toDto(Pedido pedido) {
		ClienteDto clienteDto = pedido.getCliente() != null ? ClienteDto.toDto(pedido.getCliente()) : null;
		return new PedidoDto(
				pedido.getId(),
				pedido.getDataPedido(),
				pedido.getDataEntrega(),
				pedido.getDataEnvio(),
				pedido.getStatus(), 
				pedido.getValorTotal(),
				clienteDto				
				);
	}
}