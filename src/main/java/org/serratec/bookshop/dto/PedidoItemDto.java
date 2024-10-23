package org.serratec.bookshop.dto;

public record PedidoItemDto(int quantidade,
		Double percentualDesconto,
        Long livroId) {
}
