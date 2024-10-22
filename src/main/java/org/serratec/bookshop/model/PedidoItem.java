package org.serratec.bookshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidoitem")
public class PedidoItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int quantidade;
	private Double precoVenda;
	private Double percentualDesconto;
	private Double valorBruto;
	private Double valorLiquido;
	
	@ManyToOne
	@JoinColumn(name = "id_pedido")
	private Pedido pedido;
	@ManyToOne
	@JoinColumn(name = "id_livro")
	private Livro livro;
	
//	public void calcularValorBruto() {
//        this.valorBruto = this.precoVenda * this.quantidade;
//    }
//	
//	public void aplicarDesconto() {
//        calcularValorBruto(); 
//        if (this.percentualDesconto != null && this.percentualDesconto > 0) {
//            double desconto = this.valorBruto * (this.percentualDesconto / 100);
//            this.valorLiquido = this.valorBruto - desconto; 
//        } else {
//            this.valorLiquido = this.valorBruto; 
//        }
//    }
	

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

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	

}
