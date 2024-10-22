package org.serratec.bookshop.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "livro")
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String autor;
	private String editora;
	private int qnt_estoque;
	private LocalDate data_cadastro;
	private String sinopse;
	private int anoLancamento;
	private Double valor_unitario;
	private Categoria categoria;
	
	@OneToMany(mappedBy = "livro")
	private List<PedidoItem> pedidosItem;
	
	public Long getId() {
		return id;	
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
	
	public void setId(Long id) {
		this.id = id;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}