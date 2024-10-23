package org.serratec.bookshop.dto;

import java.time.LocalDate;
import org.serratec.bookshop.model.Livro;
import org.serratec.bookshop.model.PedidoItem;

public class PedidoRegistroDto {

	private Long id;
	private int quantidade;
	private Double precoVenda;
	private Double percentualDesconto;
	private Double valorBruto;
	private Double valorLiquido;
	private Long livroId;
	private LocalDate dataPedido;
	private LocalDate dataEntrega;

	public PedidoRegistroDto() {
		
	}
	
	public PedidoRegistroDto(Long id, int quantidade, Double precoVenda, Double percentualDesconto, Double valorBruto, Double valorLiquido, Long livroId) {
		
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.precoVenda = precoVenda;
		this.percentualDesconto = percentualDesconto;
		this.valorBruto = valorBruto;
		this.valorLiquido = valorLiquido;
		this.livroId = livroId;
	}
	
	public PedidoItem toEntity() {
		PedidoItem pedidoItem = new PedidoItem();
		pedidoItem.setId(id);
		pedidoItem.setQuantidade(quantidade);
		pedidoItem.setPrecoVenda(precoVenda);
		pedidoItem.setPercentualDesconto(percentualDesconto);
		pedidoItem.setValorBruto(valorBruto);
		pedidoItem.setValorLiquido(valorLiquido);
		Livro livro = new Livro();
		pedidoItem.setLivro(livro);
		pedidoItem.getLivro().setId(livroId);
		return pedidoItem;
	}
	
	public static PedidoRegistroDto toDto(PedidoItem pedidoItem) {
		return new PedidoRegistroDto(pedidoItem.getId(), pedidoItem.getQuantidade(), pedidoItem.getPrecoVenda(), pedidoItem.getPercentualDesconto(), pedidoItem.getValorBruto(), pedidoItem.getValorLiquido(), pedidoItem.getLivro().getId());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Double getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(Double percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public Double getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}

	public Double getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(Double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public Long getLivroId() {
		return livroId;
	}

	public void setLivroId(Long livroId) {
		this.livroId = livroId;
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
}