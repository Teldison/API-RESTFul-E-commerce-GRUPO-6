package org.serratec.bookshop.dto;

import java.time.LocalDate;
import java.util.List;

import org.serratec.bookshop.model.Livro;
import org.serratec.bookshop.model.PedidoItem;

public class PedidoRegistroDto {

	private Long id;
	private LocalDate dataPedido;
	private LocalDate dataEntrega;
	private Long clienteId;
	List<PedidoItemDto> itens;
	

	public PedidoRegistroDto() {
		
	}
	
	
	public PedidoRegistroDto(Long id, LocalDate dataPedido, LocalDate dataEntrega, Long clienteId,
			List<PedidoItemDto> itens) {
		super();
		this.id = id;
		this.dataPedido = dataPedido;
		this.dataEntrega = dataEntrega;
		this.clienteId = clienteId;
		this.itens = itens;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalDate getDataPedido() {
		return dataPedido;
	}


	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}


	public LocalDate getDataEntrega() {
		return dataEntrega;
	}


	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}


	public Long getClienteId() {
		return clienteId;
	}


	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}


	public List<PedidoItemDto> getItens() {
		return itens;
	}


	public void setItens(List<PedidoItemDto> itens) {
		this.itens = itens;
	}

}