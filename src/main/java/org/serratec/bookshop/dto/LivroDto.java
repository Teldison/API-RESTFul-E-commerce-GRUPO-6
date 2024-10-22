package org.serratec.bookshop.dto;

import java.time.LocalDate;

import org.serratec.bookshop.model.Categoria;
import org.serratec.bookshop.model.Livro;

public record LivroDto(
		Long id,
		String nome,
		String autor,
		String editora,
		int qnt_estoque,
		LocalDate data_cadastro,
		String sinopse,
		int anoLancamento,
		Double valor_unitario,
		Categoria categoria
		) {
	
	public Livro toEntity() { 
		Livro livro = new Livro();
		livro.setId(this.id);
		livro.setNome(this.nome);
		livro.setAutor(this.autor);
		livro.setEditora(this.editora);
		livro.setQnt_estoque(this.qnt_estoque);
		livro.setData_cadastro(this.data_cadastro);
		livro.setSinopse(this.sinopse);
		livro.setAnoLancamento(this.anoLancamento);
		livro.setValor_unitario(this.valor_unitario);
		livro.setCategoria(this.categoria);
		
       return livro;
	}
	
	public static LivroDto toDto(Livro livro) { 
		return new LivroDto(livro.getId(), livro.getNome(), livro.getAutor(),livro.getEditora(), 
				livro.getQnt_estoque(), livro.getData_cadastro(), livro.getSinopse(), 
				livro.getAnoLancamento(), livro.getValor_unitario(), livro.getCategoria());
				
	}
	
	
}
