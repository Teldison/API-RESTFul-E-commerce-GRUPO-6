package org.serratec.bookshop.dto;

import java.time.LocalDate;
import java.util.List;

import org.serratec.bookshop.model.Categoria;
import org.serratec.bookshop.model.Pedido;
import org.serratec.bookshop.repository.LivroRepository;

public class RegistroPedidoDto {
	
	private Long id;
	private int quantidade;
	private LocalDate dataPedido;
	private LocalDate dataEntrega;
	private LocalDate dataEnvio;
	private String status;
	private Double valorTotal;
	private Long livroId;
	LivroDto livro;
	
	private String nome;
	private String autor;
	private String editora;
	private int qnt_estoque;
	private LocalDate data_cadastro;
	private String sinopse;
	private int anoLancamento;
	private Double valor_unitario;
	private Categoria categoria;
	private Object itensPedido;

	
	public RegistroPedidoDto(Long id, int quantidade, LocalDate dataPedido, LocalDate dataEntrega,
			LocalDate dataEnvio, String status, Double valorTotal, Long livroId,
			String nome, String autor,String editora, int qnt_estoque, LocalDate data_cadastro, 
			String sinopse, int anoLancamento, Double valor_unitario, Categoria categoria) {
		
		this.id = id;
		this.quantidade = quantidade;
		this.dataPedido =  dataPedido;
		this.dataEntrega =  dataEntrega;
		this.dataEnvio = dataEnvio;
		this.status = status;
		this.valorTotal = valorTotal;
		this.livroId = livroId;
		this.sinopse = sinopse;
		this.anoLancamento = anoLancamento;
		this.valor_unitario = valor_unitario;
		this.categoria = categoria;
	}

	public Pedido toEntity(LivroRepository livroRepository) {
        Pedido pedido = new Pedido();
        pedido.setId(this.id);
        pedido.setDataPedido(this.dataPedido);
        pedido.setDataEntrega(this.dataEntrega);
        pedido.setDataEnvio(this.dataEnvio);
        pedido.setStatus(this.status);
        pedido.setValorTotal(this.valorTotal);
        pedido.setLivro(this.livro.toEntity());
        
        Cliente cliente = new Cliente();
        cliente.setId(this.clienteId);
        pedido.setCliente(cliente);

        List<ItemPedido> itens = this.itensPedido.stream().map(itemDto -> {
            Livro pedido = livroRepository.findById(itemDto.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
            
            ItemPedido itemPedido = itemDto.toEntity();
            itemPedido.setProduto(produto);
            itemPedido.setPedido(pedido);
            return itemPedido;
        }).collect(Collectors.toList());

        pedido.setItensPedido(itens);
        return pedido;
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


	public LocalDate getDataEnvio() {
		return dataEnvio;
	}


	public void setDataEnvio(LocalDate dataEnvio) {
		this.dataEnvio = dataEnvio;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Double getValorTotal() {
		return valorTotal;
	}


	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}


	public Long getLivroId() {
		return livroId;
	}


	public void setLivroId(Long livroId) {
		this.livroId = livroId;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getAutor() {
		return autor;
	}


	public void setAutor(String autor) {
		this.autor = autor;
	}


	public String getEditora() {
		return editora;
	}


	public void setEditora(String editora) {
		this.editora = editora;
	}


	public int getQnt_estoque() {
		return qnt_estoque;
	}


	public void setQnt_estoque(int qnt_estoque) {
		this.qnt_estoque = qnt_estoque;
	}


	public LocalDate getData_cadastro() {
		return data_cadastro;
	}


	public void setData_cadastro(LocalDate data_cadastro) {
		this.data_cadastro = data_cadastro;
	}


	public String getSinopse() {
		return sinopse;
	}


	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}


	public int getAnoLancamento() {
		return anoLancamento;
	}


	public void setAnoLancamento(int anoLancamento) {
		this.anoLancamento = anoLancamento;
	}


	public Double getValor_unitario() {
		return valor_unitario;
	}


	public void setValor_unitario(Double valor_unitario) {
		this.valor_unitario = valor_unitario;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}